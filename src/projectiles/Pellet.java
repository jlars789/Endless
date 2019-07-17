package projectiles;

import java.awt.Graphics2D;
import java.util.Random;

import hostiles.Hostile;
import sprite.LightSprite;
import weapons.Blunderbuss;

public class Pellet extends Projectile {
	
	private float accuracy;
	private LightSprite moving;
	private static int lifespan = 42;

	public Pellet(float xCoor, float yCoor, int direction, Blunderbuss fromWeapon, float accuracy, LightSprite projectile) {
		super(xCoor, yCoor, direction, 8, true, lifespan, fromWeapon);
		
		super.adjustSpeed(randomFloat(-2, 2));
		this.accuracy = accuracy;
		this.moving = projectile;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(moving.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}
	
	public void action() {
		super.hit();
	}
	
	@Override
	public void movement() {
		
		super.move(accuracy);
		
		if(super.getLifespan() % 8 == 0) {
			super.adjustSpeed(-1);
			super.scale(0.9);
			super.adjustDamage(-2.5);
		}
		
		if(super.getLifespan() % (lifespan/2) == 0) {
			moving.scaleImage(0.5);
		}
	}
	
	private float randomFloat(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * (max - min)) + min;
	}

	@Override
	public void effect(Hostile enemy) {
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return false;
	}

}
