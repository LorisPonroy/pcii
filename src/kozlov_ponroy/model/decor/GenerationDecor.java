package kozlov_ponroy.model.decor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.objects.Nuage;
import kozlov_ponroy.model.decor.objects.OiseauDroite;
import kozlov_ponroy.model.decor.objects.OiseauGauche;

public class GenerationDecor {
	
	private enum EDecor {
		OISEAU_GAUCHE, OISEAU_DROITE, NUAGE;
		
		public DecorPreview getDecor() {
			switch(this) {
				case OISEAU_GAUCHE:
					return new OiseauGauche();
				case OISEAU_DROITE:
					return new OiseauDroite();
				case NUAGE:
					return new Nuage();
			}
			return null;
		}
	}
	
	private ArrayList<DecorPreview> liste;
	private ArrayList<DecorPreview> toDelete;
	final int DECOR_MAX = 5;
	
	public GenerationDecor() {
		liste = new ArrayList<>();
		toDelete = new ArrayList<>();
		init();
	}
	
	public void move() {
		toDelete.clear();
		//System.out.println("----------------------------");
		for(DecorPreview d: liste) {
			//System.out.println(d.toString());
			d.x += d.speed;
			if(estEnDehors(d))
				toDelete.add(d);
		}
		liste.removeAll(toDelete);
		for(int i = liste.size() ; i < DECOR_MAX ; i++) {
			addRandomDecor();
		}
	}
	
	public List<DecorPreview> getDecors(){
		return liste;
	}
	
	private boolean estEnDehors(DecorPreview d) {
		return d.x < - d.largeur || d.x > Etat.LARGEUR;
	}
	
	private void init() {
		for(int i = 0 ; i < DECOR_MAX ; i++) {
			addRandomDecor();
		}
	}
	
	private void addRandomDecor() {
		liste.add(EDecor.values()[new Random().nextInt(EDecor.values().length)].getDecor());
	}

}
