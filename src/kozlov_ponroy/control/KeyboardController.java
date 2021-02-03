package kozlov_ponroy.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import kozlov_ponroy.model.Etat;

public class KeyboardController implements KeyListener {

	private Etat etat;

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='d') {
			etat.goRight();
			System.out.println("KEY");
		}
		if(e.getKeyChar()=='q') {
			etat.goLeft();
			System.out.println("KEY");
		}
		if(e.getKeyChar()=='z') {
			etat.goUp();
			System.out.println("KEY");
		}
		if(e.getKeyChar()=='s') {
			etat.goDown();
			System.out.println("KEY");
		}
		etat.getAffichage().repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
