package kozlov_ponroy.model.obstacle.objects;

import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.obstacle.ObstaclePreview;

public class Oil extends ObstaclePreview {
	
	private final static Image img = Toolkit.getDefaultToolkit().getImage("./ressources/oil.png");
	public final static int LARGEUR = 150;
	
	public Oil() {
		super(img, LARGEUR);
	}
	
	@Override
	public boolean isMoto() {
		return false;
	}


}
