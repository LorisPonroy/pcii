package kozlov_ponroy.model.route;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kozlov_ponroy.model.Etat;

/**
 * Cr�ation de la route
 * @author Asey
 *
 */
public class Route {

	private ArrayList<Point> points;
	private Point cp;
	final Etat etat;
	public static final int MOVE = 3;
	private int initialYCheckPoint;
	private int position = 0;

	public Route(Etat etat){
		this.etat = etat;
		points = new ArrayList<>();

		//On genere les premiers points de la route :
		points.add(new Point(400, Etat.HORIZON));
		points.add(new Point(400, Etat.HORIZON+800));
		initialYCheckPoint = -500;
		cp = new Point(Etat.LARGEUR/2, initialYCheckPoint);
	}

	/**
	 * Ajoute un point en fonction du dernier ajouter
	 */
	private void ajouterPoint() {
		//La variable x est genere aleatoirement en fonction du dernier point en x
		int x = lastX() + new Random().nextInt(100) - 100 / 2;
		if(x>Etat.LARGEUR || x<0) {
			x = lastX();
		}
		Point p = new Point(x, Etat.HORIZON);
		points.add(p);

		//Il y a une faible chance de g�n�rer �galement une flaque d'huile � ce point
		/*if(Etat.getRandom(100)<5) {
			generateOil();
		}*/
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
			points.get(i).y+=MOVE;
		}
		//avancer le checkpoint
		position++;
		avancerCP();
	}

	private void avancerCP() {
		cp.y+=MOVE;
		if(cp.y > Etat.HAUTEUR) {
			initialYCheckPoint -= 500; //a chaque fois le cp met plus de temps à apparaitre
			System.out.println("Initial checkout: " + initialYCheckPoint);
			cp = new Point(cp.x, initialYCheckPoint);
			etat.nouveauCP();
		}
	}

	public Point getCheckPoint() {
		return cp;
	}

	/**
	 * Renvoie la liste des points qui forment la route
	 * @return List<Point>
	 */
	public List<Point> getPoints() {
		ArrayList<Point> temp = new ArrayList<>();
		for (int i=0;i<points.size();i++) {
			Point p = points.get(i);
			temp.add(new Point(p.x, p.y));
		}
		return points;
	}

	private Point lastPoint() {
		return points.get(points.size() - 1);
	}

	private int lastX() {
		return lastPoint().x;
	}
	
	public int getPosition() {
		return position;
	}
}
