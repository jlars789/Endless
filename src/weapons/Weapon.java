package weapons;

import java.io.Serializable;
import java.util.ArrayList;

import characters.Characters;
import main.Entity;
import main.EntityLists;
import main.Gamepanel;
import main.StatTracker;
import sprite.Sprite;
import statuseffect.Normal;
import statuseffect.StatusEffect;


/**
 * The animation used for the weapon. This will pull the appropriate animations for the respective character
 * Fields include
 * <ul><p>
 * <li> HANDGUN
 * <li> RIFLE
 * <li> HEAVY
 * <li> MELEE
 * <li> THROWN
 * </ul>
 */

enum AnimationType {
	HANDGUN, RIFLE, HEAVY, MELEE, THROWN
}

/**
 * Field used for Weapon Instantiation. Certain weapon types do not have relevant or modifiable fields. 
 * <p> All weapons must include {@code damage}, {@code fireRate}, and {@code projSpeed}. Specific Weapon fields include:
 * <ul><p>
 * <li> NORMAL_WEAPON: Has the fields of {@code pierce}, {@code magazine}, and {@code reloadSpeed}
 * <li> MELEE_WEAPON: Has the field of {@code pierce}
 * <li> BEAM_WEAPON: Has the fields of {@code magazine} and {@code reloadSpeed}
 */

enum WeaponType {
	NORMAL_WEAPON, MELEE_WEAPON, BEAM_WEAPON
}

public abstract class Weapon implements Serializable, SwapEffect {
	
	//TODO Static Variables are not serialized. Find way to make it so individual weapon stats can be changed and kept through save-state
	
	private static final long serialVersionUID = 5001535010553222239L;

	public static final int FIRE_RATE_CAP = 7;
	
	private double damage;
	
	private int magazine;
	private int magazineMax;
	private int naturalMagazineMax;
	
	private int fireRate;
	private int fireRateDefault;
	
	private float accuracy;
	
	private int pierce;
	private float projectileSpeed;
	private int reloadSpeed;
	
	private boolean delayed;
	private int shotDelay;
	private boolean reloading;
	private int reloadTimer;
	
	private boolean projectileReleased;
	protected static Characters mainChar;
	private StatusEffect effect;
	
	private static Sprite projectileSprite;
	
	/**
	 * Abstract constructor for {@code NORMAL} type
	 * @param damage Default damage of each Projectile created by the Weapon
	 * @param fireRate Amount of frames that must elapse between each Projectile release
	 * @param magazine Amount of projectiles that can be fired before needing reload. 
	 * @param pierce Amount of enemies the Projectiles released from this weapon can pass through.
	 * @param projSpeed Pixels covered in one frame by projectile released
	 * @param reloadSpeed Amount of frames that must elapse to finish a reload
	 */
	
	public Weapon(double damage, int fireRate, int magazine, int pierce, float projSpeed, int reloadSpeed) {
		this.damage = damage;
		this.instantiateFireRate(fireRate);
		this.instantiateMagazine(magazine);
		this.reloadSpeed = reloadSpeed;
		this.projectileSpeed = projSpeed;
		this.pierce = pierce;
		this.defaultInit();
	}
	
	/**
	 * Abstract constructor for {@code MELEE} Weapon types
	 * @param damage Default damage of each Projectile created by the Weapon
	 * @param fireRate Amount of frames that must elapse between each Projectile release
	 * @param pierce Amount of enemies the Projectiles released from this weapon can pass through.
	 * @param projSpeed Pixels covered in one frame by projectile released
	 */
	public Weapon(double damage, int fireRate, int pierce, float projSpeed) {
		this.damage = damage;
		this.instantiateFireRate(fireRate);
		this.instantiateMagazine(1);
		this.projectileSpeed = projSpeed;
		this.pierce = pierce;
		this.defaultInit();
	}
	
	/**
	 * Abstract constructor for {@code BEAM} Weapon types
	 * @param damage Default damage of each Projectile created by the Weapon
	 * @param fireRate Amount of frames that must elapse between each Projectile release
	 * @param magazine Amount of projectiles that can be fired before needing reload.
	 * @param projSpeed Pixels covered in one frame by projectile released
	 * @param reloadSpeed Amount of frames that must elapse to finish a reload
	 */
	
	public Weapon(double damage, int fireRate, int magazine, float projSpeed, int reloadSpeed) {
		this.damage = damage;
		this.instantiateFireRate(fireRate);
		this.instantiateMagazine(magazine);
		this.reloadSpeed = reloadSpeed;
		this.projectileSpeed = projSpeed;
		this.pierce = 1;
		this.defaultInit();
	}
	
	private void defaultInit() {
		this.effect = new Normal();
		this.accuracy = 0;
	}
	
	/**
	 * Releases all projectiles the weapon normally creates. If the weapon is Melee Type, it will not consume ammunition, but otherwise, will
	 * remove a value from the weapon's current magazine. Uses the abstract method {@code createProjectile()}
	 */
	
