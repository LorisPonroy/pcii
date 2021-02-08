package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cr�ation de la route
 * @author Asey
 *
 */
public class Route {
	
	private ArrayList<Point> points;
	private final int ESPACEMENT = 150;
	private final int MARGE_RANDOM = 300;
	private int position;
	private final int X_MAX, Y_MAX, LARGEUR_ROUTE;
	private final int PX_PAS = 5;
	private Random random;

	public Route(int x_max, int y_max, int largeurRoute){
		this.X_MAX = x_max;
		this.Y_MAX = y_max;
		this.LARGEUR_ROUTE = largeurRoute;
		points = new ArrayList<>();
		random = new Random();
		position = 0;
		points.add(new Point(random.nextInt(x_max), 0));
		while(lastY() <= Y_MAX) {
			ajouterPoint();
		}
	}
	
	private void ajouterPoint() {
		int x = lastX() + random.nextInt(MARGE_RANDOM) - MARGE_RANDOM / 2;
		if(x < 0){
			x = random.nextInt(MARGE_RANDOM);
		}
		else if(x > X_MAX - LARGEUR_ROUTE) {
			x = X_MAX - random.nextInt(MARGE_RANDOM) - LARGEUR_ROUTE;
		}
		Point p = new Point(x, lastY() + ESPACEMENT);
		System.out.println(p.toString());
		points.add(p);
	}
	
	private int lastX() {
		return points.get(points.size() - 1).x;
	}
	
	private int lastY() {
		return points.get(points.size() - 1).y;
	}
	
	public void setPosition() {
		if(position > 0 && position % ESPACEMENT == 0) {
			ajouterPoint();
			points.remove(0);
		}
		position += PX_PAS;
	}
	
	public int getPosition() {
		return position;
	}
	
	/**
	 * Renvoie tous les points qui constitue la route
	 * @return
	 */
	public ArrayList<Point> getRoute() {
		ArrayList<Point> temp = new ArrayList<>();
		for(Point p: points) {
			temp.add(new Point(p.x, p.y - position));
		}
		return temp;
	}
}
