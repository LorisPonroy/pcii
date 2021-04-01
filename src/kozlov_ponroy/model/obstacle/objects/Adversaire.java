package kozlov_ponroy.model.obstacle.objects;

import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.obstacle.ObstaclePreview;

public class Adversaire extends ObstaclePreview {

	private static Image img = Toolkit.getDefaultToolkit().getImage("./ressources/player_red.png");
	public final static int LARGEUR = 200;
	public final static int HAUTEUR = 400;

	public Adversaire() {
		super(img, LARGEUR,HAUTEUR);
		switch((int)Math.random()*4) {
		case 0:
			img = Toolkit.getDefaultToolkit().getImage("./ressources/player_red.png");
			break;
		case 1:
			img = Toolkit.getDefaultToolkit().getImage("./ressources/player_yellow.png");
			break;
		case 2:
			img = Toolkit.getDefaultToolkit().getImage("./ressources/player_green.png");
			break;
		case 3:
			break;
		default:
			break;
		}

		speed = (int) (Math.random()*3);
	}

}
