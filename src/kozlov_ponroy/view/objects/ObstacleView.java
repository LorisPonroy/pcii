package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.route.Obstacle;
import kozlov_ponroy.view.IAffichage;

public class ObstacleView implements IAffichage {

	final private Image fense;
	private Graphics2D graphics2D;
	final Etat etat;

	public ObstacleView(Etat etat) {
		this.etat = etat;
		fense = Toolkit.getDefaultToolkit().getImage("./ressources/fense.png");
	}

	@Override
	public void paint(Graphics g) {
		for(Obstacle o : etat.getRoute().getObstacles()) {
			//System.out.println("Obstacle X = " + o.getX() + " ; Y = " + o.getY());
			g.setColor(Color.black);
			int x = etat.transformePositionToPerspective(o.getX(),o.getY());
			int width = Math.abs(etat.getFacteurElargissement(o.getY()));
			graphics2D.drawImage(fense, x, o.getY(), width, width, null);

			//graphics2D.drawImage(fense, o.getX() - width/2, o.getY(), width,o.getHauteur() * width,null);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
