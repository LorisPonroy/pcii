package kozlov_ponroy.view.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.Affichage;
import kozlov_ponroy.view.IAffichage;

public class Decor implements IAffichage {

	final private Image montagneImage;
	final private Image nuage1;
	final private Image nuage2;
	private Graphics2D graphics2D;
	final Etat etat;

	public Decor(Etat etat) {
		this.etat = etat;
		montagneImage = Toolkit.getDefaultToolkit().getImage("./ressources/montagne.png");
		nuage1 = Toolkit.getDefaultToolkit().getImage("./ressources/nuage_1.png");
		nuage2 = Toolkit.getDefaultToolkit().getImage("./ressources/nuage_2.png");
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, Affichage.LARGEUR, etat.getHorizon());
		graphics2D.drawImage(montagneImage, Affichage.LARGEUR / 2 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, Affichage.LARGEUR + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(montagneImage, -50 + etat.getPositionDecor(), etat.getHorizon() - 130, 500, 130, null);
		graphics2D.drawImage(nuage1, 300 + etat.getPositionDecor(), etat.getHorizon() - 260, 400, 130, null);
		graphics2D.drawImage(nuage2, -50 + etat.getPositionDecor(), etat.getHorizon() - 260, 300, 130, null);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
