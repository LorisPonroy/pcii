package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IView;

public class PlayerView implements IView {
	public enum PlayerState{
		NORMAL(50,100),ROLL_LEFT(100,100),ROLL_RIGTH(100,100);
		public int imageWidth;
		public int imageHeigth;

		private PlayerState(int width, int height) {
			imageHeigth =height;
			imageWidth = width;
		}
	}

	public static PlayerState state = PlayerState.NORMAL;
	final Etat etat;
	final Color ombre;
	private Graphics2D graphics2D;

	public PlayerView(Etat etat) {
		this.etat = etat;
		ombre = new Color(0,0,0,170);
	}

	@Override
	public void paint(Graphics g) {
		graphics2D.setColor(ombre);
		graphics2D.fillOval(etat.getPlayerX(), etat.getHauteurJoueur() + 100, etat.getTailleJoueur(), 20);
		switch (state) {
		case NORMAL:
			if(etat.isLeft()) {
				Image playerImage = Toolkit.getDefaultToolkit().getImage("./ressources/playertleft.png");
				graphics2D.drawImage(playerImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			} else if(etat.isRight()) {
				Image playerImage = Toolkit.getDefaultToolkit().getImage("./ressources/playertright.png");
				graphics2D.drawImage(playerImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			} else {
				Image playerImage = Toolkit.getDefaultToolkit().getImage("./ressources/playert.png");
				graphics2D.drawImage(playerImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			}
			break;
		case ROLL_LEFT:
			Image playerImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_roll.png");
			graphics2D.drawImage(playerImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			break;
		case ROLL_RIGTH:
			Image playerImage2 = Toolkit.getDefaultToolkit().getImage("./ressources/player_roll_2.png");
			graphics2D.drawImage(playerImage2, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			break;
		default:
			break;
		}

		//Hitbox
		graphics2D.setColor(Color.red);
		graphics2D.drawRect(etat.getPlayerX(), etat.getPlayerY(), 2, 2);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
