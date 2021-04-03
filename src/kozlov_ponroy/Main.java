package kozlov_ponroy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import kozlov_ponroy.view.AffichageMenu;


/**
 * Configure la fenetre ainsi que les composants, modèle, controlleur
 * @author Asey
 */

public class Main {

	public static void main(String[] args) {

		JFrame fenetre = new JFrame("Course");
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		AffichageMenu affMenu = new AffichageMenu(fenetre);
		fenetre.add(affMenu);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		fenetre.setResizable(false);
		fenetre.repaint();
	}

}
