package kozlov_ponroy.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.List;

import javax.swing.JPanel;

import kozlov_ponroy.model.route.Obstacle;
import kozlov_ponroy.model.state.Etat;

/**
 * Gère l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	public static final int HAUTEUR = 800;
	public static final int LARGEUR = 800;
	private Etat etat;
	private Point p1, p2, p3;
	private int[] routeX;
	private int[] routeY;
	Graphics2D graphics2D, vaisseauG;
	final private Image playerLeftImage;
	final private Image playerRightImage;
	final private Image playerCenterImage;
	final private Image treeImage;
	final private Image montagneImage;
	final private Image nuage1;
	final private Image nuage2;
	final private Image grass;
	final private Image grass2;
	final private Image fense;
	final float NB_BANDE = 40;
	final Color C_VAISSEAU;
	final Color C_ROUTE;
	QuadCurve2D courbeDroite = new QuadCurve2D.Double();
	QuadCurve2D courbeGauche = new QuadCurve2D.Double();
	QuadCurve2D courbeBande = new QuadCurve2D.Double();
	final private float dash1[] = {20.0f};
	final private BasicStroke dashed;
	Point2D debut;
	Point2D ctrl;
	Point2D fin;
	GeneralPath closedCurve;
	final private int VITESSE_MAX = 1200;
	List<Point> route;

	public Affichage(KeyListener listener){
		/**
		 *  Initialise la taille de la fenetre au lancement
		 */
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		addKeyListener(listener);
		setFocusable(true);

		/**
		 * Tableau pour routes
		 */
		routeX = new int[5];
		routeY = new int[5];

		/**
		 * Initialisation images
		 */
		playerLeftImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_left.png");
		playerCenterImage = Toolkit.getDefaultToolkit().getImage("./ressources/playerTest.png");
		playerRightImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_right.png");
		treeImage = Toolkit.getDefaultToolkit().getImage("./ressources/tree.png");
		montagneImage = Toolkit.getDefaultToolkit().getImage("./ressources/montagne.png");
		nuage1 = Toolkit.getDefaultToolkit().getImage("./ressources/nuage_1.png");
		nuage2 = Toolkit.getDefaultToolkit().getImage("./ressources/nuage_2.png");
		grass = Toolkit.getDefaultToolkit().getImage("./ressources/Grass_Texture.jpg");
		grass2 = Toolkit.getDefaultToolkit().getImage("./ressources/Grass_Texture_2.jpg");
		fense = Toolkit.getDefaultToolkit().getImage("./ressources/fense.png");
		/**
		 * Initialisation couleurs et stroke
		 */
		C_VAISSEAU = new Color(0,0,0,170);
		C_ROUTE = new Color(100,100,100,255);
		dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
	}

	/**
	 * Appel de la fonction paint pour effacer l'ensemble de la fenetre puis dessiner les éléments
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);

		/**
		 * Recuperation graphique et antialiasing on
		 */
		graphics2D = (Graphics2D) g;
		vaisseauG = (Graphics2D) g.create();
		graphics2D.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, LARGEUR, HAUTEUR);

		/**
		 * Ligne d'horizon
		 */
		g.setColor(Color.BLACK);
		g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());
		//graphics2D.drawImage(grass, 0, etat.getPosition() % (HAUTEUR + HAUTEUR / 2) - HAUTEUR / 2, LARGEUR, HAUTEUR / 2 + etat.getHorizon(), null);
		/**
		 * TODO: Roulement image pour decor
		 */
		//graphics2D.drawImage(grass, 0, (etat.getPosition()% (HAUTEUR + HAUTEUR / 2)) - HAUTEUR / 2, LARGEUR, HAUTEUR / 2 + etat.getHorizon(), null);
		//graphics2D.drawImage(grass, 0, etat.getHorizon() * 2 + HAUTEUR / 2 + etat.getPosition() % HAUTEUR, LARGEUR, HAUTEUR / 2  + etat.getHorizon(), null);
		graphics2D.drawLine(0, HAUTEUR, LARGEUR, HAUTEUR);
		/**
		 * Affichage de la route
		 */
		g.setColor(Color.gray);
		route = etat.getRoutePoints();
		for(int i = 0 ; i < route.size() - 2 ; i++) // -2 : besoin de 3 points pour construire les routes
		{
			g.setColor(Color.gray);
			p1 = route.get(i);
			p2 = route.get(i+1);
			p3 = route.get(i+2);

			/**
			 * Definition des points pour la route
			 */
			routeX[0] = p1.x - etat.getFacteurElargissement(p1.y) / 2; routeY[0] = p1.y; //Point bas	gauche
			routeX[1] = p2.x - etat.getFacteurElargissement(p2.y) / 2; routeY[1] = p2.y; //Point haut	gauche
			routeX[2] = p2.x + etat.getFacteurElargissement(p2.y) / 2; routeY[2] = p2.y; //Point haut	droite
			routeX[3] = p1.x + etat.getFacteurElargissement(p1.y) / 2; routeY[3] = p1.y; //Point bas	droite

			g.setColor(Color.BLACK);
			/**
			 * Courbe droite route
			 */
			routeX[4] = p3.x - etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			debut = new Point2D.Double(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			ctrl = new Point2D.Double(routeX[1], routeY[1]);
			fin = new Point2D.Double(routeX[1] + (routeX[4]- routeX[1]) / 2, routeY[1] + (routeY[4]- routeY[1]) / 2);
			courbeDroite.setCurve(debut,ctrl,fin);

			/**
			 * Courbe gauche route
			 */
			routeX[4] = p3.x + etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			fin = new Point2D.Double(routeX[3] + (routeX[2]- routeX[3]) / 2, routeY[3] + (routeY[2]- routeY[3]) / 2);
			ctrl = new Point2D.Double(routeX[2], routeY[2]);
			debut = new Point2D.Double(routeX[2] + (routeX[4]- routeX[2]) / 2, routeY[2] + (routeY[4]- routeY[2]) / 2);
			courbeGauche.setCurve(debut,ctrl,fin);

			/**
			 * Courbe ferme qui est la route entiere
			 */
			closedCurve = new GeneralPath();
			closedCurve.append(courbeDroite, true);
			closedCurve.append(courbeGauche, true);
			closedCurve.lineTo(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			closedCurve.closePath();

			/**
			 * Courbe bande
			 */
			debut = new Point2D.Double(p1.x + (p2.x - p1.x) / 2, p1.y + (p2.y - p1.y) / 2);
			ctrl = new Point2D.Double(p2.x, p2.y);
			fin = new Point2D.Double(p2.x + (p3.x - p2.x) / 2, p2.y + (p3.y - p2.y) / 2);
			courbeBande.setCurve(debut,ctrl,fin);

			/**
			 * Affichage route
			 */
			graphics2D.setStroke(new BasicStroke(5));
			graphics2D.setColor(C_ROUTE);
			graphics2D.fill(closedCurve);
			graphics2D.draw(courbeGauche);
			graphics2D.draw(courbeDroite);

			/**
			 * Affichage bande route
			 */
			graphics2D.setStroke(dashed);
			graphics2D.setColor(Color.white);
			graphics2D.draw(courbeBande);
		}

		//Affichage des obstacles
		for(Obstacle o : etat.getRoute().getObstacles()) {
			g.setColor(Color.black);
			int width = Math.abs(etat.getFacteurElargissement(o.getY()));
			//width = 100;
			graphics2D.drawImage(fense, o.getX() - width/2, o.getY(), width,o.getHauteur() * width,null);
		}

		/**
		 * Affichage du joueur
		 */
		g.setColor(C_VAISSEAU);
		g.fillOval(etat.getPlayerX(), etat.getHauteurJoueur() + 100, etat.getTailleJoueur(), 20);
		vaisseauG.drawImage(playerCenterImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur() / 2, null);
		/**
		 * Suppresion de la route au dessus de l'horizon
		 */
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());

		/**
		 * Decor du fond
		 */
		graphics2D.drawImage(montagneImage, LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, LARGEUR + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, -50 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(nuage1, 300 + etat.getPositionDecor(), etat.getHorizon() - 260, 400, 130, null);
		graphics2D.drawImage(nuage2, -50 + etat.getPositionDecor(), etat.getHorizon() - 260, 300, 130, null);
		/**
		 * Affichages informations jeu
		 */
		g.setColor(Color.black);
		g.drawString(etat.getScore(), 20, 20);
		g.drawString("Vitesse : " + (int)(VITESSE_MAX / etat.getFacteurVitesse()) + " km/h", 20, 60);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
