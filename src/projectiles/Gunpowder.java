package projectiles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import abilities.ProjectileAbility;
import abilities.Pirate.GunpowderAbility;
import hostiles.Hostile;
import main.Gamepanel;
import statuseffect.Detonate;
import statuseffect.Shatter;

public class Gunpowder extends Projectile {
	
	public static int size = 120;
	public Rectangle[] speck = new Rectangle[20];

	public Gunpowder(float xCoor, float yCoor, int direction, ProjectileAbility fromAbility) {
		super(xCoor, yCoor, direction, size, true, 60, fromAbility);
		
		this.createSpecks();
	}
	
	private void createSpecks() {
		int xMod = 20;
		int yMod = 20;
		if(this.getDirection() % 2 == 0) xMod = size;
		else yMod = size;
		
		if(super.getDirection() == 0) {
			super.setBounds((int)Gamepanel.mainChar.getxCoor(), (int)(Gamepanel.mainChar.getyCoor() - size), super.getWidth(), (int)size);
		}
		else if(super.getDirection() == 1) {
			super.setBounds((int)Gamepanel.mainChar.getxCoor() + Gamepanel.mainChar.getWidth(), (int)Gamepanel.mainChar.getyCoor(), (int)size, super.getHeight());
		}
		else if(super.getDirection() == 2) {
			super.setBounds((int)Gamepanel.mainChar.getxCoor(), (int)Gamepanel.mainChar.getyCoor() + Gamepanel.mainChar.getHeight(), super.getWidth(), (int)size);
		}
		else if(super.getDirection() == 3) {
			super.setBounds((int)(Gamepanel.mainChar.getxCoor() - size), (int)Gamepanel.mainChar.getyCoor(), (int)size, super.getHeight());
		}
		
		float startX = super.getxCoor();
		float startY = super.getyCoor();
		
		if(this.getDirection() == 0) startY = super.getyCoor() + size - yMod;
		else if (this.getDirection() == 3) startX = super.getxCoor() + size - xMod;
		
		for(int i = 0; i < speck.length; i++) {
			speck[i] = new Rectangle(randomRange((int)startX, (int)startX + xMod),
					randomRange((int)startY, (int)startY + yMod), 4, 4);
		}
	}

	@Override
	protected void action() {
	}

	@Override
	protected void movement() {
		int xMod = 1;
		int yMod = 1;
		switch (this.getDirection()) {
		case 0:
			yMod = -1;
		break;
		case 3:
			xMod = -1;
		}
		if(this.getDirection() % 2 == 0) {
			for(int i = 0; i < speck.length; i++) {
				speck[i].x += randomRange(-3, 3);
				speck[i].y += yMod * randomRange(1, 4);
			}
		} else {
			for(int i = 0; i < speck.length; i++) {
				speck[i].y += randomRange(-3, 3);
				speck[i].x += xMod * randomRange(1, 4);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		float opacityChange = 0.4f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityChange));
		g2d.setColor(Color.GRAY);
		for(int i = 0; i < speck.length; i++) {
			g2d.fillRect(speck[i].x, speck[i].y, speck[i].width, speck[i].height);
		}
		opacityChange = 1;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityChange));
	}

	@Override
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(new Detonate(GunpowderAbility.explosionSize, GunpowderAbility.explosionDamage));
		enemy.giveStatusEffect(new Shatter(GunpowderAbility.duration, GunpowderAbility.damageMult));
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	protected boolean lowLayer() {
		return false;
	}
	
	private int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

}
