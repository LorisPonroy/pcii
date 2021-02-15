package kozlov_ponroy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyListener;

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
	private Point p1, p2;
	private int[] x;
	private int[] y;
	Graphics2D graphics2D;
	final private Image playerLeftImage;
	final private Image playerRightImage;
	final private Image playerCenterImage;
	final private Image treeImage;
	final private Image montagneImage;

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
			p1 = etat.getRoute().get(i);
			p2 = etat.getRoute().get(i+1);
			x[0] = p1.x - etat.getLargeurRoute(p1) / 2; y[0] = p1.y;
			x[1] = p2.x - etat.getLargeurRoute(p2) / 2; y[1] = p2.y;
			x[2] = p2.x + etat.getLargeurRoute(p2) / 2; y[2] = p2.y;
			x[3] = p1.x + etat.getLargeurRoute(p1) / 2; y[3] = p1.y;
			x[4] = p1.x; y[4] = p1.y;
			graphics2D.fillPolygon(x, y, x.length);
		}
		//Affichage joueur
		//g.setColor(Color.green);
		//g.fillOval(etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur());

		if(etat.isRight()) {
			graphics2D.drawImage(playerRightImage, etat.getPlayerX(), etat.getPlayerY(), 100, 100, null);
		} else if(etat.isLeft()) {
			graphics2D.drawImage(playerLeftImage, etat.getPlayerX(), etat.getPlayerY(), 100, 100, null);
		} else {
			graphics2D.drawImage(playerCenterImage, etat.getPlayerX(), etat.getPlayerY(), 100, 100, null);
		}

		//Suppresion de la route au dessus de l'horizon
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());

		//graphics2D.drawImage(treeImage, this.LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 120, 120, 120, null);
		graphics2D.drawImage(montagneImage, LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);

		g.setColor(Color.black);
		g.drawString("Score", 20, 20);
		g.drawString(etat.getScore(), 20, 40);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
