package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;
import kozlov_ponroy.view.IAffichage;

public class Vaisseau implements IAffichage {


	final private Image playerLeftImage;
	final private Image playerRightImage;
	final private Image playerCenterImage;
	final Etat etat;
	final Color ombre;
	private Graphics2D graphics2D;
	
	public Vaisseau(Etat etat) {
		this.etat = etat;
		ombre = new Color(0,0,0,170);
		playerCenterImage = Toolkit.getDefaultToolkit().getImage("./ressources/playert.png");
		playerLeftImage = Toolkit.getDefaultToolkit().getImage("./ressources/playertleft.png");
		playerRightImage = Toolkit.getDefaultToolkit().getImage("./ressources/playertright.png");
	}
	
	@Override
	public void paint(Graphics g) {
		graphics2D.setColor(ombre);
		//graphics2D.fillOval(etat.getPlayerX(), etat.getHauteurJoueur() + 100, etat.getTailleJoueur(), 20);
		if(etat.isLeft()) {
			graphics2D.drawImage(playerLeftImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), etat.getTailleJoueur() / 2, etat.getTailleJoueur(), null);	
		} else if(etat.isRight()) {
			graphics2D.drawImage(playerRightImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), etat.getTailleJoueur() / 2, etat.getTailleJoueur(), null);	
		} else graphics2D.drawImage(playerCenterImage, etat.getPlayerX() - etat.getTailleJoueur() / 4, etat.getPlayerY() - etat.getTailleJoueur(), etat.getTailleJoueur() / 2, etat.getTailleJoueur(), null);	
		graphics2D.setColor(Color.red);
		//Hitbox
		graphics2D.drawRect(etat.getPlayerX(), etat.getPlayerY(), 1, 1);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		this.graphics2D = g2d;
	}

}
