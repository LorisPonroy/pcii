package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.obstacle.ObstaclePreview;
import kozlov_ponroy.model.obstacle.objects.Oil;
import kozlov_ponroy.view.IView;

public class ObstacleView implements IView {

	final private Image fense;
	private Graphics2D graphics2D;
	final Etat etat;
	ObstaclePreview ob;

	public ObstacleView(Etat etat) {
		this.etat = etat;
		fense = Toolkit.getDefaultToolkit().getImage("./ressources/oil.png");
		ob = new Oil();
	}

	@Override
	public void paint(Graphics g) {
		/*for(Oil o : etat.getRoute().getOils()) {
			g.setColor(Color.black);
			int x = Etat.transformePositionToPerspective(o.getX(),o.getY());
			int x2 = Etat.transformePositionToPerspective(o.getX() + Oil.LARGEUR_OIL,o.getY());
			int width = x2 - x;
			graphics2D.drawImage(fense, x, o.getY() - width, width, width, null);
			System.out.println("Oil view X:" + x + " Y:" + o.getY());
			//hitbox
			g.setColor(Color.red);
			g.drawRect(x, o.getY() - width, width, width);
		}*/
		graphics2D.drawImage(ob.img, ob.x1, ob.y1, ob.x2 - ob.x1, ob.y2 - ob.y1, null);
		ob.move(etat);
		//System.out.println(ob.toString());
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
