package kozlov_ponroy.model.threads;

import kozlov_ponroy.model.Etat;

public class Temps extends Thread {

	private final Etat etat;
	public static int TIME = 100;

	public Temps(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void run() {
		while(true) {
			etat.time();
			try {
				Thread.sleep(TIME);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
