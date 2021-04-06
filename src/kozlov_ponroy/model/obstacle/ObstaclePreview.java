package kozlov_ponroy.model.obstacle;

import java.awt.Image;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.route.Route;
import kozlov_ponroy.model.state.Player;

public abstract class ObstaclePreview {

	public int x, x1, x2, y1, y2;
	public final Image img;
	public int speed = 3;
	public final int largeur;
	final Random random;
	final int MARGE_RANDOM = 300;

	public ObstaclePreview(Image img, int largeur) {
		this.img = img;
		this.random = new Random();
		this.y1 = y2 = -random.nextInt(2000);
		this.x = Etat.LARGEUR / 2 + random.nextInt(MARGE_RANDOM) - MARGE_RANDOM / 2;
		this.largeur = largeur;
	}
	
	public void move(Etat etat) {
		y1 += Route.MOVE;
		x1 = etat.transformePositionToPerspective(x,y1);
		x2 = etat.transformePositionToPerspective(x + largeur,y1);
		y2 = x2 - x1 + y1;
		collide(etat, etat.getPlayer());
	}

	public boolean canRemove() {
		return y1 > Etat.HAUTEUR ? true : false;
	}

	public boolean collide(Etat etat, Player player) {
		int x1p = etat.getPlayerX() - Player.LARGEUR / 2;
		int y1p = etat.getPlayerY() - Player.HAUTEUR / 4;
		int x2p = etat.getPlayerX() + Player.LARGEUR / 2;
		int y2p = etat.getPlayerY();
		if(x1 < x1p && x1p < x2 || x1 < x2p && x2p < x2) {
			if(y1 < y1p && y1p < y2 || y1 < y2p && y2p < y2) {
				etat.collide(isMoto());
			}
		}
		return false;
	}


	@Override
	public String toString() {
		return getClass().getName() + " x: " + x1 + ":" + x2 + " ; y : " + y1 + ":" + y2;
	}
	
	public abstract boolean isMoto();

}
