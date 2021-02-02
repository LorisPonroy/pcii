package kozlov_ponroy.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kozlov_ponroy.model.Etat;

public class KeyboardController implements KeyListener {

	private Etat etat;
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key");
		if(e.getKeyChar()=='d') {
			etat.goRight();
		}
		if(e.getKeyChar()=='q') {
			etat.goLeft();
		}
		if(e.getKeyChar()=='z') {
			etat.goUp();
		}
		if(e.getKeyChar()=='s') {
			etat.goDown();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
