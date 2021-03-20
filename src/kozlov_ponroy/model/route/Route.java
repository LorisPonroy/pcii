package kozlov_ponroy.model.route;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;

/**
 * Cr�ation de la route
 * @author Asey
 *
 */
public class Route {

	public static final int ESPACEMENT = 400;

	private ArrayList<Point> points;

	private ArrayList<Obstacle> obstacles;
	private final int MARGE_RANDOM = 600;
	private int position;
	private final int X_MAX, Y_MAX, LARGEUR_ROUTE;
	public final int PX_PAS = 5;
	private Random random;
	private Point cp;
	final Etat etat;

	public Route(Etat etat, int largeurRoute){
		this.etat = etat;
		X_MAX = Affichage.LARGEUR;
		Y_MAX = Affichage.HAUTEUR;
		LARGEUR_ROUTE = largeurRoute;
		points = new ArrayList<>();
		obstacles = new ArrayList<>();
		random = new Random();
		position = 0;
		points.add(new Point(random.nextInt(X_MAX), -400));
		while(lastY() <= Y_MAX) {
			ajouterPoint();
		}
		cp = (Point) lastPoint().clone();
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
				//System.out.println("Point supprim�");
			}
		}
		position += PX_PAS;
		avancerCP();
		for(Obstacle o : obstacles) {
			o.setY(o.getY() + PX_PAS);
		}
	}

	private void avancerCP() {
		cp.y -= PX_PAS;
		//System.out.println(cp.y);
		if(cp.y <= -5000) {
			cp = (Point) lastPoint().clone();
			cp.y -= position;
			etat.nouveauCP();
		}
	}

	public void genererObstacle() {
		int x = lastX() + (int) (Math.random() * MARGE_RANDOM) - MARGE_RANDOM / 2;
		int y = 0;
		obstacles.add(new Obstacle(x, y));
	}

	public Point getCheckPoint() {
		return cp;
	}

	/** TODO: refaire la fonction pour renvoyer la position X pour le joueur
	 * Renvoie la position du joueur au d�part
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
			temp.add(new Point(p.x, p.y - position));
		}
		return temp;
	}

	public int getPosition() {
		return position;
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