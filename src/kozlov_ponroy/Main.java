package kozlov_ponroy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;

public class Main {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Exo 6");
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Affichage aff = new Affichage();
		KeyboardController controller = new KeyboardController();
		Etat etat = new Etat(aff,controller);
		controller.setEtat(etat);
		aff.setEtat(etat);
		fenetre.add(aff);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}

}
