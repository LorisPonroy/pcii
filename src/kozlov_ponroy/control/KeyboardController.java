package kozlov_ponroy.control;

import java.awt.event.*;

import kozlov_ponroy.model.state.Move;

/**
 * Le controleur qui g�re le clavier et les actions li�es aux touches
 * @author Asey
 *
 */

public class KeyboardController implements KeyListener {

	private Move move;

	/**
	 * Modifie l'�tat de notre jeu en fonction de la touche appuy�e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key)
		{
		case 'd':
			move.setRight(true);
			break;
		case 'q':
			move.setLeft(true);
			break;
		case 'z':
			move.setUp(true);
			break;
		case 's':
			move.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key)
		{
		case 'd':
			move.setRight(false);
			break;
		case 'q':
			move.setLeft(false);
			break;
		case 'z':
			move.setUp(false);
			break;
		case 's':
			move.setDown(false);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void setMove(Move move) {
		this.move = move;
	}

}
