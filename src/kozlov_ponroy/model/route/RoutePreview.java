package kozlov_ponroy.model.route;

import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import kozlov_ponroy.model.Etat;

public class RoutePreview {
	
	public final Etat etat;
	private GeneralPath closedCurve, touteLaRoute, bande;
	 
	private QuadCurve2D courbeDroite = new QuadCurve2D.Double();
	private QuadCurve2D courbeGauche = new QuadCurve2D.Double();
	private QuadCurve2D courbeBande = new QuadCurve2D.Double();
	

	int[] routeX = new int[5];
	int[] routeY = new int[5];
	
	ArrayList<Point> route;
	
	public RoutePreview(Etat etat) {
		this.etat = etat;
		this.touteLaRoute = new GeneralPath();
		this.closedCurve = new GeneralPath();
		this.bande = new GeneralPath();
	}
	
	public GeneralPath routeGeneralPath() {
		route = etat.getRoutePoints();
		Point p1,p2,p3;

		Point2D debut, ctrl, fin;
		for(int i = 0 ; i < route.size() - 2 ; i++) // -2 : besoin de 3 points pour construire les routes
		{
			p1 = route.get(i);
			p2 = route.get(i+1);
			p3 = route.get(i+2);
			int p1X = etat.transformePositionToPerspective(p1.x, p1.y);
			int p2X = etat.transformePositionToPerspective(p2.x, p2.y);
			int p3X = etat.transformePositionToPerspective(p3.x, p3.y);

			/**
			 * Definition des points pour la route
			 */
			routeX[0] = p1X - etat.getFacteurElargissement(p1.y) / 2; routeY[0] = p1.y; //Point bas	gauche
			routeX[1] = p2X - etat.getFacteurElargissement(p2.y) / 2; routeY[1] = p2.y; //Point haut	gauche
			routeX[2] = p2X + etat.getFacteurElargissement(p2.y) / 2; routeY[2] = p2.y; //Point haut	droite
			routeX[3] = p1X + etat.getFacteurElargissement(p1.y) / 2; routeY[3] = p1.y; //Point bas	droite
			/**
			 * Courbe droite route
			 */
			routeX[4] = p3X - etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			debut = new Point2D.Double(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			ctrl = new Point2D.Double(routeX[1], routeY[1]);
			fin = new Point2D.Double(routeX[1] + (routeX[4]- routeX[1]) / 2, routeY[1] + (routeY[4]- routeY[1]) / 2);
			courbeDroite.setCurve(debut,ctrl,fin);

			/**
			 * Courbe gauche route
			 */
			routeX[4] = p3X + etat.getFacteurElargissement(p3.y) / 2;	routeY[4] = p3.y;
			fin = new Point2D.Double(routeX[3] + (routeX[2]- routeX[3]) / 2, routeY[3] + (routeY[2]- routeY[3]) / 2);
			ctrl = new Point2D.Double(routeX[2], routeY[2]);
			debut = new Point2D.Double(routeX[2] + (routeX[4]- routeX[2]) / 2, routeY[2] + (routeY[4]- routeY[2]) / 2);
			courbeGauche.setCurve(debut,ctrl,fin);

			/**
			 * Courbe ferme qui est la route entiere
			 */
			closedCurve.append(courbeDroite, true);
			closedCurve.append(courbeGauche, true);
			closedCurve.lineTo(routeX[0] + (routeX[1]- routeX[0]) / 2, routeY[0] + (routeY[1]- routeY[0]) / 2);
			closedCurve.closePath();
			touteLaRoute.append(closedCurve, true);

			/**
			 * Courbe bande
			 */
			debut = new Point2D.Double(p1X + (p2X - p1X) / 2, p1.y + (p2.y - p1.y) / 2);
			ctrl = new Point2D.Double(p2X, p2.y);
			fin = new Point2D.Double(p2X + (p3X - p2X) / 2, p2.y + (p3.y - p2.y) / 2);
			courbeBande.setCurve(debut,ctrl,fin);
			bande.append(courbeBande, false);
			
			closedCurve.reset();
		}
		//cp = getCheckPoint();
		//int cpX = etat.transformePositionToPerspective(cp.x, cp.y);
		//g.fillRect(cpX - etat.tailleCP() / 2, cp.y, etat.tailleCP(), 10);
		//System.out.println("Intersect : " + enbas.contains(etat.getPlayerX(), 570, 5, 5));
		//touteLaRoute.reset();
		return touteLaRoute;
	}
	
	public GeneralPath getBande() {
		return bande;
	}
	
	public void clearRoute() {
		bande.reset();
		touteLaRoute.reset();
	}

}
