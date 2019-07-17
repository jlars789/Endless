package menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import characters.Cowboy;
import characters.Diety;
import characters.Ninja;
import characters.Pirate;
import main.Gamepanel;
import sprite.LightSprite;
import sprite.Sprite;

public class CharSelect extends Menu {
	
	private final static byte H_MAX = 2;
	private final static byte V_MAX = 0;
	
	private static Sprite ninja = new LightSprite("imgs/anims/ninja_idle.png");

	public CharSelect() {
		super(H_MAX, V_MAX);
	}
	
	public void select() {
		if(super.getHoriSelector() == 0) {
			Gamepanel.mainChar = new Cowboy();
		}
		if(super.getHoriSelector() == 1) {
			Gamepanel.mainChar = new Ninja();
		}
		if(super.getHoriSelector() == 2) {
			Gamepanel.mainChar = new Pirate();
		}
		
		Gamepanel.currentMenu = new SkillSelect(Gamepanel.mainChar);
	}
	
	public void back() {
	}
	
	public void customKey(int key) {
		if(key == KeyEvent.VK_BACK_SLASH) {
			Gamepanel.mainChar = new Diety();
			Gamepanel.currentMenu = null;
			Gamepanel.gameStart();
		}
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setFont(Menu.MAIN);
		g2d.drawString("Character Select", 510, 50);
		
		g2d.setFont(Menu.SMALL);
		g2d.drawString("Cowboy", 475, 220);
		g2d.drawString("Ninja", 620, 220);
		g2d.drawString("Pirate", 755, 220);
		g2d.drawString("Damage: ", 450, 290);
		g2d.drawString("Fire Rate: ", 450, 320);
		g2d.drawString("Pierce: ", 450, 350);
		g2d.drawString("Speed: ", 450, 380);
		g2d.drawString("Health: ", 450, 410);
		
		if(super.getHoriSelector() == 0) {
			g2d.drawRect(470, 143, 80, 85);
			
			g2d.setColor(Color.RED);
			g2d.fillRect(550, 275, 200, 20);
			
			g2d.setColor(Color.GREEN);
			g2d.fillRect(550, 305, 100, 20);
			
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(550, 335, 25, 20);
			
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(550, 365, 100, 20);
			
			g2d.setColor(Color.MAGENTA);
			g2d.fillRect(550, 395, 100, 20);
		}
		else if(super.getHoriSelector() == 1) {
			g2d.drawRect(600, 90, 85, 140);
			
			g2d.setColor(Color.RED);
			g2d.fillRect(550, 275, 85, 20);
			
			g2d.setColor(Color.GREEN);
			g2d.fillRect(550, 305, 200, 20);
			
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(550, 335, 100, 20);
			
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(550, 365, 200, 20);
			
			g2d.setColor(Color.MAGENTA);
			g2d.fillRect(550, 395, 25, 20);
		}
		else if(super.getHoriSelector() == 2) {
			g2d.drawRect(740, 143, 80, 85);
			
			g2d.setColor(Color.RED);
			g2d.fillRect(550, 275, 200, 20);
			
			g2d.setColor(Color.GREEN);
			g2d.fillRect(550, 305, 25, 20);
			
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(550, 335, 200, 20);
			
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(550, 365, 50, 20);
			
			g2d.setColor(Color.MAGENTA);
			g2d.fillRect(550, 395, 200, 20);
		}
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(495, 150, 30, 50);
		g2d.drawImage(ninja.getImage(), null, 610, 100);
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(765, 150, 30, 50);
	}

}
