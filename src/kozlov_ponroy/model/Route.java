package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Création de la route
 * @author Asey
 *
 */
public class Route {
	
	private ArrayList<Point> points;
	private final int ESPACEMENT = 150;
	private int position;
	private final int X_MAX, Y_MAX;
	private final int PX_PAS = 5;
	private Random random;

	public Route(int x_max, int y_max){
		this.X_MAX = x_max;
		this.Y_MAX = y_max;
		points = new ArrayList<>();
		random = new Random();
		position = 0;
		points.add(new Point(random.nextInt(x_max), 0));
		while(lastY() <= Y_MAX) {
			ajouterPoint();
		}
	}
	
	private void ajouterPoint() {
		Point p = new Point(random.nextInt(X_MAX), lastY() + ESPACEMENT);
		System.out.println(p.toString());
		points.add(p);
	}
	
	private int lastY() {
		return points.get(points.size() - 1).y;
	}
	
	public void setPosition() {
		if(position > 0 && position % ESPACEMENT == 0) {
			ajouterPoint();
			System.out.println("ajouter point");
			points.remove(0);
		}
		position += PX_PAS;
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
