package kozlov_ponroy.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import kozlov_ponroy.model.Etat;

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
		g.drawLine(0, Etat.HORIZON, LARGEUR, Etat.HORIZON);
		g.fillOval(etat.getPlayerX(), etat.getPlayerY(), 10, 10);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
}
