package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.route.Route;
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

	public final static int HORIZON = 250;
	public final int LARGEUR_ROUTE = 100;

	private final Affichage affichage;
	private Route route;
	private int positionDecor;
	private final Player player;
	private final Move move;
	private double facteurVitesse = 1.0;

	private KeyboardController controller;

	private int time = 30000;
	private boolean cpCross = false, nvCP = true;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		route = new Route(this, LARGEUR_ROUTE);
		player = new Player(new Point(route.getFirstPosXPlayer(), Affichage.HAUTEUR / 2));
		move = new Move(player);
		this.controller = controller;
		controller.setMove(move);
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
		return new Point(tmp.x, Affichage.HAUTEUR - tmp.y);
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

	public int getPositionRoute() {
		return route.getPosition();
	}

	public Route getRoute() {
		return route;
	}

	/**
	 * Renvoie les points de la route dans le sens inversé
	 * @return
	 */
	public ArrayList<Point> getRoutePoints(){
		ArrayList<Point> temp = new ArrayList<>();
		for(Point p : route.getPoints())
		{
			temp.add(new Point(p.x, Affichage.HAUTEUR - p.y));
		}
		return temp;
	}

	public String getScore() {
		return "Score : " + route.getPosition();
	}

	/*
	 * Joueur
	 */
	public int getTailleJoueur() {
		return player.TAILLE;
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
		views.add(obstacleView);
		views.add(decor);
		views.add(horizon);
		views.add(gameInfo);
		affichage.addViews(views);
		affichage.addKeyListener(controller);
	}

	public void move() {
		move.doMove();
		/*
		 * On check si le joueur est sur la route ou en dehors
		 */
		int positionRelative = getPositionRoute() % Route.ESPACEMENT;
		Point p1 = getRoutePoints().get(1);
		Point p2 = getRoutePoints().get(2);
		Point mid = new Point((int)(p1.x + (p2.x - p1.x) / (Route.ESPACEMENT * 1.0) * positionRelative), 700);
		if(getPlayerX() + getTailleJoueur() / 2 < mid.x - LARGEUR_ROUTE || getPlayerX() + getTailleJoueur() / 2 > mid.x + LARGEUR_ROUTE) {
			//ralentissement
			if(facteurVitesse <= 10.0) {
				facteurVitesse *= 1.02;
			}
		}
		else if (facteurVitesse != 1){ //acceleration
			if(facteurVitesse > 1) {
				facteurVitesse *= 0.97;
			} else {
				facteurVitesse = 1.0;
			}
		}
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
		String formatted = String.format("%02d", sec);
		return "Temps restant : " + min + ":" + formatted;
	}

	public void time() {
		time -= Temps.TIME;
		if(cpCross && nvCP){
			nvCP = false;
			cpCross = false;
			time += 30000;
		}
	}
	
	public boolean isRight() {
		return move.isRight();
	}
	
	public boolean isLeft() {
		return move.isLeft();
	}
}
