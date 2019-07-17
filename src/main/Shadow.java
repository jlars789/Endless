package main;

import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;

public class Shadow {
	
	public static final int SIZE = 21;
	private static final LightSprite SHADOW = new LightSprite("imgs/anims/shadow.png", 4);
	
	private float[] disp = new float[2];

	public Shadow() {
	}
	
	public Shadow(double scalar) {
		//TODO make argument the size of the entity
		SHADOW.scaleImage(scalar);
	}
	
	private void center() {
		Characters c = Gamepanel.mainChar;
		switch(c.getDirection()) {
		case 0:
			disp[0] = c.getxCoor();
			disp[1] = c.getyCoor() - (c.getHeight()/4);
		break;
		
		case 1:
			disp[0] = c.getxCoor() - (c.getWidth()/8);
			disp[1] = c.getyCoor();
		break;
		
		case 2:
			disp[0] = c.getxCoor();
			disp[1] = c.getyCoor() - (c.getHeight()/8);
		break;
		
		case 3:
			disp[0] = c.getxCoor() - (c.getWidth()/4);
			disp[1] = c.getyCoor();
		break;
		
		default:
			disp[0] = c.getxCoor();
			disp[1] = c.getyCoor();
		}
	}
	
	public void drawChar(Graphics2D g2d) {
		this.center();
		g2d.drawImage(SHADOW.getImage(), null, (int)disp[0], (int)disp[1]);
	}
	
	

}
