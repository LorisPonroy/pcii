package kozlov_ponroy.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import kozlov_ponroy.model.Etat;

/**
 * Gère l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	public final int HAUTEUR = 400;
	public final int LARGEUR = 800;
	private Etat etat;

	public Affichage(KeyListener listener){
		/** Initialise la taille de la fenetre au lancement*/
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		addKeyListener(listener);
		setFocusable(true);
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		/** Affichage de la ligne d'horizon*/
		g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());
		
		/**Affichage joueur*/
		g.fillOval(etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur());
		
		/**Affichage de la route*/
		for(int i = 0 ; i < etat.getRoute().size() - 1 ; i++)
		{
			g.drawLine(etat.getRoute().get(i).x, etat.getRoute().get(i).y, etat.getRoute().get(i+1).x, etat.getRoute().get(i+1).y);
		}
		/**Suppresion de la route au dessus de l'horizon*/
		g.clearRect(0, 0, LARGEUR, etat.getHorizon());
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
