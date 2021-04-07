package kozlov_ponroy.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.Etat;

public class AffichageMenu extends JPanel{

	public static final int HAUTEUR = 500;
	public static final int LARGEUR = 800;

	private JFrame fenetre;

	public AffichageMenu(JFrame fenetre) {
		this.fenetre = fenetre;
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		setFocusable(true);
		addButtons();
		fenetre.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		} );
	}

	public void addButtons() {
		Button playButton = new Button();
		playButton.setLabel("Play");
		playButton.setBackground(Color.WHITE);
		playButton.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				KeyboardController controller = new KeyboardController();
				Affichage aff = new Affichage();
				Etat etat = new Etat(aff, controller);
				aff.setEtat(etat);
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
					
					@Override
					public void windowClosed(WindowEvent e) {
						super.windowClosed(e);
						fenetre.setVisible(true);
					}
				} );
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
		exitButton.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		exitButton.setBackground(Color.RED);
		exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		Image fond = Toolkit.getDefaultToolkit().getImage("./ressources/fond_menu.png");
		g.drawImage(fond, 0, 0,LARGEUR,HAUTEUR,null);
		System.out.println("PAINT");
	}
}
