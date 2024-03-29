package kozlov_ponroy.model.state;

import kozlov_ponroy.model.Etat;

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
	 * V�rifie la position du joueur sur l'axe horizontal et ajoute deltaX � sa position X
	 * @param deltaX
	 */
	private void addPositionX(int deltaX) {
		if(player.getX() + deltaX < Etat.LARGEUR && player.getX() + deltaX > - player.HAUTEUR / 2) {
			player.addX((int) (deltaX * (1.0 / etat.getFacteurVitesse())) + 1);
		}
	}

	/**
	 * V�rifie la position du joueur sur l'axe vertical et ajoute deltaY � sa position Y
	 * @param deltaY
	 */
	/*private void addPositionY(int deltaY) {
		if(player.getY() + deltaY < Etat.HAUTEUR && player.getY() + deltaY > Etat.HORIZON + player.TAILLE / 2) {
			player.addY(deltaY);
		}
	}*/

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

	private void minusPositionX(int deltaX) {
		if(player.getX() + deltaX < Etat.LARGEUR && player.getX() + deltaX > - player.HAUTEUR / 2) {
			player.addX((int) (deltaX * (1.0 / etat.getFacteurVitesse())) - 1);
		}
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

}
