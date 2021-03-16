package kozlov_ponroy.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.Etat;

public class AffichageMenu extends JPanel{

	public static final int HAUTEUR = 800;
	public static final int LARGEUR = 800;

	private JFrame fenetre;

	private final Image titre;

	public AffichageMenu(JFrame fenetre) {
		this.fenetre = fenetre;
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		setFocusable(true);

		titre = Toolkit.getDefaultToolkit().getImage("./ressources/nuage_1.png");
		addButtons();
	}

	public void addButtons() {
		Button playButton = new Button();
		playButton.setLabel("Play");
		playButton.setBackground(Color.red);
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Affichage aff = new Affichage();
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().invalidate();
				fenetre.getContentPane().add(aff);
				fenetre.getContentPane().revalidate();
				fenetre.pack();
				fenetre.setLocationRelativeTo(null);
				KeyboardController controller = new KeyboardController();
				new Etat(aff, controller);
				System.err.println("start");
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(playButton);

		Button exitButton = new Button();
		exitButton.setLabel("Exit");
		exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fenetre.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(exitButton);
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);

	}
}
