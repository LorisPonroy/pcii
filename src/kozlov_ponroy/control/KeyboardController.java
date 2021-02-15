package kozlov_ponroy.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import kozlov_ponroy.model.Etat;

/**
 * Le controleur qui gère le clavier et les actions liées aux touches
 * @author Asey
 *
 */

public class KeyboardController implements KeyListener {

	private Etat etat;

	/**
	 * Modifie l'état de notre jeu en fonction de la touche appuyée
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key)
		{
		case 'd':
			etat.setRight(true);
			break;
		case 'q':
			etat.setLeft(true);
			break;
		case 'z':
			etat.setUp(true);
			break;
		case 's':
			//etat.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key)
		{
		case 'd':
			etat.setRight(false);
			break;
		case 'q':
			etat.setLeft(false);
			break;
		case 'z':
		case ' ':
			etat.setUp(false);
			break;
		case 's':
			//etat.setDown(false);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
