package kozlov_ponroy.model.route;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import kozlov_ponroy.model.Etat;

/**
 * Création de la route
 * @author Asey
 *
 */
public class Route {

	private ArrayList<Point> points;

	private ArrayList<Obstacle> obstacles;
	private final int X_MAX, Y_MAX;
	private Random random;
	private Point cp;
	final Etat etat;

	public Route(Etat etat){
		this.etat = etat;
		X_MAX = Etat.LARGEUR;
		Y_MAX = Etat.HAUTEUR;
		points = new ArrayList<>();
		obstacles = new ArrayList<>();
		random = new Random();
		//On genere les premiers points de la route :
		points.add(new Point(400, Etat.HORIZON));
		points.add(new Point(400, Etat.HORIZON+800));
		cp = (Point) lastPoint().clone();
	}

	/**
	 * Ajoute un point en fonction du dernier ajouter
	 */
	private void ajouterPoint() {
		//La variable x est genere aleatoirement en fonction du dernier point en x
		int x = lastX() + random.nextInt(75) - 75 / 2;
		if(x>X_MAX || x<0) {
			x = 400;
		}
		Point p = new Point(x, Etat.HORIZON);
		points.add(p);
	}

	/**
	 * Met a jour la position
	 * Ajoute un point et supprime le dernier lorsque la position est modulo espacement
	 */
	public void avancer() {
		if(points.get(points.size()-1).getY()>Etat.HORIZON+100) {
			ajouterPoint();
		}
		if(points.get(1).getY()>800) {
			points.remove(0);
		}

		for (int i = 0;i<points.size();i++) {
			points.get(i).y+=5;
		}
		//avancer le checkpoint
		avancerCP();
		//avancer les obstacles
		for(Obstacle o : obstacles) {
			o.setY(o.getY() + 1);
		}
	}

	private void avancerCP() {
		cp.y-=5;
		if(cp.y <= -5000) {
			cp = (Point) lastPoint().clone();
			cp.y-=5;
			etat.nouveauCP();
		}
	}

	public void genererObstacle() {
		int x = lastX() + (int) (Math.random() * 600) - 600 / 2;
		int y = 0;
		obstacles.add(new Obstacle(x, y));
	}

	public Point getCheckPoint() {
		return cp;
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
		for (int i=0;i<points.size();i++) {
			Point p = points.get(i);
			temp.add(new Point(p.x, p.y));
		}
		return temp;
	}

	private Point lastPoint() {
		return points.get(points.size() - 1);
	}

	private int lastX() {
		return lastPoint().x;
	}

	private int lastY() {
		return lastPoint().y;
	}
}
