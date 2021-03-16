package kozlov_ponroy.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.Etat;

public class AffichageMenu extends JPanel{

	public static final int HAUTEUR = 200;
	public static final int LARGEUR = 200;

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
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				KeyboardController controller = new KeyboardController();
				Affichage aff = new Affichage(controller);
				new Etat(aff, controller);
				frame.getContentPane().add(aff);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				fenetre.setVisible(false);
				frame.addWindowListener( new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                    	frame.dispose();
                        fenetre.setVisible(true);
                    }
                } );
				System.out.println("start");
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
