package menus;

import java.awt.Font;
import java.awt.Graphics2D;

import main.Gamepanel;

public class GameOver extends Menu {
	
	private final static byte H_MAX = 0;
	private final static byte V_MAX = 1;
	
	public GameOver() {
		super(H_MAX, V_MAX);
	}
	
	public void select() {
		if(super.getSelector() == 0) {
			Gamepanel.reset();
			Gamepanel.currentMenu = null;
			Gamepanel.gameRunning = true;
		}
		if(super.getSelector() == 1) {
			Gamepanel.reset();
			Gamepanel.currentMenu = new CharSelect();
		}
	}
	
	public void back() {
	}
	
	public void draw(Graphics2D g2d) {
		
		Font main = new Font("Verdana", Font.PLAIN, 30);
		Font small = new Font("Verdana", Font.PLAIN, 18);
		
		g2d.setFont(main);
		g2d.drawString("Game Over", 345, 80);
		
		g2d.setFont(small);
		g2d.drawString("Play Again", 390, 170);
		g2d.drawString("Change Character", 360, 200);
	
		if(super.getSelector() == 0) g2d.drawRect(388, 153, 100, 23);
		if(super.getSelector() == 1) g2d.drawRect(358, 183, 165, 23);
	}

}
