package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IAffichage;

public class Vaisseau implements IAffichage {


	//final private Image playerLeftImage;
	//final private Image playerRightImage;
	final private Image playerCenterImage;
	final Etat etat;
	final Color ombre;
	private Graphics2D graphics2D;
	
	public Vaisseau(Etat etat) {
		this.etat = etat;
		ombre = new Color(0,0,0,170);
		playerCenterImage = Toolkit.getDefaultToolkit().getImage("./ressources/playerTest.png");
		//playerLeftImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_left.png");
		//playerRightImage = Toolkit.getDefaultToolkit().getImage("./ressources/player_right.png");
	}
	
	@Override
	public void paint(Graphics g) {
		graphics2D.setColor(ombre);
		graphics2D.fillOval(etat.getPlayerX(), etat.getHauteurJoueur() + 100, etat.getTailleJoueur(), 20);
		graphics2D.drawImage(playerCenterImage, etat.getPlayerX(), etat.getPlayerY(), etat.getTailleJoueur(), etat.getTailleJoueur() / 2, null);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		this.graphics2D = g2d;
	}

}
