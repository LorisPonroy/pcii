package kozlov_ponroy.view.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import kozlov_ponroy.model.Etat;
import kozlov_ponroy.view.IView;

public class GameInfoView extends IView {

	private final static Image compteur = Toolkit.getDefaultToolkit().getImage("./ressources/compteur.png");
	private final static Image chronometer = Toolkit.getDefaultToolkit().getImage("./ressources/chronometer.png");
	private final static int TAILLE_COMPTEUR = 100;

	private Graphics2D graphics2D;

	public GameInfoView(Etat etat) {
		super(etat);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		graphics2D.drawImage(compteur, Etat.LARGEUR-TAILLE_COMPTEUR, Etat.HAUTEUR-TAILLE_COMPTEUR, TAILLE_COMPTEUR, TAILLE_COMPTEUR, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.drawString(etat.getScore(), 20, 20);
		g.drawString("Prochain CP : " + (Etat.HAUTEUR - etat.getCheckPoint().y) + "m", 20, 40);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		if(etat.getVitesse()<100) {
			g.setColor(Color.GREEN);
		} else if(etat.getVitesse()<200) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.RED);
		}
		g.drawString(etat.getVitesse() + "",Etat.LARGEUR-TAILLE_COMPTEUR+30, Etat.HAUTEUR-TAILLE_COMPTEUR/2 + 5);
		g.setColor(Color.BLACK);
		graphics2D.drawImage(chronometer, 0, Etat.HAUTEUR-TAILLE_COMPTEUR, TAILLE_COMPTEUR, TAILLE_COMPTEUR, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.drawString(etat.tempsRestant(), 27, Etat.HAUTEUR-TAILLE_COMPTEUR + 52);
		
		if (etat.isGameOver()) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER", Etat.LARGEUR/2, Etat.HAUTEUR/2);
		}
	}

	@Override
	public void setGraphics2D(Graphics2D g2d) {
		graphics2D = g2d;
	}

}
