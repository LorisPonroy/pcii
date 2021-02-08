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
			try {
				Thread.sleep(30);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
