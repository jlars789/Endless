package hostiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import characters.Characters;
import friendlies.Friendly;
import hostiles.bosses.Boss;
import hostiles.enemies.Attack;
import main.Entity;
import main.EntityLists;
import main.Gamepanel;
import main.HealthBar;
import main.Hitbox;
import main.StatTracker;
import projectiles.Projectile;
import projectiles.ProjectileType;
import sprite.LightSprite;
import sprite.Sprite;
import statuseffect.StatusEffect;

/**
 * Abstract class for creating Hostiles. Hostiles are entities that can interact with the player by damaging or 
 * hindering the Character. 
 * @author jlars
 */

public abstract class Hostile implements Entity {
	
	private static final float EVAL_RANGE = 70;
	
	private float xSpeed;
	private float ySpeed;
	
	private ArrayList<Projectile> hitBy; //ArrayList that contains projectiles that have hit enemies
	private ArrayList<StatusEffect> effects;
	private Seek force;
	
	private LightSprite hostileSprite;
	
	private HealthBar health;
	
	private float speed;
	private int pointVal; //the points given by killing one
	
	private double damage; //damage dealt by the enemy
	private double damageMult; //damage multiplier that modifies damage done to player
	private double resist;
	
	//TODO find some way to make the projectile system work better
	
	private Characters charRef;
	private Friendly allyRef;
	
	private boolean distracted;
	private boolean locked;
	
	private Color mainColor;
	
	private Hitbox pureHitbox;
	private Rectangle evalBox;
	private Attack[] moveset;
	
	private int ID;
	
	//TODO Create abstract method that returns default values for certain stats

	public Hostile(float xCoor, float yCoor, int size, double damage, int health, float speed, int pointVal) {
		
		this.damage = damage;
		
		this.damageMult = 1;
		this.resist = 1;
		
		this.health = new HealthBar(health);
		
		this.speed = (float)(speed * Gamepanel.sizeMult);
		this.pointVal = pointVal;
		
		this.charRef = Gamepanel.mainChar;
		
		this.instantiate(xCoor, yCoor, size);
	}
	
	public Hostile(float xCoor, float yCoor, int size) {
		
		this.health = new HealthBar();
		
		this.damageMult = 1;
		this.resist = 1;
	
		this.charRef = Gamepanel.mainChar;
		this.instantiate(xCoor, yCoor, size);
	}
	
	private void instantiate(float x, float y, int size) {
		this.damageMult = 1;
		this.resist = 1;
		
		this.pureHitbox = new Hitbox((int)x, (int)y, size, size);
		
		this.evalBox = new Rectangle((int)(x - (EVAL_RANGE/2)), (int)(y - (EVAL_RANGE/2)), (int)(size + EVAL_RANGE), (int)(size + EVAL_RANGE));
		
		this.hitBy = new ArrayList<Projectile>();
		this.effects = new ArrayList<StatusEffect>();
		this.force = new Seek(this, 1);
		this.ID = randomRange(-1000, 1000);
	}
	
	/**
	 * @param delta 
	 * 
	 */
	
	public final void update(double delta) {
		
		if(delta > 0) {
			this.behavior();
			this.action(delta);
			this.projectileIterate();
		
			if(effects.size() > 0) {
				this.getEffects();
			}
		}
		
		this.evalBox.setLocation((int)(this.pureHitbox.getX() - (EVAL_RANGE/2)),
				(int)(this.pureHitbox.getY() - (EVAL_RANGE/2)));
	}
	
	/**
	 * Used for custom behavior or actions the Hostile can take. Will always be called. Leaving nothing will result in default behavior.
	 */
	
	protected abstract void behavior();
	
	
	/**
	 * Method that handles the primary behavior of the enemy. By default, this method includes checking intersection with the player and damaging
	 * them if intersection does occur. It will also invoke the Hostile's {@code force} object to act, assuming the enemy is a {@code CHASING_ENEMY}
	 * Finally, this method will check collisions with other Hostiles and the boundaries of the Room.
	 */
	
