package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.route.Oil;
import kozlov_ponroy.view.IView;

public class OilView implements IView {

	final private Image fense;
	private Graphics2D graphics2D;
	final Etat etat;

	public OilView(Etat etat) {
		this.etat = etat;
		fense = Toolkit.getDefaultToolkit().getImage("./ressources/oil.png");
	}

	@Override
	public void paint(Graphics g) {
		for(Oil o : etat.getRoute().getOils()) {
			g.setColor(Color.black);
			int x = Etat.transformePositionToPerspective(o.getX(),o.getY());
			int x2 = Etat.transformePositionToPerspective(o.getX() + Oil.LARGEUR_OIL,o.getY());
			int width = x2 - x;
			graphics2D.drawImage(fense, x, o.getY(), width, width, null);
			//hitbox
			g.setColor(Color.red);
			g.drawRect(x, o.getY(), width, width);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
