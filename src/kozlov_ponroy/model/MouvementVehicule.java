package kozlov_ponroy.model;

public class MouvementVehicule extends Thread {
	
	private Etat etat;
	
	public MouvementVehicule(Etat etat) {
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