	private void action(double delta) {
		
		if(this.pureHitbox.intersects(Gamepanel.mainChar.getPlayerHitbox())) {
			if(!Gamepanel.mainChar.getStats().isInvincible()) Gamepanel.mainChar.hit(this);
			this.damage(Gamepanel.mainChar.getStats().getSpikeDamage());
		}
		
		if(moveset != null) {
			for(int i = 0; i < moveset.length; i++) {
				moveset[i].update();
			}
		}
		
		if(this.inBounds()) {
			if(this.getBehavior().equals(Behavior.CHASING) && !Gamepanel.mainChar.getStats().isInvisible()) {
				force.applyForce();
				
				this.xSpeed = (force.getVel()[0]);
				this.ySpeed = (force.getVel()[1]);
				
				this.pureHitbox.incX(xSpeed * delta);
				this.pureHitbox.incY(ySpeed * delta);
				
				for(int i = 0; i < EntityLists.getHostileList().size(); i++) {
					if(!EntityLists.getHostileList().get(i).equals(this)) {
						
						if(this.pureHitbox.intersects(EntityLists.getHostileList().get(i).getFullHitbox())) {
							this.collide(EntityLists.getHostileList().get(i));
						}
						else if(this.force.pathIntersects(EntityLists.getHostileList().get(i))) {
							this.force.avoid();
						} 
						else if(this.force.freePath(EntityLists.getHostileList())) {
							this.force.chase();
						}
						else if(!this.force.freePath(EntityLists.getHostileList())) {
							this.force.separate();
						}
					} else if(EntityLists.getHostileList().size() == 1) {
						this.force.chase();
					}
				}
			
			}
			if(allyRef != null) {
				this.determine();
			} else {
				if(distracted) distracted = false;
			}
		} 
		else if(!this.getBehavior().equals(Behavior.PHASE)) {
			if(this.pureHitbox.getX() < 0) shiftX(speed);
			if(this.pureHitbox.getY() < 0) shiftY(speed);
			if((this.pureHitbox.getX() + this.pureHitbox.getWidth()) > Characters.X_BOUNDS) shiftX(-speed);
			if((this.pureHitbox.getY() + this.pureHitbox.getHeight()) > Characters.Y_BOUNDS) shiftY(-speed);
		}
		
		this.pureHitbox.update();
	}
	
	/*
	public static void sortLocationInPack() {
		ArrayList<Hostile> newArr = new ArrayList<Hostile>();
		
		for(int i = 0; i < Gamepanel.getEnemySize(); i++) {
			for(int j = 0; j < Gamepanel.getEnemySize(); j++) {
				if(i != j) {
					if(Gamepanel.getEnemyIndex(i).getxCoor() < Gamepanel.getEnemyIndex(j).getxCoor()) {
						
					}
				}
			}
		}
	}
	*/
	
	private void collide(Hostile h) {
		if(!h.colliding()) {
			force.collide(this, h);
		}
	}
	
	/**
	 * Used for determining whether to chase a friendly or the player. Will go after the closest entity
	 */
	
	private void determine() {
		double allyDifX = Math.abs(this.pureHitbox.getX() - allyRef.getxCoor());
		double allyDifY = Math.abs(this.pureHitbox.getY() - allyRef.getyCoor());
		double allyAbsDif = Math.sqrt((allyDifX * allyDifX) + (allyDifY * allyDifY));
		
		double charDifX = Math.abs(this.pureHitbox.getX() - charRef.getxCoor());
		double charDifY = Math.abs(this.pureHitbox.getY() - charRef.getyCoor());
		double charAbsDif = Math.sqrt((charDifX * charDifX) + (charDifY * charDifY));
		
		if((allyAbsDif > charAbsDif) && !charRef.getStats().isInvisible()) {
			distracted = false;
		} 
		else if(charRef.getStats().isInvisible()) {
			distracted = true;
		}
	}
	
	/**
	 * Iterates through all Projectiles in the game.
	 * Projectiles that intersect this enemy will damage the enemy, apply any effects the projectile may give,
	 * Invoke the {@code impact()} method on projectiles, and add the Projectile to the {@code hitBy} ArrayList,
	 * assuming the projectile is not {@code BEAM} type
	 * <p>
	 * This is the only method in which player damage is used and applied
 	 */
	
	private final void projectileIterate() {
		
		if(EntityLists.getProjectileList().size() > 0) {
			for(int i = 0; i < EntityLists.getProjectileList().size(); i++) {
				if(EntityLists.getProjectileList().get(i).getFullHitbox().intersects(evalBox)) {
					
					Projectile p = EntityLists.getProjectileList().get(i);
					
					if(!this.checkHit(p)) {
						if(p.getFullHitbox().intersects(this.pureHitbox)) {
							this.damage(p.getDamage());
							p.effect(this);
							p.impact();
							if(p.getType().compareTo(ProjectileType.BEAM_PROJECTILE) < 0) {
								this.hitBy(p);
							}
						}
					}
					
				}
			}
		}
	}
	
	/**
	 * Draws the enemy
	 * @param g Graphics import
	 */
	
