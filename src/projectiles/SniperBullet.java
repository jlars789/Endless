package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Cowboy.Continuum;
import hostiles.Hostile;
import statuseffect.Normal;
import statuseffect.StatusEffect;
import weapons.Hawk;

public class SniperBullet extends Projectile {
	
	private StatusEffect effect;

	public SniperBullet(float xCoor, float yCoor, int direction, Hawk fromWeapon) {
		super(xCoor, yCoor, direction, 3, false, 8, fromWeapon);
		this.effect = fromWeapon.getEffect();
	}
	
	public SniperBullet(float xCoor, float yCoor, int direction, Continuum fromAbility) {
		super(xCoor, yCoor, direction, 3, false, 100, fromAbility);
		this.effect = new Normal();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)super.getxCoor(), (int)super.getyCoor(), super.getWidth(), super.getHeight());
	}
	
	public void action() {
		super.hit();
	}
	
	public void movement() {
		if(super.getDirection() % 2 == 0) {
			super.setHeight(60);
		} else {
			super.setWidth(60);
		}
		super.move();
	}
	
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(effect);
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
