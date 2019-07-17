package characters;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

import abilities.Ability;
import beneficial.Beneficial;
import beneficial.Invincible;
import enemyprojectile.EnemyProjectile;
import floor.room.Room;
import hostiles.Hostile;
import item.Item;
import main.Entity;
import main.Gamepanel;
import main.Hitbox;
import main.Shadow;
import sprite.CharacterSprite;
import sprite.Sprite;
import weapons.Weapon;

/**
 * Class defining the entity controlled directly by the player.
 * @author jlars
 */

public abstract class Characters implements Serializable, Entity {

	private static final long serialVersionUID = 4129067910376306743L;
	
	public static final int X_BOUNDS = Room.X_MAX;
	public static final int Y_BOUNDS = Room.Y_MAX;
	public static final int Y_MIN = Room.Y_MIN;
	public static final int X_MIN = Room.X_MIN;
	
	public static final int DOOR_IMMUNE_TIMER = 60;
	private static final int TURN_TIME = 60;
	
	public static int CHARACTER_WIDTH = (int)(82 * Gamepanel.sizeMult);
	public static int CHARACTER_HEIGHT = (int)(60 * Gamepanel.sizeMult);
	
	public static final int START_XCOOR = (Gamepanel.WIDTH - (CHARACTER_WIDTH/2))/2;
	public static final int START_YCOOR = (Gamepanel.HEIGHT - (CHARACTER_HEIGHT/2))/2;
	
	public static final int IFRAMES = 40;
	
	public static double delta;
	
	private double damageDealtInFrame;
	
	private StatHandler stats;
	
	private int direction;
	private boolean move[] = new boolean[4];
	private boolean shoot;
	
	private boolean doorImmune = false;
	private int doorTimer;
	
	private int turnTimer;
	
	private boolean locked;
	
	private ArrayList<Item> itemInventory;
	private ArrayList<Beneficial> effects;
	
	private HUD hud;
	
	private Hitbox playerHitbox;
	private Weapon heldWeapon;
	private Moveset moves;
	private Controller controls;
	private CharacterSprite characterSprite;
	private Shadow shadow;
	//TODO Create "Sprite Handler" object that holds and manages all character sprites
	
	/**
	 * Parent class for Character Creation
	 * @param healthCap the maximum health of the character
	 * @param speed the pixels traveled per frame
	 */
	
	public Characters(int healthCap, double speed) {
		this.init();
		this.statInstantiate(healthCap, speed);
	}
	
