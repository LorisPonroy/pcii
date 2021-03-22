package kozlov_ponroy.model.decor.objects;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import kozlov_ponroy.model.decor.DecorPreview;

public class Nuage extends DecorPreview {
	
	final static Image[] img = {Toolkit.getDefaultToolkit().getImage("./ressources/nuage_1_pix.png"),
			Toolkit.getDefaultToolkit().getImage("./ressources/nuage_2_pix.png")};

	public Nuage(int x, int y) {
		super(img[new Random().nextInt(img.length)], x, y, 300, 100, 1);
	}

}
