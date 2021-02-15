package kozlov_ponroy.model;

public class MouvementRoute extends Thread {

	private final Etat etat;

	private int vitesseDefilement = 100;

	public MouvementRoute(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(true) {
			etat.avancerRoute();
			try {
				Thread.sleep(2000/vitesseDefilement);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
