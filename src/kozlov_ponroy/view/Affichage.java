package kozlov_ponroy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.route.Obstacle;

/**
 * G�re l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	public static final int HAUTEUR = 800;
	public static final int LARGEUR = 800;
	List<IAffichage> views;
	private Graphics2D graphics2D;

	public Affichage(KeyListener listener){
		/**
		 *  Initialise la taille de la fenetre au lancement
		 */
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		addKeyListener(listener);
		setFocusable(true);
		
		views = new ArrayList<>();
	}

	/**
	 * Appel de la fonction paint pour effacer l'ensemble de la fenetre puis dessiner les �l�ments
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);

		/**
		 * Recuperation graphique et antialiasing on
		 */
		graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, LARGEUR, HAUTEUR);
		
		for(IAffichage view : views) {
			view.setGraphics2D(graphics2D);
			view.paint(g);
		}
	}
	
	public void addViews(List<IAffichage> list) {
		views.addAll(list);
	}

}
