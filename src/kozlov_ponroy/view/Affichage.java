package kozlov_ponroy.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Affichage extends JPanel{

	private final int HAUTEUR = 400;
    private final int LARGEUR = 800;
    
    public Affichage(){
        /** Initialise la taille de la fenetre au lancement*/
        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
    }
    
    public int getHAUTEUR() {
        return HAUTEUR;
    }

    public int getLARGEUR() {
        return LARGEUR;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }
}
