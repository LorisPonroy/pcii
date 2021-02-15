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

	private final int HORIZON = 250;
	private final int TAILLE_JOUEUR = 20;
	private final int LARGEUR_ROUTE = 100;

	private final int moveDown = 5;
	private final int moveRight = 5;
	private final int moveLeft = -5;
	private final int moveUp = -5;

	private boolean up = false, down = false, right = false, left = false;

	private Point playerPosition;
	private Affichage affichage;
	private Route route;
	private int positionDecor;

	public Etat(Affichage affichage) {
		this.affichage = affichage;
		playerPosition = new Point(affichage.LARGEUR / 2, affichage.HAUTEUR / 2);
		route = new Route(affichage.LARGEUR, affichage.HAUTEUR, LARGEUR_ROUTE);
		new MouvementVehicule(this).start();
		new MouvementRoute(this).start();
		this.positionDecor = 0;
	}

	/**
	 * Vérifie la position du joueur sur l'axe horizontal et ajoute deltaX à sa position X
	 * @param deltaX
	 */
	private void addPositionX(int deltaX) {
		if(playerPosition.x + deltaX < affichage.getWidth() && playerPosition.x + deltaX > - TAILLE_JOUEUR / 2) {
			playerPosition.x += deltaX;
			positionDecor -= deltaX / 4;
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

	public void avancerRoute() {
		route.setPosition();
	}

	public Affichage getAffichage() {
		return affichage;
	}

	public int getHorizon() {
		return HORIZON;
	}

	public int getLargeurRoute(Point p) {
		return (int) (HORIZON/799.0*p.y - HORIZON/799.0);
	}

	public int getPlayerX() {
		return playerPosition.x;
	}

	public int getPlayerY() {
		return playerPosition.y;
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

	public String getScore() {
		return "" + route.getPosition();
	}

	public int getTailleJoueur() {
		return TAILLE_JOUEUR;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isUp() {
		return up;
	}

	public void move() {
		if(left ^ right) {
			if(left) {
				addPositionX(moveLeft);
			} else {
				addPositionX(moveRight);
			}
		}
		if(up ^ down) {
			if(up) {
				addPositionY(moveUp);
			} else {
				addPositionY(moveDown);
			}
		}
		affichage.repaint();
	}

	public void setAffichage(Affichage affichage) {
		this.affichage = affichage;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
	
	public int getPositionDecor() {
		return positionDecor;
	}
}
