package kozlov_ponroy.model;

public class Etat {
	public static final int PLAYER_Y = 0;

	private int playerX = 0;
	private final int HORIZON = 100;

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		if(this.playerX<0) {
			playerX=0;
		}
		this.playerX = playerX;
	}
	
	public int getHorizon() {
		return HORIZON;
	}


}
