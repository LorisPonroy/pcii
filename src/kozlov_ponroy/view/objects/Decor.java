package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.decor.DecorPreview;
import kozlov_ponroy.view.IView;

public class Decor extends IView {

	private Graphics2D graphics2D;
	final Color sky;

	public Decor(Etat etat) {
		super(etat);
		sky = new Color(135,206,235);
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, Etat.LARGEUR, 
				super.etat.getHorizon());
		g.setColor(sky);
		g.fillRect(0, 0, Etat.LARGEUR, super.etat.getHorizon());
		for(DecorPreview decor: super.etat.getDecors()) {
			graphics2D.drawImage(decor.img, decor.x, decor.y, decor.largeur, decor.hauteur, null);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
