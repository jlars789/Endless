package projectiles;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.LightSprite;
import weapons.Scylla;

public class Cannonball extends Projectile {
	
	private static int BLAST_RADIUS = 90;
	private static final int SIZE = 28;
	private static int blastDamage = 30;
	
	private LightSprite moving;
	private boolean detonated;

	public Cannonball(float xCoor, float yCoor, int direction, Scylla fromWeapon) {
		super(xCoor, yCoor, direction, SIZE, false, 2, fromWeapon);
		
		this.moving = fromWeapon.getProjectileSprite().copy();
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(moving.getImage(), null, (int)getxCoor(), (int)getyCoor());
	}
	
	@Override
	public void action() {
		if(!detonated) {
			super.recursion(new Explosion(super.getxCoor(), super.getyCoor(), BLAST_RADIUS, blastDamage, super.getCenterPoints()));
			this.detonated = true;
		} 
	}
	
	public void movement() {
		switch(super.getDirection()) {
		case 0:
			super.moveY(true);
		break;
		
		case 1:
			super.moveX(false);
		break;
		
		case 2: 
			super.moveY(false);
		break;
		
		case 3:
			super.moveX(true);
		break;
		}
	}
	
	
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public void effect(Hostile enemy) {
	}

	@Override
	public boolean lowLayer() {
		return false;
	}

}
