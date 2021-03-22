package kozlov_ponroy.model.decor.objects;

import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.decor.DecorPreview;

public class OiseauDroite extends DecorPreview {
	
	final static int speed = 5;
	final static Image img = Toolkit.getDefaultToolkit().getImage("./ressources/oiseau_droite.gif");

	public OiseauDroite(int x, int y) {
		super(img, x, y, 20, 20, speed);
	}
}
