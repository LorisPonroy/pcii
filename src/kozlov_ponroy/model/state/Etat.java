package kozlov_ponroy.model.state;

import java.awt.Point;
import java.util.ArrayList;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.route.Route;
import kozlov_ponroy.model.threads.MouvementRoute;
import kozlov_ponroy.model.threads.MouvementVehicule;
import kozlov_ponroy.view.Affichage;

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
	private final KeyboardController controller;
	private double facteurVitesse = 1.0;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		route = new Route(Affichage.LARGEUR, Affichage.HAUTEUR, LARGEUR_ROUTE);
		player = new Player(new Point(route.getFirstPosXPlayer(), Affichage.HAUTEUR / 2));
		move = new Move(player);
		this.controller = controller;
		controller.setMove(move);
		
		new MouvementVehicule(this).start();
		new MouvementRoute(this).start();
		positionDecor = 0;
	}

	public void avancerRoute() {
		route.avancer();
	}

	public void genererObstacle() {
		route.genererObstacle();
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

	/**
	 * Renvoie les points de la route dans le sens inversé
	 * @return
	 */
	public ArrayList<Point> getRoutePoints(){
		ArrayList<Point> temp = new ArrayList<>();
		for(Point p : route.getPoints())
		{
			temp.add(new Point(p.x, affichage.HAUTEUR - p.y));
		}
		return temp;
	}

	public int getPositionRoute() {
		return route.getPosition();
	}

	public int getPositionDecor() {
		return positionDecor;
	}

	public Route getRoute() {
		return route;
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
	
	public int getPlayerX() {
		return player.getX();
	}

	public int getPlayerY() {
		return player.getY();
	}
	
	public int getHauteurJoueur() {
		return player.HAUTEUR;
	}
	
	/* 
	 * FIN Joueur 
	 */

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
}
