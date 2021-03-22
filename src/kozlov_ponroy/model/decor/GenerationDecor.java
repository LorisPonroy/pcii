package kozlov_ponroy.model.decor;

import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.objects.Nuage;
import kozlov_ponroy.model.decor.objects.OiseauDroite;
import kozlov_ponroy.model.decor.objects.OiseauGauche;

public class GenerationDecor {
	
	ArrayList<DecorPreview> liste;
	final int yMax;
	
	public GenerationDecor() {
		liste = new ArrayList<>();
		this.yMax = Etat.HORIZON;
		//liste.add(new OiseauGauche(100, 150, 20, 20));
		liste.add(new OiseauDroite(100, 150));
		liste.add(new OiseauGauche(700, 120));
		liste.add(new Nuage(10, 10));
	}
	
	public void move() {
		for(DecorPreview d: liste) {
			d.x += d.speed;
		}
	}
	
	public List<DecorPreview> getDecors(){
		return liste;
	}

}
