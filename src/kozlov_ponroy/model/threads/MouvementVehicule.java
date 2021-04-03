package kozlov_ponroy.model.threads;

import kozlov_ponroy.model.Etat;

public class MouvementVehicule extends Thread {

	private Etat etat;

	public MouvementVehicule(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(!etat.isGameOver()) {
			etat.move();
			try {
				Thread.sleep(20);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
