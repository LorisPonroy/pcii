package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IAffichage;

public class GameInfoView implements IAffichage {
	
	final Etat etat;
	final private int VITESSE_MAX = 1200;
	
	public GameInfoView(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString(etat.getScore(), 20, 20);
		g.drawString("Vitesse : " + (int)(VITESSE_MAX / etat.getFacteurVitesse()) + " km/h", 20, 60);
		g.drawString(etat.tempsRestant(), 20, 100);
	}
	
	@Override
	public void setGraphics2D(Graphics2D g2d) {}

}
