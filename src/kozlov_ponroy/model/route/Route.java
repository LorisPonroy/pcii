package kozlov_ponroy.model.route;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.model.Etat;

/**
 * Création de la route
 * @author Asey
 *
 */
public class Route {

	private ArrayList<Point> points;
	private ArrayList<Oil> oils;
	private Point cp;
	final Etat etat;

	public Route(Etat etat){
		this.etat = etat;
		points = new ArrayList<>();
		oils = new ArrayList<>();

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
		int x = lastX() + Etat.getRandom(100) - 100 / 2;
		if(x>Etat.LARGEUR || x<0) {
			x = lastX();
		}
		Point p = new Point(x, Etat.HORIZON);
		points.add(p);

		//Il y a une faible chance de générer également une flaque d'huile à ce point
		if(Etat.getRandom(100)<5) {
			generateOil();
		}
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
		for(Oil o : oils) {
			o.setY(o.getY() + 1);
		}

		//détection des collisions
		boolean isInCollision = false;
		for(Oil o : oils) {
			isInCollision = isInCollision || o.isInCollision(etat.getPlayerX(), etat.getPlayerY());
		}
		if(isInCollision) {
			etat.doABarrelRoll();
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

	public void generateOil() {
		int x = lastX() + Etat.getRandom(Etat.LARGEUR_ROUTE) - Etat.LARGEUR_ROUTE/2;
		int y = 0;
		oils.add(new Oil(x, y));
	}

	public Point getCheckPoint() {
		return cp;
	}

	/** TODO: refaire la fonction pour renvoyer la position X pour le joueur
	 * Renvoie la position du joueur au départ
	 * @return
	 */
	//public int getFirstPosXPlayer() {
	//return points.get(1).x;
	//}

	public ArrayList<Oil> getOils() {
		return oils;
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
}
