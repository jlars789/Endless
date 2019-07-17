package characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.text.DecimalFormat;

import main.Gamepanel;

public class HUD implements Serializable {
	
	
	private static final long serialVersionUID = -605327409097846835L;
	
	private static final int healthX = Characters.X_BOUNDS - 160;
	private static final int healthY = Characters.Y_BOUNDS;
	private static final int healthWidth = 100;
	private static final int healthHeight= 25;
	
	private static final int ability1X = 60;
	private static final int ability2X = 110;
	private static final int abilityY = Characters.Y_BOUNDS - 10;
	
	//private Characters Gamepanel.mainChar;
	private static Map map;

	public HUD() {
		map = new Map(10);
	}
	
	public void draw(Graphics2D g2d) {
		
		map.draw(g2d);
		
		Font small = new Font("Verdana", Font.PLAIN, 15);
		g2d.setFont(small);
		
		Font abilityFont = new Font("Verdana", Font.PLAIN, 22);
		
		g2d.setColor(Color.RED);
		g2d.fillRect(healthX, healthY, (int)((Gamepanel.mainChar.getStats().getHealth().getValues()[0] * healthWidth) / Gamepanel.mainChar.getStats().getHealth().getValues()[1]), healthHeight);
		
		g2d.setColor(Color.WHITE);
		
		//magazine status
		if(!Gamepanel.mainChar.getHeldWeapon().isReloading()) g2d.drawString("" + Gamepanel.mainChar.getHeldWeapon().getMagazine() + "/" + Gamepanel.mainChar.getHeldWeapon().getMagazineMax(), healthX + 60, healthY - 5);
		if(Gamepanel.mainChar.getHeldWeapon().isReloading()) g2d.drawString("Reloading...", (int)Gamepanel.mainChar.getxCoor() - 10, (int)Gamepanel.mainChar.getyCoor() - 15);
		
		g2d.drawString(Gamepanel.mainChar.getHeldWeapon().weaponName(), healthX, healthY - 20);
		g2d.drawRect(healthX, healthY, healthWidth, healthHeight);
		
		//number display
		g2d.drawString("Health: " + (int)Gamepanel.mainChar.getStats().getHealth().getValues()[0], healthX + 5, healthY + 16);
		
		if(Gamepanel.mainChar.getMoveset().getAbilities()[0].getCooldown() == 0) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect(ability1X, abilityY, 40, 40);
		} else {
			g2d.setColor(Color.GRAY);
			g2d.fillRect(ability1X, abilityY, 40 - (int)(Gamepanel.mainChar.getMoveset().getAbilities()[0].getCooldown() * 40 / Gamepanel.mainChar.getMoveset().getAbilities()[0].getCooldownMax()), 40);
		}
		
		if(Gamepanel.mainChar.getMoveset().getAbilities()[1].getCooldown() == 0) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect(ability2X, abilityY, 40, 40);
		} else {
			g2d.setColor(Color.GRAY);
			g2d.fillRect(ability2X, abilityY, 40 - (int)(Gamepanel.mainChar.getMoveset().getAbilities()[1].getCooldown() * 40 / Gamepanel.mainChar.getMoveset().getAbilities()[1].getCooldownMax()), 40);
		}
		
		if(!Gamepanel.mainChar.getMoveset().getAbilities()[2].getStatus()) {
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(170, abilityY + 5, (int)(Gamepanel.mainChar.getStats().getEnergy() * 150 / Gamepanel.mainChar.getMoveset().getAbilities()[2].getCost()), 20);
			if(Gamepanel.mainChar.getStats().getEnergy() < Gamepanel.mainChar.getMoveset().getAbilities()[2].getCost()) {
				g2d.setColor(Color.WHITE);
			} else {
				g2d.setColor(Color.CYAN);
			}
			g2d.drawRect(170, abilityY + 5, 150, 20);
		} else {
			g2d.setColor(Color.CYAN);
			g2d.fillRect(170, abilityY, (int)(Gamepanel.mainChar.getMoveset().getAbilities()[2].getDurationTimer() * 150 / Gamepanel.mainChar.getMoveset().getAbilities()[2].getDuration()), 20);
		}
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(ability1X, abilityY, 40, 40);
		g2d.drawRect(ability2X, abilityY, 40, 40);
		
		if(Gamepanel.mainChar.getMoveset().getAbilities()[0].getToggle()) {
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(ability1X, abilityY, 40, 40);
		}
		
		if(Gamepanel.mainChar.getMoveset().getAbilities()[1].getToggle()) {
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(ability2X, abilityY, 40, 40);
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		g2d.drawString("Damage: " + df.format(Gamepanel.mainChar.getHeldWeapon().getDamage()), 20, 100);
		g2d.drawString("Attack Speed: " + df.format(Gamepanel.mainChar.getHeldWeapon().getFireRate()), 20, 140);
		g2d.drawString("Speed: " + df.format(Gamepanel.mainChar.getStats().getSpeed()), 20, 180);
		
		g2d.setFont(abilityFont);
		g2d.setColor(Color.BLACK);
		g2d.drawString("E", ability1X + 13, abilityY + 25);
		
		g2d.drawString("LS", ability2X + 6, abilityY + 25);
	}
	
	public static Map getMap() {
		return map;
	}

}
