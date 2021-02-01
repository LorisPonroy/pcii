package kozlov_ponroy;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;

public class Main {
	
	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Exo 6");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Affichage aff = new Affichage();
        Etat etat = new Etat();
        fenetre.add(aff);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
	}

}
