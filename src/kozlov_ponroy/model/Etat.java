package kozlov_ponroy.model;

import java.awt.Point;
import java.awt.geom.GeneralPath;
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
 * G�re l'�tat de notre MVC
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
	private final int VITESSE_BASE = 100;

	private KeyboardController controller;

	private int time = 30000;
	private boolean cpCross = false, nvCP = true;
	final RoutePreview routePreview;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		route = new Route(this, LARGEUR_ROUTE);
		player = new Player(new Point(route.getFirstPosXPlayer(), Affichage.HAUTEUR - 50));
		move = new Move(player, this);
		this.controller = controller;
		controller.setMove(move);
		this.routePreview = new RoutePreview(this);
		initAffichage();

		new MouvementVehicule(this).start();
		new MouvementRoute(this).start();
		new Temps(this).start();
		positionDecor = 0;

		System.out.println("Fe(y=H) = "  + getFacteurElargissement(HORIZON));
		System.out.println("Fe(y=0) = "  + getFacteurElargissement(0));
		System.out.println("Fe(y=H/2) = "  + getFacteurElargissement(HORIZON/2));
		System.out.println("---");
		System.out.println("fp(100,H) = " + transformePositionToPerspective(100, HORIZON));
		System.out.println("fp(100,0) = " + transformePositionToPerspective(100, 0));
		System.out.println("fp(100,H/2) = " + transformePositionToPerspective(100, HORIZON/2));
		System.out.println("---");
		System.out.println("fp(0,H) = " + transformePositionToPerspective(0, HORIZON));
		System.out.println("fp(0) = " + transformePositionToPerspective(0, 0));
		System.out.println("fp(0,H/2) = " + transformePositionToPerspective(0, HORIZON/2));
		System.out.println("---");
		System.out.println("fp(-100,H) = " + transformePositionToPerspective(-100, HORIZON));
		System.out.println("fp(-100,0) = " + transformePositionToPerspective(-100, 0));
		System.out.println("fp(-100,H/2) = " + transformePositionToPerspective(-100, HORIZON/2));
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
	 * Renvoie les points de la route dans le sens invers�
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

	public void initAffichage() {
		List<IAffichage> views = new ArrayList<>();
		Vaisseau vaisseau = new Vaisseau(this);
		Horizon horizon = new Horizon(this);
		RouteView routeView = new RouteView(this, routePreview);
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

	/*
	 * FIN Joueur
	 */

	public void move() {
		move.doMove();
		
		/*
		 * On check si le joueur est sur la route ou en dehors
		 */
		if(!routePreview.routeGeneralPath().contains(getPlayerX(), getPlayerY())) {
			//ralentissement
			facteurVitesse *= 1.01;
		} else { //vitesse de base
			if(move.isDown()) { //freine
				facteurVitesse *= 1.01;
			}
			else if(move.isUp() && getVitesse() < 250) { //accelere
				facteurVitesse *= 0.998;
			} else if(facteurVitesse > 1) {
				facteurVitesse *= 0.99;
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
	
	public boolean isRight() {
		return move.isRight();
	}
	
	public boolean isLeft() {
		return move.isLeft();
	}

	public int transformePositionToPerspective(int x,int y) {
		double centre = 400;
		double a = (x - centre) / (800.0 - HORIZON);
		double b = x - a*800.0;
		return (int) Math.round(a * y + b);
	}
	
	public RoutePreview route() {
		return routePreview;
	}
	
	public boolean isGameOver() {
		return facteurVitesse > 100;
	}
	
	public int getVitesse() {
		return (int) (VITESSE_BASE * (1.0 / facteurVitesse));
	}
	
}
