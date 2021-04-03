package kozlov_ponroy.view.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IView;

public class RouteView implements IView {

	final Etat etat;
	final private float dash1[] = {20.0f};
	final private BasicStroke dashed;
	final private BasicStroke defaultStroke;
	private Graphics2D graphics2D;

	public RouteView(Etat etat) {
		this.etat = etat;
		dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		defaultStroke = new BasicStroke(5);
	}

	@Override
	public void paint(Graphics g) {
		graphics2D.setStroke(defaultStroke);
		g.setColor(Color.gray);
		ArrayList<Point> points = (ArrayList<Point>) etat.getRoutePoints();

		Point p;
		int nbPointsPolygoneRoute = (points.size()+2)* 2;
		int[] pointsX = new int[nbPointsPolygoneRoute];
		int[] pointsY = new int[nbPointsPolygoneRoute];
		int compteur = 0;
		for(int i = 0 ; i < points.size(); i++)
		{
			p = points.get(i);
			pointsX[compteur] = etat.transformePositionToPerspective(p.x - Etat.LARGEUR_ROUTE,p.y);
			pointsY[compteur] = p.y;
			compteur++;

		}
		pointsX[compteur] = etat.transformePositionToPerspective(400 + Etat.LARGEUR_ROUTE,Etat.HORIZON);
		pointsY[compteur] = Etat.HORIZON;
		compteur++;
		pointsX[compteur] = etat.transformePositionToPerspective(400 - Etat.LARGEUR_ROUTE,Etat.HORIZON);
		pointsY[compteur] = Etat.HORIZON;
		compteur++;
		for(int i = points.size()-1 ; i>=0  ; i--)
		{
			p = points.get(i);
			pointsX[compteur] = etat.transformePositionToPerspective(p.x + Etat.LARGEUR_ROUTE,p.y);
			pointsY[compteur] = p.y;
			compteur++;
		}

		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(pointsX, pointsY, compteur);
		graphics2D.setStroke(dashed);
		graphics2D.setColor(Color.white);
		for(int i =0;i<points.size()-1;i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			int p1X = etat.transformePositionToPerspective(p1.x, p1.y);
			int p2X = etat.transformePositionToPerspective(p2.x, p2.y);
			graphics2D.draw(new Line2D.Float(p1X, p1.y, p2X, p2.y));
		}
		Point p1 = points.get(points.size()-1);
		int p1X = etat.transformePositionToPerspective(p1.x, p1.y);
		graphics2D.draw(new Line2D.Float(p1X, p1.y, 400, Etat.HORIZON));
		
		Point cp = etat.getCheckPoint();
		graphics2D.fillRect(cp.x, cp.y, 100, 10); //trouver les points avec la route jsp lequel
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
