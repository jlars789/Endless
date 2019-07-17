package menus;

import java.awt.Graphics2D;

import main.Gamepanel;

public class TitleScreen extends Menu {
	
	private static final byte H_MAX = 0;
	private static final byte V_MAX = 0;

	public TitleScreen() {
		super(H_MAX, V_MAX);
	}
	
	public void select() {
		if(super.getSelector() == 0) {
			Gamepanel.currentMenu = new CharSelect();
		}
	}
	
	public void back() {
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setFont(Menu.MAIN);
		g2d.drawString("Play", 610, 200);
		
		g2d.setFont(Menu.SMALL);
		g2d.drawString("Controls:", 590, 230);
		g2d.drawString("Shooting :Arrow Keys", 525, 255);
		g2d.drawString("Movement: WASD", 525, 275);
		g2d.drawString("Ability 1: E", 525, 295);
		g2d.drawString("Ability 2: LShift", 525, 315);
		g2d.drawString("Special Ability: Q", 525, 335);
		g2d.drawString("Swap Weapons: F", 525, 355);
		g2d.drawString("Reload: R", 525, 375);
		if(super.getSelector() == 0)g2d.drawRect(604, 171, 75, 38);
	}

}
