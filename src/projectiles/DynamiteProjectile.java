package projectiles;

import java.awt.Graphics2D;

import abilities.Cowboy.Dynamite;
import characters.Characters;
import hostiles.Hostile;
import hostiles.enemies.Enemy;
import sprite.LightSprite;

public class DynamiteProjectile extends Projectile {

	private static final int BLAST_RADIUS = 200;
	private static final int RANGE = 310;
	private static int fuseTime = 60;
	
	private static int totalFuseTime = 80 + fuseTime;
	
	private int countdownTimer;
	private boolean hit;
	private boolean detonated;
	private int ticks;
	private LightSprite moving;
	
	public DynamiteProjectile(float xCoor, float yCoor, int direction, Dynamite fromAbility, LightSprite projectileSprite) {
		super(xCoor, yCoor, direction, 10, true, totalFuseTime, fromAbility);
		
		this.ticks = RANGE;
		this.moving = projectileSprite;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(moving.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}
	
	@Override
	public void action() {
		
	}
	
	public void effect(Enemy enemy) {
		
	}
	
	private void fuse() {
		if(this.hit == false) {
			super.setSpeed(0);
			this.countdownTimer = fuseTime;
			this.hit = true;
		}
	}
	
	public void movement() {
		if(this.ticks > 0) {
			super.move();
			if(ticks % 25 == 0) {
				super.adjustSpeed(-1);
			}
			this.ticks -= (int)(1.5 * super.getSpeed());
		} else {
			this.fuse();
		}
		if((super.getyCoor() + super.getHeight()) > Characters.Y_BOUNDS) {
			this.bounce(0);
		}
		else if(super.getyCoor() < 0) {
			this.bounce(2);
		}
		else if(super.getxCoor() < 0) {
			this.bounce(1);
		}
		else if((super.getxCoor() + super.getWidth()) > Characters.X_BOUNDS) {
			this.bounce(3);
		}
		
		this.timer();
		
	}
	
	private void bounce(int direction) {
		switch(direction) {
		case 0:
			super.moveY(-20);
		break;
		case 1:
			super.moveX(20);
		break;
		case 2:
			super.moveY(20);
		break;
		default:
			super.moveX(-20);
		break;
		}
	}
	
	private void detonate() {
		super.recursion(new Explosion(super.getxCoor(), super.getyCoor(), BLAST_RADIUS, Dynamite.DAMAGE, super.getCenterPoints()));
		this.detonated = true;
	}
	
	private void timer() {
		if(super.getSpeed() <= 0) {
			if(this.countdownTimer > 0) {
				this.countdownTimer--;
			} else if(!detonated) {
				this.detonate();
				super.destroy();
			}
		}
		
		if(!detonated) {
			
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
