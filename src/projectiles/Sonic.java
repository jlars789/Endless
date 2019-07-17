package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Pirate.SonicBoom;
import hostiles.Hostile;
import hostiles.enemies.Enemy;
import statuseffect.Stun;

public class Sonic extends Projectile {
	
	private Stun stun;	
	private static final int LIFESPAN = 22;

	public Sonic(float xCoor, float yCoor, int direction, SonicBoom fromAbility) {
		super(xCoor, yCoor, direction, 40, true, LIFESPAN, fromAbility);
		this.stun = fromAbility.getEffect();
		if(super.getDirection() % 2 == 0) {
			super.moveX(-30);
			super.setWidth(100);
		} else {
			super.moveY(-30);
			super.setHeight(100);
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)super.getxCoor(), (int)super.getyCoor(), super.getWidth(), super.getHeight());
	}
	
	public void action() {
		super.hit();
	}
	
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(stun.copy());
		if(enemy instanceof Enemy) {
			((Enemy)(enemy)).knockBack(LIFESPAN - (super.getLifespan()) * 10, super.getDirection());
		}
	}
	
	public void movement() {
		super.move();
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