	public void draw(Graphics2D g) {
		
		if(this.hostileSprite == null && this.mainColor != null) {
			g.setColor(mainColor);
			g.fillRect((int)this.pureHitbox.getX(), (int)this.pureHitbox.getY(), (int) this.pureHitbox.getWidth(), 
					(int) this.pureHitbox.getHeight());
			g.setColor(Color.BLACK);
			g.drawRect((int)this.pureHitbox.getX(), (int)this.pureHitbox.getY(), (int) this.pureHitbox.getWidth(), 
					(int) this.pureHitbox.getHeight());
		} else if(this.hostileSprite != null) {
			if(this.getBehavior().equals(Behavior.CHASING) || this.getBehavior().equals(Behavior.TIMID)) {
				g.drawImage(this.hostileSprite.currentRotated(this.getAngleFacing()), null, 
						(int)this.pureHitbox.getX(), (int)this.pureHitbox.getY());
			} else {
				g.drawImage(this.hostileSprite.getImage(), null, (int)this.pureHitbox.getX(), (int)this.pureHitbox.getY());
			}
		}
		if(effects.size() > 0) {
			for(int i = 0; i < effects.size(); i++) {
				effects.get(i).draw(g);
			}
		}
		
		//g.drawRect(evalBox.x, evalBox.y, evalBox.width, evalBox.height);
		//g.drawString(this.health.getValues()[0] + "", (int)this.getxCoor(), (int)this.getyCoor());
		
		//g.drawLine((int)this.getCenterPoint()[0], (int)this.getCenterPoint()[1], (int)force.terminus()[0], (int) force.terminus()[1]);
		
	}
	
	/**
	 * Used for aiming and creating a projectile with enemies that shoot
	 */
	protected void shoot(ArrayList<Entity> shots) {
		if(!this.locked) {
			EntityLists.addNew(shots);
		}
	}
	
	public void kill() {
		this.getHealth().setHealth(0);
	}
	
	protected double getAngleFacing() {
		double dx = Gamepanel.mainChar.getCenterPoint()[0] - this.getCenterPoint()[0];
		double dy = -(Gamepanel.mainChar.getCenterPoint()[1] - this.getCenterPoint()[1]);
		return Math.toDegrees(Math.atan2(dx, dy));
	}
	
	/**
	 * Gives a status effect to an enemy
	 * @param effect StatusEffect object
	 */
	
	public void giveStatusEffect(StatusEffect effect) {
		effect.giveEffect(this);
		effects.add(effect);
	}
	
	/**
	 * Adds a projectile that has hit the enemy
	 * @param hit Projectile that has hit the enemy
	 */
	
	private void hitBy(Projectile hit) {
		hitBy.add(hit);
		StatTracker.shotsHit++;
	}
	
	private boolean checkHit(Projectile hit) {
		return (hitBy.contains(hit));
	}
	
	/**
	 * The damage dealt to the enemy
	 * @param damage Value that is removed from the enemy's current health
	 */
	
	public void damage(double damage) {
		damage /= resist;
		this.health.damage(damage);
		Gamepanel.mainChar.incDamageInFrame(damage);
	}
	
