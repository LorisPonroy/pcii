package kozlov_ponroy.model;

import java.awt.Point;

import kozlov_ponroy.view.Affichage;

public class Etat {

	public static final int HORIZON = 100;

	public static final int moveDown = 5;
	public static final int moveRight = 5;
	public static final int moveLeft = -5;
	public static final int moveUp = -5;

	private Point playerPosition;
	private Affichage affichage;

	public Etat(Affichage affichage) {
		this.affichage = affichage;
		playerPosition = new Point(this.affichage.getWidth() / 2, this.affichage.getHeight() / 2);
	}

	private void addPositionX(int deltaX) {
		if(playerPosition.x + deltaX<affichage.getWidth() && playerPosition.x + deltaX > 0) {
			playerPosition.x += deltaX;
		}
	}

	private void addPositionY(int deltaY) {
		if(playerPosition.y + deltaY<affichage.getHeight() && playerPosition.y + deltaY > 0) {
			playerPosition.y += deltaY;
		}
	}
	
	public Affichage getAffichage() {
		return affichage;
	}

	public int getPlayerX() {
		return playerPosition.x;
	}

	public int getPlayerY() {
		return playerPosition.y;
	}
	
	public void goUp() {
		addPositionY(moveUp);
		affichage.repaint();
	}

	public void goDown() {
		addPositionY(moveDown);
		affichage.repaint();
	}

	public void goLeft() {
		addPositionX(moveLeft);
		affichage.repaint();
	}

	public void goRight() {
		addPositionX(moveRight);
		affichage.repaint();
	}


	public void setAffichage(Affichage affichage) {
		this.affichage = affichage;
	}
}
