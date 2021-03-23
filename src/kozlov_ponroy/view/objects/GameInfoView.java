package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IView;

public class GameInfoView implements IView {
	
	final Etat etat;
	
	public GameInfoView(Etat etat) {
		this.etat = etat;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString(etat.getScore(), 20, 20);
		g.drawString("Vitesse : " + etat.getVitesse() + " km/h", 20, 60);
		g.drawString(etat.tempsRestant(), 20, 100);
	}
	
	@Override
	public void setGraphics2D(Graphics2D g2d) {}

}
