package kozlov_ponroy.model.state;

import java.awt.Point;

public class Player {

	public final int TAILLE = 180;
	public final int HAUTEUR = 550;
	
	private Point position;
	
	public Player(Point position) {
		this.position = position;
	}
	
	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}
	
	public void addX(int deltaX) {
		position.x += deltaX;
	}
	
	public void addY(int deltaY) {
		position.y += deltaY;
	}
	
	public Point getPosition() {
		return position;
	}

}
