package kozlov_ponroy.model.threads;

import kozlov_ponroy.model.Etat;

public class MouvementRoute extends Thread {

	private final Etat etat;

	public MouvementRoute(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(!etat.isGameOver()) {
			etat.avancerRoute();
			try {
				Thread.sleep((long)(20 * etat.getFacteurVitesse()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
