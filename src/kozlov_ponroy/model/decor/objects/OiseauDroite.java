package kozlov_ponroy.model.decor.objects;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.DecorPreview;

public class OiseauDroite extends DecorPreview {
	
	final static int speed = 5;
	final static Image img = Toolkit.getDefaultToolkit().getImage("./ressources/oiseau_droite.gif");
	final static int TAILLE = 20;

	public OiseauDroite() {
		super(img, TAILLE, TAILLE, false);
	}
}
