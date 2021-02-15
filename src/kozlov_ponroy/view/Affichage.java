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

import kozlov_ponroy.model.Etat;

/**
 * Gère l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	public final int HAUTEUR = 800;
	public final int LARGEUR = 800;
	private Etat etat;
	private Point p1, p2, p3;
	private int[] x;
	private int[] y;
	Graphics2D graphics2D;
	final private Image playerLeftImage;
	final private Image playerRightImage;
	final private Image playerCenterImage;
	final private Image treeImage;
	final private Image montagneImage;
	final float NB_BANDE = 40;
	final Color C_VAISSEAU;
	QuadCurve2D courbeGauche = new QuadCurve2D.Double();
	QuadCurve2D courbeDroite = new QuadCurve2D.Double();
	Point2D debut;
	Point2D ctrl;
	Point2D fin;
	GeneralPath closedCurve;

	public Affichage(KeyListener listener){
		// Initialise la taille de la fenetre au lancement
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		addKeyListener(listener);
		setFocusable(true);
		x = new int[5];
		y = new int[5];
		playerLeftImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_left.png");
		playerCenterImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_center.png");
		playerRightImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_right.png");
		treeImage = Toolkit.getDefaultToolkit().getImage("./ressources/tree.png");
		montagneImage = Toolkit.getDefaultToolkit().getImage("./ressources/montagne.png");
		C_VAISSEAU = new Color(0,0,0,170);
		closedCurve = new GeneralPath();
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.clearRect(0, 0, LARGEUR, HAUTEUR);
		g.setColor(Color.BLACK);
		//Affichage de la ligne d'horizon
		g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());

		//Affichage de la route
		g.setColor(Color.gray);
		List<Point> route = etat.getRoute();
		for(Point p : route) {
			//p.y+=etat.getHorizon();
		}
		for(int i = 0 ; i < route.size() - 1 ; i++)
		{
			g.setColor(Color.gray);
			p1 = route.get(i);
			p2 = route.get(i+1);

			x[0] = p1.x - etat.getFacteurElargissement(p1.y) / 2; y[0] = p1.y;
			x[1] = p2.x - etat.getFacteurElargissement(p2.y) / 2; y[1] = p2.y;
			x[2] = p2.x + etat.getFacteurElargissement(p2.y) / 2; y[2] = p2.y;
			x[3] = p1.x + etat.getFacteurElargissement(p1.y) / 2; y[3] = p1.y;
			x[4] = p1.x; y[4] = p1.y;
			//graphics2D.fillPolygon(x, y, x.length);


			if(i < route.size() - 2) {
				g.setColor(Color.BLACK);
				graphics2D.setStroke(new BasicStroke(5));
				p3 = route.get(i+2);
				x[4] = p3.x - etat.getFacteurElargissement(p3.y) / 2;	y[4] = p3.y;
				debut = new Point2D.Double(x[0] + (x[1]- x[0]) / 2, y[0] + (y[1]- y[0]) / 2);
				ctrl = new Point2D.Double(x[1], y[1]);
				fin = new Point2D.Double(x[1] + (x[4]- x[1]) / 2, y[1] + (y[4]- y[1]) / 2);
				courbeGauche.setCurve(debut,ctrl,fin);
				graphics2D.draw(courbeGauche);

				x[4] = p3.x + etat.getFacteurElargissement(p3.y) / 2;	y[4] = p3.y;
				fin = new Point2D.Double(x[3] + (x[2]- x[3]) / 2, y[3] + (y[2]- y[3]) / 2);
				ctrl = new Point2D.Double(x[2], y[2]);
				debut = new Point2D.Double(x[2] + (x[4]- x[2]) / 2, y[2] + (y[4]- y[2]) / 2);
				courbeDroite.setCurve(debut,ctrl,fin);
				graphics2D.draw(courbeDroite);
				//closedCurve.append(courbeGauche, true);
				//closedCurve.append(courbeDroite, true);
				//closedCurve.lineTo(x[0] + (x[1]- x[0]) / 2, y[0] + (y[1]- y[0]) / 2);
				//closedCurve.closePath();
				//graphics2D.fill(closedCurve);
			}
			g.setColor(Color.gray);
			for(float j = 0 ; j < NB_BANDE ; j+=2) {
				y[0] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				x[0] = (int)(- etat.getFacteurElargissement(y[0])/50.0 + p1.x + (p2.x - p1.x) / NB_BANDE * j);
				y[1] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * (j+1));
				x[1] = (int)(- etat.getFacteurElargissement(y[1])/50.0 + p1.x + (p2.x - p1.x) / NB_BANDE * (j+1));
				y[2] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * (j+1));
				x[2] = (int)(+ etat.getFacteurElargissement(y[2])/50.0 + p1.x + (p2.x - p1.x) / NB_BANDE * (j+1));
				y[3] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				x[3] = (int)(+ etat.getFacteurElargissement(y[3])/50.0 + p1.x + (p2.x - p1.x) / NB_BANDE * j);
				y[4] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				x[4] = (int)(- etat.getFacteurElargissement(y[4])/50.0 + p1.x + (p2.x - p1.x) / NB_BANDE * j);
				graphics2D.fillPolygon(x, y, x.length);
			}
		}

		//Suppresion de la route au dessus de l'horizon
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());

		graphics2D.drawImage(montagneImage, LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, LARGEUR + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, -50 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);

		//Affichage du joueur
		g.setColor(C_VAISSEAU);
		g.fillOval(etat.getPlayerX(), etat.getHauteurJoueur()+etat.getTailleJoueur(), etat.getTailleJoueur(), 20);
		if(etat.isRight()) {
			graphics2D.drawImage(playerRightImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		} else if(etat.isLeft()) {
			graphics2D.drawImage(playerLeftImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		} else {
			graphics2D.drawImage(playerCenterImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		}

		g.setColor(Color.black);
		g.drawString("Score", 20, 20);
		g.drawString(etat.getScore(), 20, 40);

		g.drawString("Vitesse : " + (int)(700 / etat.getFacteurVitesse()) + " km/h", 20, 60);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
