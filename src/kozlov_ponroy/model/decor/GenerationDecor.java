package kozlov_ponroy.model.decor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.objects.Nuage;
import kozlov_ponroy.model.decor.objects.OiseauDroite;
import kozlov_ponroy.model.decor.objects.OiseauGauche;

public class GenerationDecor {
	
	ArrayList<DecorPreview> liste;
	final int yMax;
	final int DECOR_MAX = 5;
	
	public GenerationDecor() {
		liste = new ArrayList<>();
		this.yMax = Etat.HORIZON;
		init();
	}
	
	public void move() {
		ArrayList<DecorPreview> toDelete = new ArrayList<>();
		for(DecorPreview d: liste) {
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
		Random r = new Random();
		DecorPreview decor = null;
		int tmp = r.nextInt(2);
		if(tmp == 0) {
			decor = new Nuage();
		} else {
			if(r.nextBoolean()) {
				decor = new OiseauGauche();
			} else decor = new OiseauDroite();
		}
		liste.add(decor);
		System.out.println(decor.toString());
	}

}
