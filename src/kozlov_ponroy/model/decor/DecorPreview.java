package kozlov_ponroy.model.decor;

import java.awt.Image;
import java.util.Random;

import kozlov_ponroy.model.Etat;

public abstract class DecorPreview {
	
	public int x, y;
	public final int speed;
	public final Image img;
	public final int hauteur, largeur;
	public final boolean isGoingLeft;
	
	public DecorPreview(Image img, int largeur, int hauteur, boolean isGoingLeft) {
		this.img = img;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.isGoingLeft = isGoingLeft;
		Random r = new Random();
		this.y = r.nextInt(Etat.HORIZON) - hauteur;
		if(isGoingLeft) {
			this.x = Etat.LARGEUR;
			this.speed = - r.nextInt(5) + 1;
		} else {
			this.x = -largeur;
			this.speed = r.nextInt(5) + 1;
		}
	}

	public String toString() {
		return getClass().getName() + " x: " + x + " ; y : " + y;
	}
}
