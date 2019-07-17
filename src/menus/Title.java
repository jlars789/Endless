package menus;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import main.Gamepanel;
import sprite.LightSprite;

public class Title extends Menu {
	
	private static final LightSprite CHRONUS = new LightSprite("imgs/menu/chronus.png");
	
	private static final byte H_MAX = 0;
	private static final byte V_MAX = 0;

	public Title() {
		super(H_MAX, V_MAX);
	}

	@Override
	public void select() {
		Gamepanel.currentMenu = new TitleScreen();
	}

	@Override
	public void back() {

	}

	@Override
	public void draw(Graphics2D g2d) {
		float opacityChange = 0.7f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityChange));
		g2d.drawImage(CHRONUS.getImage(), null, 550, 200);
	}

}
