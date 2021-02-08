package kozlov_ponroy.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import kozlov_ponroy.model.Etat;

public class KeyboardController implements KeyListener {

	private Etat etat;

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key)
		{
		case 'd':
			etat.goRight();
			break;
		case 'q':
			etat.goLeft();
			break;
		case 'z':
			etat.goUp();
			break;
		case 's':
			etat.goDown();
			break;
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

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
