package kozlov_ponroy.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kozlov_ponroy.model.Etat;

public class KeyboardController implements MouseListener, KeyListener {

	private Etat etat ;

	public KeyboardController(Etat etat) {
		this.etat = etat;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='d') {
			etat.setPlayerX(etat.getPlayerX()+1);
		}
		if(e.getKeyChar()=='q') {
			etat.setPlayerX(etat.getPlayerX()-1);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
