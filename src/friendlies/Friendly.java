package friendlies;

import java.awt.Graphics2D;
import java.util.ArrayList;

import hostiles.Hostile;
import main.Entity;
import main.EntityLists;
import main.Hitbox;

public abstract class Friendly implements Entity {
	
	private float xCoor;
	private float yCoor;
	private int width;
	private int height;
	
	private double damage;
	private int pierce;
	
	private int entityType;
	
	private int attackTimer;
	private int attackTimerMax;
	
	private Hitbox friendlyHitbox;
	
	//TODO Remove all fields covered by Hitbox class
	
	private int lifespanCounter;
	
	/**
	 * Parent class for friendly entities
	 */
	
	public Friendly(float xCoor, float yCoor, int width, int height, int lifespan) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		
		this.width = width;
		this.height = height;
		
		this.friendlyHitbox = new Hitbox((int)xCoor, (int)yCoor, width, height);
		
		this.lifespanCounter = lifespan;
	}

	public Friendly(float xCoor, float yCoor, int width, int height, double damage, int pierce, int attackTimer, int lifespan) {
		
		//this.mainChar = mainChar;
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = width;
		this.height = height;
		this.friendlyHitbox = new Hitbox((int)xCoor, (int)yCoor, width, height);
		
		this.damage = damage;
		this.pierce = pierce;
		
		this.attackTimer = attackTimer;
		this.attackTimerMax = attackTimer;
		this.lifespanCounter = lifespan;
		
	}
	
	public abstract void action(Hostile enemy);
	
	protected void shoot() {
		if(this.getProjectile() != null) {
			EntityLists.addNew(this.getProjectile());
		}
		this.attackTimer = this.attackTimerMax;
	}
	
	public final void update(double delta) {
		if(this.lifespanCounter > 0) {
			this.lifespanCounter--;
		}
		
		if(this.attackTimer > 0) this.attackTimer--;
		else this.shoot();
		
		if(EntityLists.getList(EntityLists.HOST_IND).size() > 0) this.action((Hostile)((EntityLists.getList(EntityLists.HOST_IND))).get(0));
		
		this.friendlyHitbox.setBounds(xCoor, yCoor, width, height);
	}
	
	public abstract void draw(Graphics2D g);
	
	public float[] getCenterPoint() {
		float[] m = new float[2];
		m[0] = xCoor + (width / 2);
		m[1] = yCoor + (height/2);
		return m;
	}
	
	public void shift(float xDist, float yDist) {
		this.xCoor += xDist;
		this.yCoor += yDist;
	}

	public float getxCoor() {
		return xCoor;
	}

	public void setxCoor(float xCoor) {
		this.xCoor = xCoor;
	}

	public float getyCoor() {
		return yCoor;
	}

	public void setyCoor(float yCoor) {
		this.yCoor = yCoor;
	}
	
	public double getDamage() {
		return this.damage;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getDuration() {
		return this.lifespanCounter;
	}
	
	public int getEntityType() {
		return this.entityType;
	}
	
	public boolean isExpired() {
		if(lifespanCounter > 0) return false;
		else return true;
	}
	
	public float[] getMidpoint() {
		float[] m = new float[2];
		m[0] = (this.xCoor + (this.width / 2));
		m[1] = (this.yCoor + (this.height / 2));
		
		return m;
	}
	
	protected abstract ArrayList<Entity> getProjectile();
	
	public int getPierce() {
		return this.pierce;
	}
	
	public abstract Friendly copy();

	public Hitbox getFriendlyHitbox() {
		return this.friendlyHitbox;
	}

}
