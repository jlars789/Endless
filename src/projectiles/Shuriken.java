package projectiles;

import java.awt.Graphics2D;

import friendlies.ShadowDouble;
import hostiles.Hostile;
import sprite.HeavySprite;
import weapons.ShurikenWeapon;

public class Shuriken extends Projectile {
	
	private static int size = 20;
	private static int lifespan = 75;
	
	private float xSpeed;
	private float ySpeed;
	private double absoluteSpeed;
	private boolean fromChar;
	
	private float accuracy;
	
	private HeavySprite moving;

	public Shuriken(float xCoor, float yCoor, int direction, ShurikenWeapon fromWeapon) {
		super(xCoor, yCoor, direction, size, true, lifespan, fromWeapon);
		this.fromChar = true;
		this.moving = fromWeapon.getProjectileSprite();
	}
	
	public Shuriken(float xCoor, float yCoor, int direction, ShurikenWeapon fromWeapon, float accuracy) {
		super(xCoor, yCoor, direction, size, true, lifespan, fromWeapon);
		this.fromChar = true;
		this.moving = fromWeapon.getProjectileSprite();
		this.accuracy = accuracy;
	}
	
	public Shuriken(float xCoor, float yCoor, float xSpeed, float ySpeed, ShadowDouble fromFriend) {
		super(xCoor, yCoor, xSpeed, ySpeed, size, true, lifespan, fromFriend);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.absoluteSpeed = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));
		this.fromChar = false;
		this.moving = fromFriend.getProjectileSprite().copy();
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(moving.getImage(), null, (int)super.getxCoor() + 10, (int)super.getyCoor());
	}
	
	public void action() {
		super.hit();
	}
	
	public void movement() {
		
		moving.update();
		
		if(fromChar) {
			super.move(accuracy);
		} else {
			super.moveX(-(float)(xSpeed / (absoluteSpeed * .1)));
			super.moveY(-(float)(ySpeed / (absoluteSpeed * .1)));
		}
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
