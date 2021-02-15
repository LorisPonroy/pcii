package kozlov_ponroy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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

	public Affichage(KeyListener listener){
		// Initialise la taille de la fenetre au lancement
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		addKeyListener(listener);
		setFocusable(true);
		x = new int[5];
		y = new int[5];
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.BLACK);
		//Affichage de la ligne d'horizon
		g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());

		//Affichage de la route
		g.setColor(Color.gray);
		for(int i = 0 ; i < etat.getRoute().size() - 1 ; i++)
		{
			p1 = etat.getRoute().get(i);
			p2 = etat.getRoute().get(i+1);
			x[0] = p1.x; y[0] = p1.y;
			x[1] = p2.x; y[1] = p2.y;
			x[2] = p2.x + etat.getLargeurRoute(p2); y[2] = p2.y;
			x[3] = p1.x + etat.getLargeurRoute(p1); y[3] = p1.y;
			x[4] = p1.x; y[4] = p1.y;
			g.fillPolygon(x, y, x.length);
		}
		//Affichage joueur
		//g.setColor(Color.green);
		//g.fillOval(etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur());
		final Image img = Toolkit.getDefaultToolkit().getImage("./ressources/player.png");
		g.drawImage(img, etat.getPlayerX(), etat.getPlayerY(), 100, 100, null);

		//Suppresion de la route au dessus de l'horizon
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());

		g.setColor(Color.black);
		g.drawString("Score", 20, 20);
		g.drawString(etat.getScore(), 20, 40);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
