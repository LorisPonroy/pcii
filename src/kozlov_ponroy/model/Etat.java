package kozlov_ponroy.model;

import java.awt.Point;

import kozlov_ponroy.view.Affichage;

public class Etat {

	public static final int PLAYER_Y = 0;

	private Point playerPosition;
	private final int HORIZON = 100;
	private Affichage affichage;
	private final int moveDown = 5;
	private final int moveRight = 5;
	private final int moveLeft = -5;
	private final int moveUp = -5;

	public Etat(Affichage affichage) {
		this.affichage = affichage;
		playerPosition = new Point(this.affichage.getWidth() / 2, this.affichage.getHeight() / 2);
	}

	public int getHorizon() {
		return HORIZON;
	}

	public int getPlayerX() {
		System.out.println("X = " + playerPosition.x);
		return playerPosition.x;
	}
	
	public int getPlayerY() {
		System.out.println("Y = " + playerPosition.y);
		return playerPosition.y;
	}
	
	private void setPositionX(int deltaX) {
		int x = getPlayerX();
		if(x + deltaX >= 0 || x + deltaX <= affichage.getWidth()) {
			playerPosition.x += deltaX;
		}
	}
	
	private void setPositionY(int deltaY) {
		int y = getPlayerY();
		if(y + deltaY >= 0 || y + deltaY <= affichage.getHeight()) {
			playerPosition.y += deltaY;
		}
	}

	public void goUp() {
		setPositionY(moveUp);
	}

	public void goDown() {
		setPositionY(moveDown);
	}
	
	public void goLeft() {
		setPositionX(moveLeft);
	}
	
	public void goRight() {
		setPositionX(moveRight);
	}
}
