package kozlov_ponroy.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Création de la route
 * @author Asey
 *
 */
public class Route {
	
	private ArrayList<Point> points;

	public Route(){
		points = new ArrayList<>();
		points.add(new Point(100,0));
		points.add(new Point(400,200));
		points.add(new Point(300,400));
		points.add(new Point(150,600));
	}
	
	/**
	 * Renvoie tous les points qui constitue la route
	 * @return
	 */
	public ArrayList<Point> getRoute() {
		return points;
	}
}
