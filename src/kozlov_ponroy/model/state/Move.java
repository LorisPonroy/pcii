package kozlov_ponroy.model.state;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;

public class Move {

	private boolean up = false, down = false, right = false, left = false;
	private final int moveDown = 4;
	private final int moveRight = 5;
	private final int moveLeft = -5;
	private final int moveUp = -2;
	
	private final Player player;
	private final Etat etat;
	
	public Move(Player player, Etat etat) {
		this.player = player;
		this.etat = etat;
	}
	
	/**
	 * Vérifie la position du joueur sur l'axe horizontal et ajoute deltaX à sa position X
	 * @param deltaX
	 */
	private void addPositionX(int deltaX) {
		if(player.getX() + deltaX < Affichage.LARGEUR && player.getX() + deltaX > - player.TAILLE / 2) {
			player.addX((int) (deltaX * (1.0 / etat.getFacteurVitesse())) + 1);
		}
	}
	
	private void minusPositionX(int deltaX) {
		if(player.getX() + deltaX < Affichage.LARGEUR && player.getX() + deltaX > - player.TAILLE / 2) {
			player.addX((int) (deltaX * (1.0 / etat.getFacteurVitesse())) - 1);
		}
	}

	/**
	 * Vérifie la position du joueur sur l'axe vertical et ajoute deltaY à sa position Y
	 * @param deltaY
	 */
	private void addPositionY(int deltaY) {
		if(player.getY() + deltaY < Affichage.HAUTEUR && player.getY() + deltaY > Etat.HORIZON + player.TAILLE / 2) {
			player.addY(deltaY);
		}
	}
	
	public void doMove() {
		if(left ^ right) {
			if(left) {
				minusPositionX(moveLeft);
			} else {
				addPositionX(moveRight);
			}
		}
		/*if(up) {
			addPositionY(moveUp);
		} else if(down){
			addPositionY(moveDown);
		}*/
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public boolean isDown() {
		return down;
	}
	
}
