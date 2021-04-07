package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IView;

public class Horizon extends IView {

	public Horizon(Etat etat) {
		super(etat);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, etat.getHorizon(), Etat.LARGEUR, 1);
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {}
}
