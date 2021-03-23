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
import kozlov_ponroy.view.objects.PlayerView;
import kozlov_ponroy.view.objects.PlayerView.PlayerState;

/**
 * G�re l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	List<IView> views;
	private Graphics2D graphics2D;
	final Color sand;

	public Affichage(){
		/**
		 *  Initialise la taille de la fenetre au lancement
		 */
		setPreferredSize(new Dimension(Etat.LARGEUR, Etat.HAUTEUR));
		setFocusable(true);

		views = new ArrayList<>();
		sand = new Color(242,209,107);
	}

	public void addViews(List<IView> list) {
		views.addAll(list);
	}

	public void doABarrelRoll() {
		Thread thread = new Thread(() -> {
			PlayerView.state = PlayerState.ROLL_LEFT;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {System.err.println(e);}
			PlayerView.state = PlayerState.ROLL_RIGTH;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {System.err.println(e);}
			PlayerView.state = PlayerState.NORMAL;
		});
		thread.start();
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

		g.setColor(sand);
		g.fillRect(0, Etat.HORIZON, Etat.LARGEUR, Etat.HAUTEUR);

		for(IView view : views) {
			view.setGraphics2D(graphics2D);
			view.paint(g);
		}
	}

}
