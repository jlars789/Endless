package projectiles;

import java.awt.Graphics2D;

import abilities.Cowboy.HeavyWhip;
import characters.Characters;
import hostiles.Hostile;
import hostiles.enemies.Enemy;
import main.Gamepanel;
import sprite.LightSprite;
import statuseffect.Shatter;

public class Whip extends Projectile {
	
	private int range;
	
	private Shatter shatter;
	private LightSprite projectileSprite;
	
	public Whip(float xCoor, float yCoor, int direction, HeavyWhip fromAbility) {
		super(xCoor, yCoor, direction, 6, true, 5, fromAbility);
		this.shatter = fromAbility.getEffect().copy();
		this.range = 110;
		this.projectileSprite = fromAbility.getSprite();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.projectileSprite.currentRotated(90 * super.getDirection()), null, (int)super.getxCoor(), (int)super.getyCoor());
	}
	
	@Override
	public void action() {
	}
	
	@Override
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(shatter.copy());
		if(enemy instanceof Enemy) {
			((Enemy)(enemy)).knockBack(50, super.getDirection());
		}
	}
	
	@Override
	public void movement() {
		Characters charRef = Gamepanel.mainChar;
		if(super.getDirection() == 0) {
			super.setBounds((int)charRef.getxCoor() + charRef.getWidth()/2, (int)(charRef.getyCoor() - range), super.getWidth(), range);
		}
		else if(super.getDirection() == 1) {
			super.setBounds((int)charRef.getxCoor() + charRef.getWidth(), (int)charRef.getyCoor() + charRef.getHeight()/2, range, super.getHeight());
		}
		else if(super.getDirection() == 2) {
			super.setBounds((int)charRef.getxCoor() + charRef.getWidth()/2, (int)charRef.getyCoor() + charRef.getHeight(), super.getWidth(), range);
		}
		else if(super.getDirection() == 3) {
			super.setBounds((int)(charRef.getxCoor() - range), (int)charRef.getyCoor() + charRef.getHeight()/2, range, super.getHeight());
		}
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return true;
	}

}
