package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.decor.DecorPreview;
import kozlov_ponroy.model.decor.GenerationDecor;
import kozlov_ponroy.model.obstacle.GenerationObstacle;
import kozlov_ponroy.model.obstacle.ObstaclePreview;
import kozlov_ponroy.model.route.Route;
import kozlov_ponroy.model.state.Move;
import kozlov_ponroy.model.state.Player;
import kozlov_ponroy.model.threads.MouvementRoute;
import kozlov_ponroy.model.threads.MouvementVehicule;
import kozlov_ponroy.model.threads.Temps;
import kozlov_ponroy.view.Affichage;
import kozlov_ponroy.view.IView;
import kozlov_ponroy.view.objects.Decor;
import kozlov_ponroy.view.objects.GameInfoView;
import kozlov_ponroy.view.objects.Horizon;
import kozlov_ponroy.view.objects.ObstacleView;
import kozlov_ponroy.view.objects.PlayerView;
import kozlov_ponroy.view.objects.RouteView;

/**
 * G�re l'�tat de notre MVC
 *
 * @author Asey
 *
 */

public class Etat {
	private static final int CONSTANT_SPEED = 100;

	public static final int HAUTEUR = 500;

	public static final int LARGEUR = 800;

	public final static int HORIZON = 200;
	public final static int LARGEUR_ROUTE = 500;

	private final Affichage affichage;
	private Route route;
	private final Player player;
	private final Move move;

	private double facteurVitesse = 1.0;
	private KeyboardController controller;
	private int time = 30000;

	private boolean cpCross = false, nvCP = true;

	final GenerationDecor generationDecor;
	final GenerationObstacle generationObstacles;

	private int posDecor = 0;
	
	private boolean stop = false;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		route = new Route(this);
		player = new Player(new Point(LARGEUR/2, HAUTEUR - 50));
		move = new Move(player, this);
		this.controller = controller;
		controller.setMove(move);
		initAffichage();
		generationDecor = new GenerationDecor();
		generationObstacles = new GenerationObstacle(this);

		new MouvementVehicule(this).start();
		new MouvementRoute(this).start();
		new Temps(this).start();

	}

	public void avancerRoute() {
		route.avancer();
		generationObstacles.move();
		if(getCheckPoint().y > player.getY()) {
			cpCross = true;
		} else {
			cpCross = false;
		}
	}

	/**
	 * Fonction appeler lorsque le joueur fait une collision avec un obstacle
	 */
	public void collide(boolean isMoto) {
		//System.out.println("Collide");
		facteurVitesse += 0.1;
		if(isMoto) {
			//On arrete le jeu car la moto s'est crashé
			stop = true;
		}
	}

	public void doABarrelRoll() {
		facteurVitesse *= 1.01;
		affichage.doABarrelRoll();
	}

	public Point getCheckPoint() {
		return route.getCheckPoint();
	}

	public List<DecorPreview> getDecors() {
		return generationDecor.getDecors();
	}

	public int getFacteurElargissement(int y) {
		return (int) ((HORIZON-y) * 0.4);
	}

	public double getFacteurVitesse() {
		return facteurVitesse;
	}

	public int getHorizon() {
		return HORIZON;
	}

	public ArrayList<ObstaclePreview> getObstacles(){
		return generationObstacles.getObstacles();
	}

	public Player getPlayer() {
		return player;
	}

	public int getPlayerX() {
		return player.getX();
	}

	/*
	 * FIN Joueur
	 */

	public int getPlayerY() {
		return player.getY();
	}

	public int getPosDecor() {
		return posDecor;
	}

	public int getRandom(int i) {
		return new Random().nextInt(i);
	}

	public Route getRoute() {
		return route;
	}

	/**
	 * Renvoie les points de la route dans le sens invers�
	 * @return
	 */
	public List<Point> getRoutePoints(){
		return route.getPoints();
	}

	public String getScore() {
		return "Score : ";
	}

	/*
	 * Joueur
	 */
	public int getTailleJoueur() {
		return Player.HAUTEUR;
	}

	public int getVitesse() {
		return (int) (CONSTANT_SPEED * (1.0 / facteurVitesse));
	}

	public void initAffichage() {
		List<IView> views = new ArrayList<>();
		PlayerView playerView = new PlayerView(this);
		Horizon horizon = new Horizon(this);
		RouteView routeView = new RouteView(this);
		Decor decor = new Decor(this);
		GameInfoView gameInfo = new GameInfoView(this);
		ObstacleView obstacleView = new ObstacleView(this);
		views.add(routeView);
		views.add(obstacleView);
		views.add(decor);
		views.add(horizon);
		views.add(gameInfo);
		views.add(playerView);
		affichage.addViews(views);
		affichage.addKeyListener(controller);
	}

	public boolean isGameOver() {
		return time < 0 || stop;
	}

	public boolean isLeft() {
		return move.isLeft();
	}

	public boolean isRight() {
		return move.isRight();
	}

	public void move() {
		move.doMove();

		/*
		 * On check si le joueur est sur la route ou en dehors
		 */
		//if(!routePreview.routeGeneralPath(null).contains(getPlayerX(), getPlayerY())) {
		//ralentissement
		//facteurVitesse *= 1.01;
		//} else { //vitesse de base
		if(move.isDown()) { //freine
			facteurVitesse *= 1.01;
		}
		else if(move.isUp() && getVitesse() < 250 && getVitesse() >= 100) { //accelere
			facteurVitesse *= 0.998;
		} else if(facteurVitesse > 1) {
			facteurVitesse *= 0.99;
		}
		//}
		affichage.repaint();
	}

	public void nouveauCP() {
		nvCP = true;
	}

	public int tailleCP() {
		return -getFacteurElargissement(getCheckPoint().y);
	}

	public String tempsRestant() {
		int sec = time / 1000;
		int min = sec / 60;
		sec = sec % 60;
		return "Temps restant : " + min + ":" + String.format("%02d", sec);
	}

	public void time() {
		time -= Temps.TIME;
		if(cpCross && nvCP){
			nvCP = false;
			cpCross = false;
			time += 30000;
		}
		generationDecor.move();
	}

	public int transformePositionToPerspective(int x,int y) {
		double centre = 400;
		double a = (x - centre) / (800.0 - HORIZON);
		double b = x - a*800.0;
		return (int) Math.round(a * y + b);
	}
}
