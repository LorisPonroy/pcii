package kozlov_ponroy.model;

public class Mouvement extends Thread {
	
	private Etat etat;
	
	public Mouvement(Etat etat) {
		this.etat = etat;
	}
	
	@Override
	public void run() {
		while(true) {
			etat.move();
			try {
				Thread.sleep(20);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
