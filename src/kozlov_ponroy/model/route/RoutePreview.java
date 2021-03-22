package kozlov_ponroy.model.route;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.GeneralPath;
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
		touteLaRoute = new GeneralPath();
		closedCurve = new GeneralPath();
		bande = new GeneralPath();
	}

	public void clearRoute() {
		bande.reset();
		touteLaRoute.reset();
	}

	public GeneralPath getBande() {
		return bande;
	}

	public void routeGeneralPath(Graphics g) {


	}

}
