package enemyprojectile;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import characters.Characters;
import main.Entity;
import main.EntityLists;
import main.Gamepanel;
import main.Hitbox;
import projectiles.ProjectileType;
import sprite.LightSprite;
import sprite.Sprite;

public abstract class EnemyProjectile implements Entity {
	
	private double damage;
	
	private float xSpeed;
	private float ySpeed;
	private double absoluteSpeed;
	private int lifespan;
	
	private double scalar;
	private boolean impact;
	
	private static Sprite projectileSprites[] = new Sprite[2];

	private Hitbox projectileHitbox;
	
	/**
	 * Parent class for enemy projectile creation
	 * @param xCoor Staring x-coordinate of the projectile
	 * @param yCoor Starting y-coordinate of the projectile
	 * @param xSpeed Speed in which the projectile moves horizontally
	 * @param ySpeed Speed in which the projectile moves vertically
	 * @param damage Damage that the projectile deals upon impact
	 */
	
	public EnemyProjectile(float xCoor, float yCoor, int size, float xSpeed, float ySpeed, double damage, double scalar) {
		
		this.projectileHitbox = new Hitbox(xCoor, yCoor, size, size);
		
		this.xSpeed = (float)(xSpeed * Gamepanel.sizeMult);
		this.ySpeed = (float)(ySpeed * Gamepanel.sizeMult);
		this.absoluteSpeed = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));
		this.scalar = scalar;
		
		this.damage = damage;
		this.impact = false;
	}
	
	protected void impact() {
		this.impact = true;
		this.action();
	}
	
	/**
	 * Defines what happens upon impacting an object
	 */
	
	protected abstract void action();
	
	/**
	 * Handles the movement of the projectile
	 * @param delta 
	 */
	
	public void update(double delta) {
		if(delta > 0) this.lifespan--;
		
		int mod = 1;
		if(!(lifespan > 20)) mod = 2;
		
		this.projectileHitbox.incY(-(ySpeed/(absoluteSpeed * (scalar * mod))) * delta);
		this.projectileHitbox.incX(-(xSpeed/(absoluteSpeed * (scalar * mod))) * delta);
		
		this.projectileHitbox.update();
		
		this.charScan();
		
		if(!this.inBounds()) {
			this.impact = true;
		}
		if(this.lifespan <= 0) {
			this.impact = true;
		}
	}
	
	private void charScan() {
		if(this.projectileHitbox.intersects(Gamepanel.mainChar.getPlayerHitbox())) {
			if(!Gamepanel.mainChar.getStats().isInvincible()) {
				Gamepanel.mainChar.hit(this);
			}
			this.impact();
		}
		
		for(int i = 0; i < EntityLists.getProjectileList().size(); i++) {
			if(EntityLists.getProjectileList().get(i).getType().compareTo(ProjectileType.ORBITAL_PROJECTILE) == 0) {
				if(this.projectileHitbox.intersects(EntityLists.getProjectileList().get(i).getFullHitbox())) {
					this.impact();
				}
			}
		}
		
		for(int i = 0; i < EntityLists.getFriendList().size(); i++) {
			if(this.projectileHitbox.intersects(EntityLists.getFriendList().get(i).getFriendlyHitbox())) {
				this.impact();
			}
		}
	}
	
	private boolean inBounds() {
		if(projectileHitbox.getX() < 0) return false;
		else if((projectileHitbox.getX() + projectileHitbox.getWidth()) > Characters.X_BOUNDS) return false;
		else if(projectileHitbox.getY() < 0) return false;
		else if((projectileHitbox.getY() + projectileHitbox.getHeight()) > Characters.Y_BOUNDS) return false;
		else return true;
	}
	
	/**
	 * Draws the character
	 * @param g Graphics import
	 */
	
	public abstract void draw(Graphics2D g2d);
	
	public static void loadImages() {
		projectileSprites[0] = new LightSprite("imgs/enemyprojectiles/spit.png");
		projectileSprites[1] = new LightSprite("imgs/enemyprojectiles/bigball.png");
	}
	
	public static Sprite getSprite(int index) {
		return projectileSprites[index].copy();
	}
	
	protected void kill() {
		this.impact = true;
		this.lifespan = 0;
	}
	
	public boolean isExpired() {
		return this.impact;
	}

	public float getxCoor() {
		return (float) projectileHitbox.getX();
	}
	
	public float getyCoor() {
		return (float) projectileHitbox.getY();
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public double getSpeed() {
		return this.absoluteSpeed;
	}
	
	public void setLifespan(int life) {
		this.lifespan = life;
	}

	public Rectangle getProjectileHitbox() {
		return projectileHitbox;
	}

	public void setProjectileHitbox(Hitbox projectileHitbox) {
		this.projectileHitbox = projectileHitbox;
	}

}
