package projectiles;

import java.awt.Graphics2D;
import java.util.Random;

import abilities.Cowboy.Trickshot;
import hostiles.Hostile;
import sprite.LightSprite;
import statuseffect.Normal;
import statuseffect.StatusEffect;
import weapons.Dessenger;
import weapons.Minigun;

public class Bullet extends Projectile {
	
	private float accuracy;
	
	private int bounces;
	private boolean released;
	
	private int time;
	
	private Trickshot ability;
	private LightSprite moving;
	
	private StatusEffect effect;


	public Bullet(float xCoor, float yCoor, int direction, Dessenger fromWeapon, float accuracy) {
		super(xCoor, yCoor, direction, 4, false, 8, fromWeapon);
		this.accuracy = accuracy;
		this.moving = fromWeapon.getProjectileSprite().copy();
		this.effect = fromWeapon.getEffect();
		
		this.imageInstantiation();
		
	}
	
	public Bullet(float xCoor, float yCoor, int direction, Minigun fromWeapon, float accuracy) {
		super(xCoor, yCoor, direction, 6, false, 8, fromWeapon);
		this.accuracy = accuracy;
		this.effect = fromWeapon.getEffect();
		this.moving = fromWeapon.getProjectileSprite().copy();
		
		this.imageInstantiation();
	}
	
	public Bullet(float xCoor, float yCoor, int direction, Trickshot fromAbility, float accuracy, int bounces) {
		super(xCoor, yCoor, direction, 6, false, 42, fromAbility);
		this.accuracy = accuracy;
		this.moving = fromAbility.getProjectileSprite().copy();
		this.effect = new Normal();
		this.bounces = bounces;
		this.ability = fromAbility;
		this.imageInstantiation();
	}
	
	public void imageInstantiation() {
		this.moving.rotateImage(90 * super.getDirection());
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.moving.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}
	
	public void action() {
		if(bounces == 0 || released) super.hit();
	}
	
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(this.effect);
	}
	
	protected void movement() {
		
		if(super.getDirection() % 2 == 0) {
			super.setHeight(20);
		} else {
			super.setWidth(20);
		}
		
		super.move(this.accuracy);
		
		if(bounces > 0 && this.ability != null) {
			if(super.hasImpact() && !released && time > 15) {
				this.releaseProjectile();
				super.destroy();
			}
		}
		
		time++;
	}
	
	private void releaseProjectile() {
		int dir = 0;
		if(this.getDirection() > 1) dir = this.getDirection() - 2;
		else dir = this.getDirection() + 2;
		
		float xMod = 0;
		float yMod = 0;
		
		switch(this.getDirection()) {
		case 0:
			yMod = 20;
		break;
		
		case 1:
			xMod = -20;
		break;
		
		case 2:
			yMod = -20;
		break;
		
		case 3:
			xMod = 20;
		break;
		}
		
		if(this.getDirection() % 2 == 1) {
			if(super.outOfBounds()[0]) dir = 2;
			else if(super.outOfBounds()[2]) dir = 0;
		} else {
			if(super.outOfBounds()[1]) dir = 3;
			else if(super.outOfBounds()[3]) dir = 1;
		}
		
		super.recursion(new Bullet(this.getxCoor() + xMod, this.getyCoor() + yMod, dir, this.ability, randomFloat(-10, 10), this.bounces - 1));
		this.released = true;
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return false;
	}
	
	private float randomFloat(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * (max - min)) + min;
	}

}
