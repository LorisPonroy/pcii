package kozlov_ponroy.model.route;

public class Obstacle {
	private int x;
	private int y;

	private int hauteur;

	public Obstacle(int x,int y){
		this.x = x;
		this.y = y;
		hauteur = (int) (Math.random()*2 + 2);
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}




}
