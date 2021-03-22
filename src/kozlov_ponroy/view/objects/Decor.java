package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.DecorPreview;
import kozlov_ponroy.view.Affichage;
import kozlov_ponroy.view.IAffichage;

public class Decor implements IAffichage {

	private Graphics2D graphics2D;
	final Etat etat;
	final Color sky;

	public Decor(Etat etat) {
		this.etat = etat;
		sky = new Color(135,206,235);
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, Affichage.LARGEUR, etat.getHorizon());
		g.setColor(sky);
		g.fillRect(0, 0, Affichage.LARGEUR, etat.getHorizon());
		for(DecorPreview decor: etat.getDecors()) {
			graphics2D.drawImage(decor.img, decor.x, decor.y, decor.largeur, decor.hauteur, null);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