	private void getEffects() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).update();
			if(!effects.get(i).isActive()) {
				effects.remove(i);
			}
		}
		
	}
	
	public void knockback(int direction, float distance) {
		if(!(this instanceof Boss)) {
			this.shift(direction, distance);
		}
	}
	
	/**
	 * Moves the Hostile in any given cardinal direction with a certain distance
	 * @param direction Cardinal direction (0: up, 1: right, 2: down, 3: left)
	 * @param distance Pixels traveled in shift
	 */
	
	protected void shift(int direction, float distance) {
		if(direction == 0) this.pureHitbox.incY(-distance);
		else if(direction == 1) this.pureHitbox.incX(distance);
		else if(direction == 2) this.pureHitbox.incY(distance);
		else if(direction == 3) this.pureHitbox.incX(-distance);
	}
	
	protected void shiftX(float distance) {
		this.pureHitbox.incX(distance);
	}
	
	protected void shiftY(float distance) {
		this.pureHitbox.incY(distance);
	}
	
	/**
	 * Checks to see if the Hostile is within the boundaries of the Room
	 * @return {@code true} if Hostile is within boundaries, {@code false} if any portion of their Hitbox has exited the bounds
	 */
	
	private boolean inBounds() {
		if(this.pureHitbox.getX() < 0) return false;
		else if((this.pureHitbox.getX() + this.pureHitbox.getWidth()) > Characters.X_BOUNDS) return false;
		else if(this.pureHitbox.getY() < 0) return false;
		else if((this.pureHitbox.getY() + this.pureHitbox.getHeight()) > Characters.Y_BOUNDS) return false;
		else return true;
	}
	
	protected boolean[] outOfBounds(double distance) {
		boolean[] bound = {false, false, false, false};
		if(pureHitbox.getY() - distance < Characters.Y_MIN) bound[0] = true;
		if((pureHitbox.getX() + distance + pureHitbox.getWidth()) > Characters.X_BOUNDS) bound[1] = true;
		if((pureHitbox.getY() + distance + pureHitbox.getHeight()) > Characters.Y_BOUNDS) bound[2] = true;
		if(pureHitbox.getX() - distance < Characters.X_MIN) bound[3] = true;
		return bound;
	}
	
	protected void setStats(double health, double damage, float speed, int pointVal) {
		this.health = new HealthBar(health);
		this.damage = damage;
		this.speed = (float)(speed * Gamepanel.sizeMult);
		this.pointVal = pointVal;
	}
	
	public int getWidth() {
		return (int) this.pureHitbox.getWidth();
	}
	
	public int getHeight() {
		return (int) this.pureHitbox.getHeight();
	}
	
	public float getxCoor() {
		return (float) this.pureHitbox.getX();
	}
	
	public float getyCoor() {
		return (float) this.pureHitbox.getY();
	}
	
	public float[] getCenterPoint() {
		float[] m = new float[2];
		m[0] = (float)(this.pureHitbox.getX() + (this.pureHitbox.getWidth()/2));
		m[1] = (float)(this.pureHitbox.getY() + (this.pureHitbox.getHeight()/2));
		return m;
	}
	
	@SuppressWarnings("unused")
	private float[] getVel() {
		float[] vel = new float[2];
		vel[0] = this.xSpeed;
		vel[1] = this.ySpeed;
		
		return vel;
	}
	
	private boolean colliding() {
		return this.force.isColliding();
	}
	
	protected void setAttack(Attack[] attacks) {
		this.moveset = attacks;
	}
	
	protected Attack getAttack(int i) {
		return moveset[i];
	}
	
	@SuppressWarnings("unused")
	private boolean withinRange(Hostile h2) {
		
		boolean inRange = false;
		
		if(h2.getFullHitbox().intersects(this.evalBox) && !h2.equals(this)) {
			inRange = true;
		}
		
		return inRange;
		
	}
	
	protected boolean isDistracted() {
		return this.distracted;
	}
	
	public boolean canShoot() {
		return !this.locked;
	}
	
	public void setLock(boolean t) {
		this.locked = t;
	}
	
	protected Hitbox getFullHitbox() {
		return pureHitbox;
	}
	
	protected HealthBar getHealth() {
		return this.health;
	}
	
	public boolean isExpired() {
		return health.dead();
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = (float)(speed * Gamepanel.sizeMult);
	}
	
	protected abstract Behavior getBehavior();
	
	public double getDamage() {
		return this.damage * damageMult;
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public int getPointVal() {
		return this.pointVal;
	}
	
	public void setResistance(double resist) {
		this.resist = resist;
	}
	
	public double getResistance() {
		return this.resist;
	}
	
	public double getDamageMult() {
		return damageMult;
	}
	
	public void setDamageMult(double damageMult) {
		this.damageMult = damageMult;
	}
	
	protected Friendly getAllyRef() {
		return this.allyRef;
	}
	
	protected Characters getCharRef() {
		return this.charRef;
	}
	
	public void setAllyRef(Friendly friend) {
		this.allyRef = friend;
	}
	
	public void setCharacter(Characters mainChar) {
		this.charRef = mainChar;
	}
	
	protected void setColor(Color c) {
		this.mainColor = c;
	}
	
	public ArrayList<Hostile> hostilesInProximity(float prox){
		float proximity = this.getWidth() + prox;
		Rectangle proxim = new Rectangle((int)(this.getxCoor() - (proximity/2)), (int)(this.getyCoor() - (proximity/2)),
				(int)(this.getWidth() + proximity), (int)(this.getHeight() + proximity));
		
		ArrayList<Hostile> h = new ArrayList<Hostile>();
		
		for(int i = 0; i < EntityLists.getHostileList().size(); i++) {
			if(EntityLists.getHostileList().get(i).getFullHitbox().intersects(proxim)) {
				if(!EntityLists.getHostileList().get(i).equals(this)) {
					h.add(EntityLists.getHostileList().get(i));
				}
			}
		}
		
		return h;
	}
	
	protected int getID() {
		return this.ID;
	}
	
	protected void setSprite(LightSprite sprite) {
		this.hostileSprite = sprite;
	}
	
	protected Sprite getSprite() {
		return this.hostileSprite;
	}
	
	public boolean equals(Hostile h2) {
		return (this.ID == h2.getID());
	}
	
	private int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public abstract Hostile copy();

}
