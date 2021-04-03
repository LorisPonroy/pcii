package kozlov_ponroy.model.obstacle;

import java.util.ArrayList;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.obstacle.objects.Adversaire;
import kozlov_ponroy.model.obstacle.objects.Oil;

public class GenerationObstacle {

	private ArrayList<ObstaclePreview> liste;
	private ArrayList<ObstaclePreview> toDelete;
	final int OBSTACLES_MAX = 2;
	final Etat etat;
	final Random random;

	public GenerationObstacle(Etat etat) {
		this.etat = etat;
		this.random = new Random();
		liste = new ArrayList<>();
		toDelete = new ArrayList<>();
		init();
	}

	private void generateObstacle() {
		if(liste.size() < OBSTACLES_MAX) {
			liste.add(new Adversaire());
			/*if(random.nextInt(6) == 1) {
				liste.add(new Adversaire());
			}
			else{
				liste.add(new Oil());
			}*/
		}
	}

	public ArrayList<ObstaclePreview> getObstacles(){
		return liste;
	}

	private void init() {
		for(int i = 0 ; i < OBSTACLES_MAX ; i++) {
			generateObstacle();
		}
	}

	public void move() {
		toDelete.clear();
		for(ObstaclePreview ob: liste) {
			ob.move(etat);
			if(ob.canRemove()) {
				toDelete.add(ob);
			}
		}
		liste.removeAll(toDelete);
		generateObstacle();
	}

}
