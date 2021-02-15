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
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

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
	final float NB_BANDE = 6;
	final Color C_VAISSEAU;
	QuadCurve2D courbe = new QuadCurve2D.Double();
	Point2D debut;
	Point2D ctrl;
	Point2D fin;

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
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.BLACK);
		//Affichage de la ligne d'horizon
		g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());

		//Affichage de la route
		g.setColor(Color.gray);
		graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i = 0 ; i < etat.getRoute().size() - 1 ; i++)
		{
			g.setColor(Color.gray);
			p1 = etat.getRoute().get(i);
			p2 = etat.getRoute().get(i+1);

			x[0] = p1.x - etat.getLargeurRoute(p1) / 2; y[0] = p1.y;
			x[1] = p2.x - etat.getLargeurRoute(p2) / 2; y[1] = p2.y;
			x[2] = p2.x + etat.getLargeurRoute(p2) / 2; y[2] = p2.y;
			x[3] = p1.x + etat.getLargeurRoute(p1) / 2; y[3] = p1.y;
			x[4] = p1.x; y[4] = p1.y;
			//graphics2D.fillPolygon(x, y, x.length);


			if(i < etat.getRoute().size() - 2) {
				g.setColor(Color.RED);
				graphics2D.setStroke(new BasicStroke(10));
				p3 = etat.getRoute().get(i+2);
				x[2] = p3.x - etat.getLargeurRoute(p3) / 2;	y[2] = p3.y;
				debut = new Point2D.Double(x[0] + (x[1]- x[0]) / 2, y[0] + (y[1]- y[0]) / 2);
				ctrl = new Point2D.Double(x[1], y[1]);
				fin = new Point2D.Double(x[1] + (x[2]- x[1]) / 2, y[1] + (y[2]- y[1]) / 2);
				courbe.setCurve(debut,ctrl,fin);
				graphics2D.draw(courbe);
			}
			g.setColor(Color.yellow);
			for(float j = 0 ; j < NB_BANDE ; j+=2) {
				x[0] = (int)(- 5 + p1.x + (p2.x - p1.x) / NB_BANDE * j); 		y[0] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				x[1] = (int)(- 5 + p1.x + (p2.x - p1.x) / NB_BANDE * (j+1));	y[1] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * (j+1));
				x[2] = (int)(+ 5 + p1.x + (p2.x - p1.x) / NB_BANDE * (j+1)); 	y[2] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * (j+1));
				x[3] = (int)(+ 5 + p1.x + (p2.x - p1.x) / NB_BANDE * j); 	 	y[3] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				x[4] = (int)(- 5 + p1.x + (p2.x - p1.x) / NB_BANDE * j); 		y[4] = (int)(p1.y + (p2.y - p1.y) / NB_BANDE * j);
				graphics2D.fillPolygon(x, y, x.length);
			}
		}

		//Affichage point de fuite (temp)
		g.drawOval(etat.getPOINT_DE_FUITE().x, etat.getPOINT_DE_FUITE().y, 5, 5);

		g.setColor(C_VAISSEAU);
		g.fillOval(etat.getPlayerX(), etat.getHauteurJoueur()+etat.getTailleJoueur(), etat.getTailleJoueur(), 20);
		if(etat.isRight()) {
			graphics2D.drawImage(playerRightImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		} else if(etat.isLeft()) {
			graphics2D.drawImage(playerLeftImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		} else {
			graphics2D.drawImage(playerCenterImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur(), null);
		}

		//Suppresion de la route au dessus de l'horizon
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());

		graphics2D.drawImage(montagneImage, LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, LARGEUR + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, -50 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);


		g.setColor(Color.black);
		g.drawString("Score", 20, 20);
		g.drawString(etat.getScore(), 20, 40);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
