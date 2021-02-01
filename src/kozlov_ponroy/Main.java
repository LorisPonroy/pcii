package kozlov_ponroy;

import javax.swing.JFrame;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;

public class Main {
	
	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Exo 6");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Etat etat = new Etat();
        Affichage aff = new Affichage();
        aff.setEtat(etat);
        fenetre.add(aff);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
	}

}
