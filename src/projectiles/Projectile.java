package projectiles;

import java.awt.Graphics2D;

import abilities.ProjectileAbility;
import characters.Characters;
import friendlies.Friendly;
import hostiles.Hostile;
import main.Entity;
import main.EntityLists;
import main.Gamepanel;
import main.Hitbox;
import weapons.Weapon;


public abstract class Projectile implements Entity {
	
	private static double delta;
	
	private float projectileSpeed;
	private int pierce;
	private int pierceMax;
	private int direction;
	private double pierceMultipler = 1;
	
	private double damage;
	private int lifespan;
	
	private boolean impact;
	
	private Hitbox projectileHitbox;
	
	//TODO Load all projectile images when starting the game. Do not make them weapon based.
	
	/**
	 * Parent Class for Projectile Creation
	 * @param xCoor The starting x-Coordinate for the projectile
	 * @param yCoor The starting y-Coordinate for the projectile
	 * @param direction The cardinal direction of the projectile
	 * @param width The distance of the rectangle's x-plane
	 * @param height The distance of the rectangle's y-plane
	 * @param impact Whether or not the projectile's effects are active immediately
	 * @param lifespan The duration in which the projectile exists after impact
	 */
	
	public Projectile(float xCoor, float yCoor, int direction, int size, boolean impact, int lifespan) {
		this.direction = direction;
		this.projectileHitbox = new Hitbox((int)xCoor, (int)yCoor, size, size);
		this.impact = impact;
		this.pierce = 1;
		this.lifespan = lifespan;
	}
	
	public Projectile(float xCoor, float yCoor, int direction, int size, boolean impact, int lifespan, Weapon fromWeapon) {
		this(xCoor, yCoor, direction, size, impact, lifespan);
		
		this.projectileSpeed = (float)(fromWeapon.getProjectileSpeed() * Gamepanel.sizeMult);
		this.pierceMax = fromWeapon.getPierce();
		this.pierce = (int)(pierceMax * pierceMultipler);
		this.damage = fromWeapon.getDamage();
		
		this.projectileHitbox = new Hitbox((int)xCoor, (int)yCoor, size, size);
	}
	
	public Projectile(float xCoor, float yCoor, int direction, int size, boolean impact, int lifespan, ProjectileAbility fromAbility) {
		this(xCoor, yCoor, direction, size, impact, lifespan);
		
		this.projectileSpeed = (float)(fromAbility.getProjectileSpeed() * Gamepanel.sizeMult);
		this.pierceMax = fromAbility.getPierce();
		this.pierce = (int)(pierceMax * pierceMultipler);
		this.damage = fromAbility.getDamage();
		
		this.projectileHitbox = new Hitbox((int)xCoor, (int)yCoor, size, size);
	}
	
	public Projectile(float xCoor, float yCoor, float xSpeed, float ySpeed, int size, boolean impact, int lifespan, Friendly fromFriend) {
		this.damage = fromFriend.getDamage();
		this.pierceMax = fromFriend.getPierce();
		this.pierce = pierceMax;
		this.projectileHitbox = new Hitbox((int)xCoor, (int)yCoor, size, size);
		this.impact = impact;
		this.lifespan = lifespan;
	}
	
	public static void setDelta(double delta) {
		Projectile.delta = delta;
	}
	
	/**
	 * Updates the position of the projectile hitbox
	 * @param delta 
	 */
	public void update(double d) {
		
		this.projectileHitbox.update();
		
		if(delta > 0) {
			this.movement();
			this.timer();
		}
		
		if((this.projectileHitbox.getX() + this.projectileHitbox.getWidth()) > Characters.X_BOUNDS || this.projectileHitbox.getX() < Characters.X_MIN) {
			this.impact();
		}
		else if((this.projectileHitbox.getY() + this.projectileHitbox.getHeight()) > Characters.Y_BOUNDS || this.projectileHitbox.getY() < Characters.Y_MIN) {
			this.impact();
		}
	}
	
	/**
	 * Defines what the projectile does upon impact
	 */
	
	protected abstract void action();
	
	/**
	 * The main method for basic projectile movement
	 */
	protected abstract void movement();
	/**
	 * Draws the projectile
	 * @param g Graphics import
	 */
	public abstract void draw(Graphics2D g2d);
	
	/**
	 * Handles the timers used for projectiles
	 */
	private void timer() {
		if(this.impact) {
			this.lifespan--;
		}
	}
	/**
	 * Defines the effects given to enemies hit
	 * @param enemy The enemy that has been hit
	 */
	public abstract void effect(Hostile enemy);
	
	protected void move() {
		switch(this.direction) {
		case 0:
			this.projectileHitbox.incY(-projectileSpeed * delta);
		break;
		case 1:
			this.projectileHitbox.incX(projectileSpeed * delta);
		break;
		case 2:
			this.projectileHitbox.incY(projectileSpeed * delta);
		break;
		case 3:
			this.projectileHitbox.incX(-projectileSpeed * delta);
		break;
		}
	}
	
