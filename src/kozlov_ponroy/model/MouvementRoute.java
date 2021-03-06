package kozlov_ponroy.model;

public class MouvementRoute extends Thread {

	private final Etat etat;

	public MouvementRoute(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(true) {
			etat.avancerRoute();
			if(Math.random()<0.01) {
				etat.genererObstacle();
			}
			try {
				Thread.sleep((long)(30 * etat.getFacteurVitesse()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
