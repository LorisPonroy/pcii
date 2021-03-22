package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.route.Route;
import kozlov_ponroy.model.route.RoutePreview;
import kozlov_ponroy.model.state.Move;
import kozlov_ponroy.model.state.Player;
import kozlov_ponroy.model.threads.MouvementRoute;
import kozlov_ponroy.model.threads.MouvementVehicule;
import kozlov_ponroy.model.threads.Temps;
import kozlov_ponroy.view.Affichage;
import kozlov_ponroy.view.IAffichage;
import kozlov_ponroy.view.objects.Decor;
import kozlov_ponroy.view.objects.GameInfoView;
import kozlov_ponroy.view.objects.Horizon;
import kozlov_ponroy.view.objects.ObstacleView;
import kozlov_ponroy.view.objects.RouteView;
import kozlov_ponroy.view.objects.Vaisseau;

/**
 * Gère l'état de notre MVC
 *
 * @author Asey
 *
 */

public class Etat {
	//Taille de l'ecran
	public static final int HAUTEUR = 400;
	public static final int LARGEUR = 800;

	public final static int HORIZON = 200;
	public final static int LARGEUR_ROUTE = 300;

	private final Affichage affichage;
	private Route route;
	private int positionDecor;
	private final Player player;
	private final Move move;
	private double facteurVitesse = 1.0;
	private final int VITESSE_BASE = 100;

	private KeyboardController controller;

	private int time = 30000;
	private boolean cpCross = false, nvCP = true;
	final RoutePreview routePreview;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		route = new Route(this);
		player = new Player(new Point(route.getFirstPosXPlayer(), HAUTEUR - 50));
		move = new Move(player, this);
		this.controller = controller;
		controller.setMove(move);
		routePreview = new RoutePreview(this);
		initAffichage();

		new MouvementVehicule(this).start();
		new MouvementRoute(this).start();
		new Temps(this).start();
		positionDecor = 0;
	}

	public void avancerRoute() {
		route.avancer();
		if(getCheckPoint().y > player.getY()) {
			cpCross = true;
		} else {
			cpCross = false;
		}
	}

	public void genererObstacle() {
		route.genererObstacle();
	}

	public Point getCheckPoint() {
		Point tmp = route.getCheckPoint();
		return new Point(tmp.x, HAUTEUR - tmp.y);
	}

	public int getFacteurElargissement(int y) {
		return (int) ((HORIZON-y) * 0.4);
	}

	public double getFacteurVitesse() {
		return facteurVitesse;
	}

	public int getHauteurJoueur() {
		return player.HAUTEUR;
	}

	public int getHorizon() {
		return HORIZON;
	}

	public int getPlayerX() {
		return player.getX();
	}

	public int getPlayerY() {
		return player.getY();
	}

	public int getPositionDecor() {
		return positionDecor;
	}

	public Route getRoute() {
		return route;
	}

	/**
	 * Renvoie les points de la route dans le sens inversé
	 * @return
	 */
	public ArrayList<Point> getRoutePoints(){
		return route.getPoints();
	}

	public String getScore() {
		return "Score : ";
	}

	/*
	 * Joueur
	 */
	public int getTailleJoueur() {
		return player.TAILLE;
	}

	public int getVitesse() {
		return (int) (VITESSE_BASE * (1.0 / facteurVitesse));
	}

	/*
	 * FIN Joueur
	 */

	public void initAffichage() {
		List<IAffichage> views = new ArrayList<>();
		Vaisseau vaisseau = new Vaisseau(this);
		Horizon horizon = new Horizon(this);
		RouteView routeView = new RouteView(this);
		Decor decor = new Decor(this);
		GameInfoView gameInfo = new GameInfoView(this);
		ObstacleView obstacleView = new ObstacleView(this);
		views.add(routeView);
		views.add(vaisseau);
		//views.add(obstacleView);
		views.add(decor);
		views.add(horizon);
		views.add(gameInfo);
		affichage.addViews(views);
		affichage.addKeyListener(controller);
	}

	public boolean isGameOver() {
		return facteurVitesse > 100;
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
		else if(move.isUp() && getVitesse() < 250) { //accelere
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

	public RoutePreview route() {
		return routePreview;
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
	}

	public int transformePositionToPerspective(int x,int y) {
		double centre = 400;
		double a = (x - centre) / (800.0 - HORIZON);
		double b = x - a*800.0;
		return (int) Math.round(a * y + b);
	}

}
