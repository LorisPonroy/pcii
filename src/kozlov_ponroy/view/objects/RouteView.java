package kozlov_ponroy.view.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IAffichage;

public class RouteView implements IAffichage {
	
	final Etat etat;
	private List<Point> route;
	private Point p1, p2, p3;
	private int[] routeX;
	private int[] routeY;
	private Color colorRoute;
	
	QuadCurve2D courbeDroite = new QuadCurve2D.Double();
	QuadCurve2D courbeGauche = new QuadCurve2D.Double();
	QuadCurve2D courbeBande = new QuadCurve2D.Double();

	final private float dash1[] = {20.0f};
	final private BasicStroke dashed;
	private Point2D debut;
	private Point2D ctrl;
	private Point2D fin;
	private GeneralPath closedCurve;

	private Graphics2D graphics2D;
	
	public RouteView(Etat etat) {
		this.etat = etat;
		this.route = new ArrayList<>();
		routeX = new int[5];
		routeY = new int[5];
		colorRoute = new Color(100,100,100,255);
		dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
	}

	@Override
	public void paint(Graphics g) {		
		g.setColor(Color.gray);
		route = etat.getRoutePoints();
		for(int i = 0 ; i < route.size() - 2 ; i++) // -2 : besoin de 3 points pour construire les routes
		{
			g.setColor(Color.gray);
			p1 = route.get(i);
			p2 = route.get(i+1);
			p3 = route.get(i+2);

			/**
			 * Definition des points pour la route
			 */
			routeX[0] = p1.x - etat.getFacteurElargissement(p1.y) / 2; routeY[0] = p1.y; //Point bas	gauche
			routeX[1] = p2.x - etat.getFacteurElargissement(p2.y) / 2; routeY[1] = p2.y; //Point haut	gauche
			routeX[2] = p2.x + etat.getFacteurElargissement(p2.y) / 2; routeY[2] = p2.y; //Point haut	droite
			routeX[3] = p1.x + etat.getFacteurElargissement(p1.y) / 2; routeY[3] = p1.y; //Point bas	droite

			g.setColor(Color.BLACK);
			/**
			 * Courbe droite route
			 */
			routeX[4] = p3.x - etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			debut = new Point2D.Double(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			ctrl = new Point2D.Double(routeX[1], routeY[1]);
			fin = new Point2D.Double(routeX[1] + (routeX[4]- routeX[1]) / 2, routeY[1] + (routeY[4]- routeY[1]) / 2);
			courbeDroite.setCurve(debut,ctrl,fin);

			/**
			 * Courbe gauche route
			 */
			routeX[4] = p3.x + etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			fin = new Point2D.Double(routeX[3] + (routeX[2]- routeX[3]) / 2, routeY[3] + (routeY[2]- routeY[3]) / 2);
			ctrl = new Point2D.Double(routeX[2], routeY[2]);
			debut = new Point2D.Double(routeX[2] + (routeX[4]- routeX[2]) / 2, routeY[2] + (routeY[4]- routeY[2]) / 2);
			courbeGauche.setCurve(debut,ctrl,fin);

			/**
			 * Courbe ferme qui est la route entiere
			 */
			closedCurve = new GeneralPath();
			closedCurve.append(courbeDroite, true);
			closedCurve.append(courbeGauche, true);
			closedCurve.lineTo(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			closedCurve.closePath();

			/**
			 * Courbe bande
			 */
			debut = new Point2D.Double(p1.x + (p2.x - p1.x) / 2, p1.y + (p2.y - p1.y) / 2);
			ctrl = new Point2D.Double(p2.x, p2.y);
			fin = new Point2D.Double(p2.x + (p3.x - p2.x) / 2, p2.y + (p3.y - p2.y) / 2);
			courbeBande.setCurve(debut,ctrl,fin);

			/**
			 * Affichage route
			 */
			graphics2D.setStroke(new BasicStroke(5));
			graphics2D.setColor(colorRoute);
			graphics2D.fill(closedCurve);
			graphics2D.draw(courbeGauche);
			graphics2D.draw(courbeDroite);

			/**
			 * Affichage bande route
			 */
			graphics2D.setStroke(dashed);
			graphics2D.setColor(Color.white);
			graphics2D.draw(courbeBande);
		}

		graphics2D.setStroke(new BasicStroke(5));
		g.setColor(Color.RED);
		g.drawRect(routeX[3]-20, routeY[0], routeX[0]-routeX[3]+40, 5);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		this.graphics2D = g2d;
	}
}