	protected boolean[] outOfBounds() {
		boolean bounds[] = {false, false, false, false};
		
		if(this.getyCoor() < Characters.Y_MIN) {
			bounds[0] = true;
		}
		else if(this.getyCoor() + this.getHeight() > Characters.Y_BOUNDS) {
			bounds[2] = true;
		}
		if(this.getxCoor() + this.getWidth() > Characters.X_BOUNDS) {
			bounds[1] = true;
		} 
		else if(this.getxCoor() < Characters.X_MIN) {
			bounds[3] = true;
		}
		
		return bounds;
	}
	
	protected void move(float accuracy) {
		switch(this.direction) {
		case 0:
			this.projectileHitbox.incY(-projectileSpeed * delta);
			this.projectileHitbox.incX(accuracy * delta);
		break;
		case 1:
			this.projectileHitbox.incX(projectileSpeed * delta);
			this.projectileHitbox.incY(accuracy * delta);
		break;
		case 2:
			this.projectileHitbox.incY(projectileSpeed * delta);
			this.projectileHitbox.incX(accuracy * delta);
		break;
		case 3:
			this.projectileHitbox.incX(-projectileSpeed);
			this.projectileHitbox.incY(accuracy);
		break;
		}
	}
	
	protected void hit() {
		this.pierce--;
	}
	
	public void impact() {
		this.impact = true;
		this.action();
	}
	
	public void destroy() {
		this.impact = true;
		this.pierce = 0;
	}
	
	protected void setBounds(float xCoor, float yCoor, int width, int height) {
		this.projectileHitbox.setBounds(xCoor, yCoor, width, height);
	}
	
	protected void setLocation(float xCoor, float yCoor) {
		this.projectileHitbox.setLocation((int)xCoor, (int)yCoor);
	}
	
	protected void setxCoor(float xCoor) {
		this.projectileHitbox.setX(xCoor);
	}
	
	protected float getxCoor() {
		return (float) this.projectileHitbox.getX();
	}
	
	protected void setyCoor(float yCoor) {
		this.projectileHitbox.setY(yCoor);
	}
	
	protected float getyCoor() {
		return (float) this.projectileHitbox.getY();
	}
	
	protected float[] getCenterPoints() {
		return this.projectileHitbox.getCenterPoints();
	}
	
	protected void moveX(float dist) {
		this.projectileHitbox.incX(dist);
	}
	
	protected void moveY(float dist) {
		this.projectileHitbox.incY(dist);
	}
	
	protected void moveX(boolean neg) {
		if(!neg) this.projectileHitbox.incX(this.projectileSpeed);
		else this.projectileHitbox.incX(-this.projectileSpeed);
	}
	
	protected void moveY(boolean neg) {
		if(!neg) this.projectileHitbox.incY(this.projectileSpeed);
		else this.projectileHitbox.incY(-this.projectileSpeed);
	}
	
	protected void setHeight(int height) {
		this.projectileHitbox.setHeight(height);
	}
	
	protected void setWidth(int width) {
		this.projectileHitbox.setWidth(width);
	}
	
	protected void scale(double scalar) {
		this.projectileHitbox.scale(scalar);
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	protected void adjustDamage(double damage) {
		this.damage += damage;
	}
	
	protected int getWidth() {
		return (int) this.projectileHitbox.getWidth();
	}
	
	protected int getHeight() {
		return (int) this.projectileHitbox.getHeight();
	}
	
	protected void setPierce(int pierce) {
		this.pierce = pierce;
	}
	
	public boolean isExpired() {
		return (this.pierce <= 0 || this.lifespan <= 0);
	}
	
	protected float getSpeed() {
		return (float)(this.projectileSpeed * delta);
	}
	
	protected void setSpeed(float speed) {
		this.projectileSpeed = speed;
	}
	
	protected void adjustSpeed(float speed) {
		this.projectileSpeed += speed;
	}
	
	protected int getDirection() {
		return this.direction;
	}
	
	protected void setDirection(int direction) {
		this.direction = direction;
	}
	
	public Hitbox getFullHitbox() {
		return this.projectileHitbox;
	}
	
	protected int getLifespan() {
		return this.lifespan;
	}
	
	public abstract ProjectileType getType();
	
	protected abstract boolean lowLayer();
	
	private boolean aboveEnemies() {
		return false;
	}
	
	public byte getLayer() {
		if(aboveEnemies()) return 2;
		else if(!lowLayer()) return 1;
		else return 0;
	}
	
	protected void recursion(Projectile p) {
		EntityLists.addNew(p);
	}
	
	protected boolean hasImpact() {
		return this.impact;
	}

}
