package kozlov_ponroy.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import kozlov_ponroy.model.Etat;

public abstract class IView {
	
	protected final Etat etat;
	
	protected IView(Etat etat) {
		this.etat = etat;
	}

	public abstract void paint(Graphics g);
	public abstract void setGraphics2D(Graphics2D g2D);
	
	protected Etat getEtat() {
		return etat;
	}

}