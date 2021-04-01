package kozlov_ponroy.view.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.obstacle.ObstaclePreview;
import kozlov_ponroy.view.IView;

public class ObstacleView implements IView {

	private Graphics2D graphics2D;
	final Etat etat;

	public ObstacleView(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void paint(Graphics g) {
		for(ObstaclePreview ob: etat.getObstacles()) {
			graphics2D.drawImage(ob.img, ob.x1, ob.y1, ob.x2 - ob.x1, ob.y2 - ob.y1, null);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
