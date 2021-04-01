package kozlov_ponroy.model.obstacle;

import java.awt.Image;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.state.Player;

public abstract class ObstaclePreview {
	
	public int x, x1, x2, y1, y2;
	public final Image img;
	public final int speed = 3;
	public final int largeur;
	
	public ObstaclePreview(Image img, int largeur) {
		this.img = img;
		this.y1 = y2 = 0;
		this.x1 = x2 = x = Etat.LARGEUR / 2;
		this.largeur = largeur;
	}
	
	public void move(Etat etat) {
		y1 += speed;
		x1 = etat.transformePositionToPerspective(x,y1);
		x2 = etat.transformePositionToPerspective(x + largeur,y1);
		y2 = x2 - x + y1;
		collide(etat, etat.getPlayer());
	}
	
	public boolean collide(Etat etat, Player player) {
		int x1p = etat.getPlayerX() - Player.LARGEUR / 2;
		int y1p = etat.getPlayerY() - Player.HAUTEUR / 4;
		int x2p = etat.getPlayerX() + Player.LARGEUR;
		int y2p = etat.getPlayerY();
		/*System.out.println("---------------------------------------------");
		System.out.println("player x: " + x1p + ":" + x2p + " ; y:" + y1p + ":" + y2p);
		System.out.println("oil x: " + x1 + ":" + x2 + " ; y:" + y1 + ":" + y2);*/
		if(x1 < x1p && x1p < x2 || x1 < x2p && x2p < x2) {
			if(y1 < y1p && y1p < y2 || y1 < y2p && y2p < y2) {
				//System.out.println("COLLISION");
				etat.collide();
			}
		}
		return false;
	}
	
	public String toString() {
		return getClass().getName() + " x: " + x1 + ":" + x2 + " ; y : " + y1 + ":" + y2;
	}
	
	public boolean canRemove() {
		return y1 > 500 ? true : false;
	}

}
