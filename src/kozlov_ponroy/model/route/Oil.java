package kozlov_ponroy.model.route;

import kozlov_ponroy.model.Etat;

public class Oil {
	public static final int LARGEUR_OIL = 250;
	private int x;
	private int y;

	public Oil(int x,int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isInCollision(int playerX, int playerY) {
		int x = Etat.transformePositionToPerspective(this.x,y);
		int x2 = Etat.transformePositionToPerspective(this.x + LARGEUR_OIL,y);
		int width = x2 - x;
		return playerX > x && playerX < x2 && playerY < y && playerY > y-width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}




}
