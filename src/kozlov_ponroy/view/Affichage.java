package kozlov_ponroy.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.objects.PlayerView;
import kozlov_ponroy.view.objects.PlayerView.PlayerState;

/**
 * Gère l'affichage dans le MVC
 * @author Asey
 *
 */
public class Affichage extends JPanel{

	List<IView> views;
	private Graphics2D graphics2D;
	private final Color sand;
	private Etat etat;
	private JButton bMenu;

	public Affichage(){
		/**
		 *  Initialise la taille de la fenetre au lancement
		 */
		setPreferredSize(new Dimension(Etat.LARGEUR, Etat.HAUTEUR));
		setFocusable(true);	
		views = new ArrayList<>();
		sand = new Color(242,209,107);
		bMenu = new JButton("Menu");
		bMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				close();
	        }  
	    });  
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

		g.setColor(sand);
		g.fillRect(0, Etat.HORIZON, Etat.LARGEUR, Etat.HAUTEUR);

		for(IView view : views) {
			view.setGraphics2D(graphics2D);
			view.paint(g);
		}
		if(etat.isGameOver()) {
			graphics2D.setColor(Color.white);
			graphics2D.fillRoundRect(250, 175, 300, 150, 30, 30);
			graphics2D.setColor(Color.black);
			graphics2D.drawString(etat.getScore(), 350, 220);
			graphics2D.setStroke(new BasicStroke(3));
			bMenu.setBounds(350, 240, 100, 30);
			add(bMenu);
		}
	}
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public void close() {
		SwingUtilities.getWindowAncestor(this).dispose();
	}

}
