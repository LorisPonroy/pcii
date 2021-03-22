package kozlov_ponroy.model.decor;

import java.awt.Image;
import java.util.Random;

public class DecorPreview {
	
	public int x, y;
	public final int speed;
	public final Image img;
	public final int hauteur, largeur;
	public final boolean isLeft;
	
	public DecorPreview(Image img, int x, int y, int largeur, int hauteur, int speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.speed = speed;
		isLeft = new Random().nextBoolean();
	}

	public String toString() {
		return "x: " + x + " ; y : " + y;
	}
}
