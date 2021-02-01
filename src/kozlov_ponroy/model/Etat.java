package kozlov_ponroy.model;

import kozlov_ponroy.control.KeyboardController;
import kozlov_ponroy.view.Affichage;

public class Etat {

	public static final int PLAYER_Y = 0;

	private int playerX = 0;
	private final int HORIZON = 100;
	private Affichage affichage;
	private KeyboardController controller;

	public Etat(Affichage affichage, KeyboardController controller) {
		this.affichage = affichage;
		this.controller = controller;
	}

	public int getHorizon() {
		return HORIZON;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		if(this.playerX<0) {
			playerX=0;
		}
		this.playerX = playerX;
	}


}
