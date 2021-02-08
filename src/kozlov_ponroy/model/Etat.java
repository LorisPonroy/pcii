package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;

import kozlov_ponroy.view.Affichage;

/**
 * Gère l'état de notre MVC
 * 
 * @author Asey
 *
 */

public class Etat {

	private final int HORIZON = 100;
	private final int TAILLE_JOUEUR = 20;
	
	private final int moveDown = 5;
	private final int moveRight = 5;
	private final int moveLeft = -5;
	private final int moveUp = -5;
	
	private boolean up = false, down = false, right = false, left = false;

	private Point playerPosition;
	private Affichage affichage;
	private Route route;

	public Etat(Affichage affichage) {
		this.affichage = affichage;
		playerPosition = new Point(affichage.LARGEUR / 2, affichage.HAUTEUR / 2);
		route = new Route();
		(new Mouvement(this)).start();
	}

	/**
	 * Vérifie la position du joueur sur l'axe horizontal et ajoute deltaX à sa position X
	 * @param deltaX
	 */
	private void addPositionX(int deltaX) {
		if(playerPosition.x + deltaX < affichage.getWidth() && playerPosition.x + deltaX > - TAILLE_JOUEUR / 2) {
			playerPosition.x += deltaX;
		}
	}

	/**
	 * Vérifie la position du joueur sur l'axe vertical et ajoute deltaY à sa position Y
	 * @param deltaY
	 */
	private void addPositionY(int deltaY) {
		if(playerPosition.y + deltaY < affichage.getHeight() && playerPosition.y + deltaY > HORIZON - TAILLE_JOUEUR / 2) {
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
	
	public void setAffichage(Affichage affichage) {
		this.affichage = affichage;
	}
	
	/**
	 * Renvoie les points de la route dans le sens inversé
	 * @return
	 */
	public ArrayList<Point> getRoute(){
		ArrayList<Point> temp = new ArrayList<>();
		for(Point p : route.getRoute())
		{
			temp.add(new Point(p.x, affichage.HAUTEUR - p.y));
		}
		return temp;
	}
	
	public int getHorizon() {
		return HORIZON;
	}
	
	public int getTailleJoueur() {
		return TAILLE_JOUEUR;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void move() {
		if(left && !right) {
			addPositionX(moveLeft);
		}
		else if(right) {
			addPositionX(moveRight);
		}
		if(up && !down) {
			addPositionY(moveUp);
		}
		else if(down) {
			addPositionY(moveDown);
		}
		affichage.repaint();
	}
}