	public void shoot() {
		if(!mainChar.getStats().hasInfiniteAmmo() && this.getType() != WeaponType.MELEE_WEAPON) this.magazine--;
		this.shotDelay = this.getFireRate();
		this.delayed = true;
		StatTracker.shotsFired++;
		this.spawnProjectile(createProjectile());
	}
	
	/**
	 * Used for creating an ArrayList of Projectile Objects. Can contain multiple and of varying types.This method is called when the weapon shoots
	 * and is added to the {@link Gamepanel}'s Projectile ArrayList through the {@code spawnProjectile()} method.
	 * @return Custom ArrayList with projectiles. 
	 * @exception NullPointerException null values will create a NullPointerException
	 */
	
	public abstract ArrayList<Entity> createProjectile();
	
	/**
	 * Handles the basic timers of the weapon, including the reload timer, shot delay, and setting ability to fire if these values are equal to 0.
	 * Will be called every frame
	 */
	
	public void timer() {
		if(reloading) {
			this.reloadTimer--;
			if(!(reloadTimer + 1 == reloadSpeed)) mainChar.setShoot(false);
		}
		if(delayed || shotDelay > 0) this.shotDelay--;
		if(reloadTimer == 0) reload();
		if(shotDelay == 0) this.delayed = false;
		else if(shotDelay < 0) this.shotDelay = this.getFireRate();
		
		if(this.fireRate < Weapon.FIRE_RATE_CAP) {
			this.fireRate = Weapon.FIRE_RATE_CAP;
		}
	}
	
	/**
	 * Instantly refills the magazine field to be equal to the magazineMax field. Also sets the reload timer to its maximum value.
	 */
	
	public void reload() {
		this.magazine = this.magazineMax;
		this.reloading = false;
		this.reloadTimer = this.reloadSpeed;
	}
	
	/**
	 * Resets the magazine to its original state
	 */
	
	public final void reset() {
		this.magazineMax = this.naturalMagazineMax;
		this.reload();
	}
	
	public void swapTo() {}
	
	public void swapFrom() {}

	public double getDamage() {
		if(mainChar != null) {
			return this.damage * Math.sqrt(mainChar.getStats().getDamage()/5);
		} else {
			return Math.sqrt(this.damage);
		}
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getMagazine() {
		return magazine;
	}

	public void setMagazine(int magazine) {
		this.magazine = magazine;
	}

	public int getMagazineMax() {
		return magazineMax;
	}

	public void setMagazineMax(int magazineMax) {
		this.magazineMax = magazineMax;
	}
	
	protected void instantiateMagazine(int mag) {
		this.magazine = mag;
		this.magazineMax = mag;
		this.naturalMagazineMax = mag;
	}

	public int getFireRate() {
		if(mainChar != null) {
			return (int)((fireRate/(Math.log(mainChar.getStats().getAttackSpeed()))));
		} else {
			return this.fireRate;
		}
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	
	public int getFireRateDefault() {
		return this.fireRateDefault;
	}
	
	public void setFireRateDefault(int fireRateDefault) {
		this.fireRateDefault = fireRateDefault;
	}
	
	protected void instantiateFireRate(int fireRate) {
		this.fireRateDefault = fireRate;
		this.fireRate = this.fireRateDefault;
	}

	public int getPierce() {
		return pierce;
	}

	public void setPierce(int pierce) {
		this.pierce = pierce;
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
	public float getAccuaracy() {
		return this.accuracy;
	}
	
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public int getReloadSpeed() {
		return reloadSpeed;
	}

	public void setReloadSpeed(int reloadSpeed) {
		this.reloadSpeed = reloadSpeed;
	}

	public boolean isChambered() {
		return delayed;
	}

	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}

	public int getShotDelay() {
		return shotDelay;
	}

	public void setShotDelay(int shotDelay) {
		this.shotDelay = shotDelay;
	}

	public boolean isReloading() {
		return reloading;
	}

	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public int getReloadTimer() {
		return reloadTimer;
	}

	public void setReloadTimer(int reloadTimer) {
		this.reloadTimer = reloadTimer;
	}
	
	public abstract WeaponType getType();
	
	public abstract AnimationType getAnimType();
	
	public abstract String weaponName();

	/**
	 * Determines if the fire rate allows for a shot to be released
	 * @return If the weapon is allowed to shoot
	 */
	
	public boolean shotReady() {
		return projectileReleased;
	}

	public void shotSet(boolean projectileReleased) {
		this.projectileReleased = projectileReleased;
	}
	
	private void spawnProjectile(ArrayList<Entity> p) {
		EntityLists.addNew(p);
	}

	public Sprite getProjectileSprite() {
		return projectileSprite;
	}
	
	public StatusEffect getEffect() {
		return effect.copy();
	}

	public void setEffect(StatusEffect effect) {
		this.effect = effect;
	}
	
	public static void setCharacter(Characters charRef) {
		mainChar = charRef;
	}

}
