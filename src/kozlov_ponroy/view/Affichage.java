package kozlov_ponroy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import kozlov_ponroy.model.Etat;

/**
 * Gère l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	List<IAffichage> views;
	private Graphics2D graphics2D;

	public Affichage(){
		/**
		 *  Initialise la taille de la fenetre au lancement
		 */
		setPreferredSize(new Dimension(Etat.LARGEUR, Etat.HAUTEUR));
		//addKeyListener(listener);
		setFocusable(true);

		views = new ArrayList<>();
	}

	public void addViews(List<IAffichage> list) {
		views.addAll(list);
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
		graphics2D.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Etat.LARGEUR, Etat.HAUTEUR);

		for(IAffichage view : views) {
			view.setGraphics2D(graphics2D);
			view.paint(g);
		}
	}

}
