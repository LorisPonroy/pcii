package kozlov_ponroy.view.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.model.route.Route;
import kozlov_ponroy.model.route.RoutePreview;
import kozlov_ponroy.view.IAffichage;

public class RouteView implements IAffichage {

	final Etat etat;
	final RoutePreview route;
	final private float dash1[] = {20.0f};
	final private BasicStroke dashed;
	final private BasicStroke defaultStroke;
	private Graphics2D graphics2D;

	public RouteView(Etat etat, RoutePreview route) {
		this.etat = etat;
		this.route = route;
		dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		defaultStroke = new BasicStroke(5);
	}

	@Override
	public void paint(Graphics g) {
		graphics2D.setStroke(defaultStroke);
		g.setColor(Color.gray);
		graphics2D.fill(etat.route().routeGeneralPath());
		
		
		graphics2D.setStroke(dashed);
		graphics2D.setColor(Color.white);
		graphics2D.draw(etat.route().getBande());
		etat.route().clearRoute();
		graphics2D.setStroke(defaultStroke);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}
}
