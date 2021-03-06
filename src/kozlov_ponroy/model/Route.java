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

	public static final int ESPACEMENT = 400;

	private ArrayList<Point> points;

	private ArrayList<Obstacle> obstacles;
	private final int MARGE_RANDOM = 300;
	private int position;
	private final int X_MAX, Y_MAX, LARGEUR_ROUTE;
	public final int PX_PAS = 5;
	private Random random;

	public Route(int x_max, int y_max, int largeurRoute){
		X_MAX = x_max;
		Y_MAX = y_max;
		LARGEUR_ROUTE = largeurRoute;
		points = new ArrayList<>();
		obstacles = new ArrayList<>();
		random = new Random();
		position = 0;
		points.add(new Point(random.nextInt(x_max), -400));
		while(lastY() <= Y_MAX) {
			ajouterPoint();
		}
	}

	/**
	 * Ajoute un point en fonction du dernier ajouter
	 */
	private void ajouterPoint() {
		//La variable x est genere aleatoirement en fonction du dernier point en x
		int x = lastX() + random.nextInt(MARGE_RANDOM) - MARGE_RANDOM / 2;
		if(x < LARGEUR_ROUTE){ //si x passe en dessous de largeur route alors on genere un nombre strictement positif
			x = random.nextInt(MARGE_RANDOM);
		}
		else if(x > X_MAX - LARGEUR_ROUTE) {
			x = X_MAX - random.nextInt(MARGE_RANDOM);
		}
		Point p = new Point(x, lastY() + ESPACEMENT);
		points.add(p);
	}

	/**
	 * Met a jour la position
	 * Ajoute un point et supprime le dernier lorsque la position est modulo espacement
	 */
	public void avancer() {
		if(position > 0 && position % ESPACEMENT == 0) {
			//System.out.println("position =" + position);
			ajouterPoint();
			if(points.get(0).y - position < -ESPACEMENT) {
				points.remove(0);
				//System.out.println("Point supprimé");
			}
		}
		position += PX_PAS;
		for(Obstacle o : obstacles) {
			o.setY(o.getY() + PX_PAS);
		}
	}

	public void genererObstacle() {
		int x = (int) (Math.random()*X_MAX);
		int y = 0;
		obstacles.add(new Obstacle(x, y));
	}

	/** TODO: refaire la fonction pour renvoyer la position X pour le joueur
	 * Renvoie la position du joueur au départ
	 * @return
	 */
	public int getFirstPosXPlayer() {
		return points.get(1).x;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * Renvoie tous les points qui constitue la route
	 * @return
	 */
	public ArrayList<Point> getPoints() {
		ArrayList<Point> temp = new ArrayList<>();
		for(Point p: points) {
			temp.add(new Point(p.x, p.y - position));
		}
		return temp;
	}



	public int getPosition() {
		return position;
	}

	private int lastX() {
		return points.get(points.size() - 1).x;
	}

	private int lastY() {
		return points.get(points.size() - 1).y;
	}
}