	private void init() {
		this.direction = 2;
		this.playerHitbox = new Hitbox(START_XCOOR, START_YCOOR, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		this.hud = new HUD();
		this.stats = new StatHandler(true);
		this.controls = new Controller(this);
		this.effects = new ArrayList<Beneficial>();
		this.itemInventory = new ArrayList<Item>();
		this.shadow = new Shadow();
	}
	
	private void statInstantiate(int healthCap, double speed) {
		this.stats.healthInstantiate(healthCap);
		
		this.stats.setSpeed(speed, true);
		this.stats.setNaturalSpeed(speed);
	}
	/**
	 * Method that is called every frame, will call other methods and update other Objects.This method will: 
	 * <ul><p>
	 * <li> This method will update the Character's {@link Moveset}
	 * <li> Iterate through all {@link Item} Objects in the {@code itemInventory} ArrayList
	 * <li> Iterate through all {@link Beneficial} Objects in the {@code effects} ArrayList
	 * <li> Rotate the Character's {@link Sprite} depending on direction facing
	 * <li> Call the {@code movement()} and {@code timer()} methods
	 * </ul><p>
	 * @param delta 
	 */
	public final void update(double delta) {
		
		Characters.delta = delta;
		
		if(delta > 0) {
			this.moves.update();
			this.buffIterate();
			this.timer();
		}
		
		if(itemInventory.size() > 0) {
			for(int i = 0; i < itemInventory.size(); i++) {
				if(itemInventory.get(i).isAdded() == false) {
					itemInventory.get(i).add(this);
				} else {
					itemInventory.get(i).lingerEffect(this);
				}
			}
		}
		this.movement(delta);
		
		if(turnTimer > 0 && !shoot) {
			turnTimer--;
		}
		else if(turnTimer == 0) {
			for(int i = 0; i < move.length; i++) {
				if(move[i]) {
					this.setDirectionFromMove(i);
				}
			}
		}
		
		
		this.damageDealtInFrame = 0;
	}
	
	private void buffIterate() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).update();
			if(!effects.get(i).isActive()) {
				effects.remove(i);
			}
		}
	}
	
	/**
	 * Method that handles all of the timers that relate to the character
	 */
	
	private void timer() {
		
		if(doorImmune) doorTimer--;
		if(doorTimer == 0) doorImmune = false;
	
		if(heldWeapon.getMagazine() == 0 && !heldWeapon.isReloading()) {
			heldWeapon.setReloading(true);
			heldWeapon.setReloadTimer(heldWeapon.getReloadSpeed());
		}
		
		heldWeapon.timer();
		//this.heal(999);
	}
	
	/**
	 * Handles the movement of the character
	 */
	
	private void movement(double delta) {
		this.playerHitbox.move(move, (stats.getSpeed() * delta));
		if(shoot && !heldWeapon.isChambered()) {
			heldWeapon.shoot();
		}
	}
	
	/**
	 * Draws the character 
	 * @param g Graphics import
	 */
	
	public void draw(Graphics2D g2d) {
		
		float opacityChange = 0.4f;
		
		shadow.drawChar(g2d);
		
		if(stats.isInvisible()) {
			opacityChange = 0.4f;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityChange));
		}
		g2d.drawImage(this.characterSprite.drawWithDirection(), null, (int)this.playerHitbox.getX(), (int)this.playerHitbox.getY());
		if (opacityChange < 0.7){
			opacityChange = 1;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityChange));
		}
		for(int i = 0; i < itemInventory.size(); i++) {
			itemInventory.get(i).drawCostume(g2d);
		}
		hud.draw(g2d);
	}
	
	/**
	 * Instantly restores health to the Character. 
	 * @param heal Amount of health restored
	 */
	
	public void heal(double heal) {
		stats.getHealth().heal(heal);
	}
	
	/**
	 * Method called when hit by an {@link EnemyProjectile} Object. 
	 * <p>
	 * This method will reduce the health of this Character depending on the damage of the EnemyProjectile parameter.
	 * It will also knock this Character back a set distance upon impact and set this Character to be invincible for a period of time. 
	 * @param e EnemyProjectile that has intersected this Character
	 */
	
	public void hit(EnemyProjectile e) {
		
		double damage = e.getDamage() / stats.getResistance();
		
		this.stats.getHealth().damage(damage);
		
		int direction;
		double xDistLow = Math.abs(e.getxCoor() - (this.getxCoor() + this.getWidth()));
		double xDistHigh = Math.abs(e.getxCoor() - (this.getxCoor()));
		double yDistLow = Math.abs(e.getyCoor() - (this.getyCoor() + this.getHeight()));
		double yDistHigh = Math.abs(e.getyCoor() - (this.getyCoor()));
		
		if(xDistHigh >= xDistLow && xDistHigh >= yDistLow && xDistHigh >= yDistHigh) {
			direction = 1;
		}
		else if(xDistLow >= xDistHigh && xDistLow >= yDistLow && xDistLow >= yDistHigh) {
			direction = 3;
		}
		else if(yDistLow >= xDistHigh && yDistLow >= xDistLow && yDistLow >= yDistHigh) {
			direction = 2;
		} else {
			direction = 0;
		}
		
		if(this.inBounds(30)) {
			this.knockback(direction, 30);
		}
		this.buff(new Invincible(IFRAMES));
	}
	
	/**
	 * Method called when hit by a {@link Hostile} Object. 
	 * <p>
	 * This method will reduce the health of this Character depending on the damage of the Hostile parameter.
	 * It will also knock this Character back a set distance upon impact and set this Character to be invincible for a period of time. 
	 * @param e Hostile that has intersected this Character
	 */
	
	public void hit(Hostile hostile) {
		
		double damage = hostile.getDamage() / stats.getResistance();
		
		int direction;
		double xDistLow = Math.abs(hostile.getxCoor() - (this.playerHitbox.getX() + this.playerHitbox.getWidth()));
		double xDistHigh = Math.abs(hostile.getxCoor() - (this.playerHitbox.getX()));
		double yDistLow = Math.abs(hostile.getyCoor() - (this.playerHitbox.getX() + this.playerHitbox.getHeight()));
		double yDistHigh = Math.abs(hostile.getyCoor() - (this.playerHitbox.getY()));
		
		if(xDistHigh >= xDistLow && xDistHigh >= yDistLow && xDistHigh >= yDistHigh) {
			direction = 1;
		}
		else if(xDistLow >= xDistHigh && xDistLow >= yDistLow && xDistLow >= yDistHigh) {
			direction = 3;
		}
		else if(yDistLow >= xDistHigh && yDistLow >= xDistLow && yDistLow >= yDistHigh) {
			direction = 2;
		} else {
			direction = 0;
		}
		
		this.stats.getHealth().damage(damage);
		if(this.inBounds(30)) {
			this.knockback(direction, 30);
		}
		this.buff(new Invincible(IFRAMES));
	}
	
	public void giveEnergy(Hostile h) {
		if(stats.getEnergy() + h.getPointVal() > moves.getAbilities()[2].getCost()) {
			stats.setEnergy(moves.getAbilities()[2].getCost());
		} else {
			stats.increaseEnergy(h.getPointVal());
		}
	}
	
	public void buff(Beneficial b) {
		b.start();
		effects.add(b);
	}
	
	public void knockback(int direction, int distance) {
		int dist = (int)(distance * delta);
		if(inBounds(dist)) {
			if(direction == 0) {
				this.playerHitbox.incY(-dist);
			}
			else if(direction == 3) {
				this.playerHitbox.incX(dist);
			}
			
			else if(direction == 2) {
				this.playerHitbox.incY(dist);
			}
			
			else {
				this.playerHitbox.incX(-dist);
			}
		}
	}
	
	private boolean inBounds(float distance) {
		if(playerHitbox.getX() - distance < Characters.X_MIN) return false;
		else if((playerHitbox.getX() + distance + playerHitbox.getWidth()) > Characters.X_BOUNDS) return false;
		else if(playerHitbox.getY() - distance < Characters.Y_MIN) return false;
		else if((playerHitbox.getY() + distance + playerHitbox.getHeight()) > Characters.Y_BOUNDS) return false;
		else return true;
	}
	
	public boolean inBounds(float distance, int direction) {
		
		boolean in = false;
		
		switch (direction) {
		case 0:
			if(!(playerHitbox.getY() - distance < Characters.Y_MIN)) in = true;
			break;
		case 1:
			if(!((playerHitbox.getX() + distance + playerHitbox.getWidth()) > Characters.X_BOUNDS)) in = true;
			break;
		case 2:
			if(!((playerHitbox.getY() + distance + playerHitbox.getHeight()) > Characters.Y_BOUNDS)) in = true;
			break;
		case 3:
			if(!(playerHitbox.getX() - distance < Characters.X_MIN)) in = true;
			break;
		}
		
		return in;
		
	}
	
	private boolean[] outOfBounds(double distance) {
		boolean[] bound = {false, false, false, false};
		if(playerHitbox.getY() - distance < Characters.Y_MIN) bound[0] = true;
		if((playerHitbox.getX() + distance + playerHitbox.getWidth()) > Characters.X_BOUNDS) bound[1] = true;
		if((playerHitbox.getY() + distance + playerHitbox.getHeight()) > Characters.Y_BOUNDS) bound[2] = true;
		if(playerHitbox.getX() - distance < Characters.X_MIN) bound[3] = true;
		return bound;
	}
	
	public ArrayList<Item> getInventory() {
		return this.itemInventory; 
	}
	
	public void reset() {
		stats.reset();
		this.heldWeapon.reset();
		this.setLocation(START_XCOOR, START_YCOOR);
		this.itemInventory.clear();
		this.heldWeapon = moves.getWeapons()[0];
	}
	
	public void giveKeyCode(int key) {
		controls.passKeyCode(key);
	}
	
	public float[] getCenterPoint() {
		float[] m = new float[2];
		m[0] = (float)(playerHitbox.getX() + (playerHitbox.getWidth()/2));
		m[1] = (float)(playerHitbox.getY() + (playerHitbox.getHeight()/2));
		return m;
	}
	
	public float getxCoor() {
		return (float)this.playerHitbox.getX();
	}
	
	public void setxCoor(float xCoor) {
		this.playerHitbox.setX(xCoor);
	}
	
	public float getyCoor() {
		return (float) this.playerHitbox.getY();
	}
	
	public void setyCoor(float yCoor) {
		this.playerHitbox.setY(yCoor);
	}
	
	public int getHeight() {
		return (int) this.playerHitbox.getHeight();
	}
	
	public int getWidth() {
		return (int)  this.playerHitbox.getWidth();
	}
	
	public void setLocation(float xCoor, float yCoor) {
		this.playerHitbox.setX(xCoor);
		this.playerHitbox.setY(yCoor);
	}
	
	public void setDirection(int direction) {
		this.setDirectionFromMove(direction);
		this.turnTimer = TURN_TIME;
	}
	
	private void setDirectionFromMove(int direction) {
		this.rotateHitbox(direction);
		this.direction = direction; 
		this.characterSprite.adjustDirection(direction);
	}
	
	private void rotateHitbox(int direction) {
		int t = this.direction + direction;
		if(t % 2 == 1) t = 1;
		else t = 2;
		this.playerHitbox.rotate(t);
	}
	
	public StatHandler getStats() {
		return this.stats;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public Hitbox getPlayerHitbox() {
		return this.playerHitbox;
	}

	public boolean[] getMove() {
		return move;
	}
	
	public int moveIndex() {
		int m = 0;
		for(int i = 0; i < move.length; i++) {
			if(move[i]) {
				m = i;
				break;
			}
		}
		return m;
	}

	public void move(int index) {
		
		if(index < 2) {
			if(!move[index + 2]) {
				this.move[index] = true;
			}
		} else {
			if(!move[index - 2]) {
				this.move[index] = true;
			}
		}
		
	}
	
	public void shift(int direction, float distance) {
		int dist = (int)(distance * delta);
		if(!outOfBounds(distance)[direction]) {
			if(direction == 0) {
				this.playerHitbox.incY(-dist);
			}
			else if(direction == 3) {
				this.playerHitbox.incX(-dist);
			}
			
			else if(direction == 2) {
				this.playerHitbox.incY(dist);
			}
			
			else {
				this.playerHitbox.incX(dist);
			}
		}
	}
	
	public boolean isExpired() {
		return this.getStats().getHealth().dead();
	}
	
	public void stop(int index) {
		this.move[index] = false;
	}
	
	protected void reload() {
		if(this.heldWeapon.getMagazine() < this.heldWeapon.getMagazineMax() && !this.heldWeapon.isReloading()) {
			this.heldWeapon.setReloading(true);
		}
	}
	
	//TODO Make activating an ability while the other is active cancel the ability
	
	protected void abilityActivate(int ability) {
		if(ability != 2) {
			if(this.moves.getAbilities()[ability].isReady() && !this.moves.getAbilities()[ability].getToggle()) this.moves.getAbilities()[ability].activate();
			else if(this.moves.getAbilities()[ability].getToggle()) {
				this.moves.getAbilities()[ability].setToggle(false);
				locked = false;
			}
		} else {
			if(this.stats.getEnergy() >= this.moves.getAbilities()[2].getCost()) {
				this.moves.getAbilities()[2].activate();
			}
		}
	}
	
	protected void abilityKey(int keycode) {
		for(int i = 0; i < this.moves.getAbilities().length; i++) {
			this.moves.getAbilities()[i].giveKeyCode(keycode);
		}
	}

	public boolean isShoot() {
		return shoot;
	}
	
	public void setShoot(boolean shooting) {
		if(!this.heldWeapon.isReloading() && !locked && shooting) {
			this.shoot = shooting;
		}
		else if(!shooting) {
			this.shoot = shooting;
		}
	}
	
	public Weapon getHeldWeapon() {
		return this.heldWeapon;
	}
	
	public void setHeldWeapon(Weapon weaponSet) {
		this.heldWeapon = weaponSet;
	}
	
	public Moveset getMoveset() {
		return this.moves;
	}
	
	public void setMoves(Ability[] a, Weapon[] w) {
		this.moves = new Moveset(a, w);
		this.heldWeapon = w[0];
	}
	
	public void swapWeapons() {
		if(heldWeapon.equals(moves.getWeapons()[0])) {
			moves.getWeapons()[0].swapFrom();
			heldWeapon = moves.getWeapons()[1];
			moves.getWeapons()[1].swapTo();
		} 
		else if(heldWeapon.equals(moves.getWeapons()[1])) {
			moves.getWeapons()[1].swapFrom();
			heldWeapon = moves.getWeapons()[0];
			moves.getWeapons()[0].swapTo();
		}
		else {
			heldWeapon.swapFrom();
			heldWeapon = moves.getWeapons()[0];
			moves.getWeapons()[0].swapTo();
		}
	}
	
	public void setLock(boolean lock) {
		this.locked = lock;
	}

	public void setDoorImmune(boolean doorImmune) {
		this.doorImmune = doorImmune;
		if(doorImmune) doorTimer = 40;
	}
	
	public boolean isDoorImmune() {
		return this.doorImmune;
	}
	
	public void addItem(Item item) {
		itemInventory.add(item);
	}
	
	public void incDamageInFrame(double damage) {
		this.damageDealtInFrame+=damage;
	}
	
	public double getDamageInFrame() {
		return this.damageDealtInFrame;
	}
	
	public Sprite getSprite() {
		return this.characterSprite;
	}
	
	public void setSprite(CharacterSprite sprite) {
		this.characterSprite = sprite;
	}
	
}
