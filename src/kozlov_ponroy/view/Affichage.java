package kozlov_ponroy.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import kozlov_ponroy.model.Etat;

public class Affichage extends JPanel{

	private final int HAUTEUR = 400;
    private final int LARGEUR = 800;
    private Etat etat;
    
    public Affichage(KeyListener listener){
        /** Initialise la taille de la fenetre au lancement*/
        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        addKeyListener(listener);
        setFocusable(true);
    }
    
    public void setEtat(Etat etat) {
    	this.etat = etat;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(0, etat.getHorizon(), LARGEUR, etat.getHorizon());
    }
}
