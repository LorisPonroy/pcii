package kozlov_ponroy.model.threads;

import kozlov_ponroy.model.Etat;

public class Temps extends Thread {

	public static int TIME = 100;
	private final Etat etat;

	public Temps(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(!etat.isGameOver()) {
			etat.time();
			try {
				Thread.sleep(TIME);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
