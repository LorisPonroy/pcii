package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.state.Player;
import kozlov_ponroy.view.IView;

public class PlayerView implements IView {
	public enum PlayerState{
		NORMAL(Player.LARGEUR,Player.HAUTEUR),ROLL_LEFT(Player.HAUTEUR,Player.HAUTEUR),ROLL_RIGTH(Player.HAUTEUR,Player.HAUTEUR);
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
	final Image left, right, normal, roll_1, roll_2;

	public PlayerView(Etat etat) {
		this.etat = etat;
		ombre = new Color(0,0,0,170);
		left = Toolkit.getDefaultToolkit().getImage("./ressources/playertleft.png");
		right = Toolkit.getDefaultToolkit().getImage("./ressources/playertright.png");
		normal = Toolkit.getDefaultToolkit().getImage("./ressources/player.png");
		roll_1 = Toolkit.getDefaultToolkit().getImage("./ressources/player_roll.png");
		roll_2 = Toolkit.getDefaultToolkit().getImage("./ressources/player_roll_2.png");
	}

	@Override
	public void paint(Graphics g) {
		//TODO: Faire l'ombre de la moto
		switch (state) {
		case NORMAL:
			if(etat.isLeft()) {
				graphics2D.drawImage(left, etat.getPlayerX() - Player.LARGEUR / 2, etat.getPlayerY() - Player.HAUTEUR, state.imageWidth, state.imageHeigth, null);
			} else if(etat.isRight()) {
				graphics2D.drawImage(right, etat.getPlayerX() - Player.LARGEUR / 2, etat.getPlayerY() - Player.HAUTEUR, state.imageWidth, state.imageHeigth, null);
			} else {
				graphics2D.drawImage(normal, etat.getPlayerX() - Player.LARGEUR / 2, etat.getPlayerY() - Player.HAUTEUR, state.imageWidth, state.imageHeigth, null);
			}
			break;
		case ROLL_LEFT:
			graphics2D.drawImage(roll_1, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			break;
		case ROLL_RIGTH:
			graphics2D.drawImage(roll_2, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), state.imageWidth, state.imageHeigth, null);
			break;
		}
		graphics2D.drawRect(etat.getPlayerX() - Player.LARGEUR / 2, etat.getPlayerY() - Player.HAUTEUR / 4, Player.LARGEUR, Player.HAUTEUR / 4);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
