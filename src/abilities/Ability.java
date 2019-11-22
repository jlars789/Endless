package abilities;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import main.Gamepanel;
import statuseffect.StatusEffect;

/**
 * Abstract class used for creating Abilities.
 * <p>
 * Abilities are custom actions that are used in-game with the E key if the slot is 0, Shift if 1, and Q if 2.
 * The three native extensions include:
 * <ul><p>
 * <li> {@link EffectAbility}: Does something to the Character
 * <li> {@link ProjectileAbility}:  Spawns one or more {@link Projectile} Objects on use
 * <li> {@link SpawnAbility}: Spawns one or more {@link Friendly} Objects on use
 * </ul><p>
 * The three activation methods include:
 * <ul><p>
 * <li> {@code USE_ON_PRESS} which will use the ability immediately on its respective button press.
 * <li> {@code USE_ON_SHOT} which will toggle the ability on the button press, then can be used by shooting. Will end upon use
 * <li> {@code USE_MULTIPLE} which will toggle, then can be used multiple times after toggle
 * <li> {@code EFFECT_PROJECTILE} is to be used for ProjectileAbilities, and indicates the ability should not end immediately upon use
 * <p>
 */
public abstract class Ability implements Serializable {
	
	private static final long serialVersionUID = 2013876752973538492L;
	
	public static final byte USE_ON_PRESS = 0;
	public static final byte USE_ON_SHOT = 1;
	public static final byte USE_MULTIPLE = 2;
	public static final byte EFFECT_PROJECTILE = 3;
	
	private boolean active; //determines if the ability is being "held" by the player
	private boolean inUse; //determines if the main utility of the ability is active
	
	private int cooldownDefault;
	private int cooldownMax; //the maximum cooldown of the ability
	private int cooldown; //the current cooldown status of the ability
	
	private int duration;
	private int durationTimer;
	private boolean independent; //determines of the player can fire other attacks while ability is toggled
	private int cost; //cost of energy
	
	private StatusEffect effect;
	private int abilitySlot;
	
	/**
	 * Abstract class for abilities. Abilities are used by the player and must have a cooldown and slot
	 */
	
	public Ability() {
	}
	
	/**
	 * Updates the ability and its timers. Called every frame
	 */
	
	public void update() {
		this.durationTimer();
	}
	
	public void activate() {
		this.active = true;
		if(this.getType() == USE_ON_PRESS) {
			if(!this.inUse) {
				this.use();
			}
		}
	}
	
	public void use() {
		this.inUse = true;
		Gamepanel.mainChar.setLock(false);
	}
	
	public void cooldownTimer() {
		this.cooldown--;
	}
	
	protected void durationTimer() {
		if(durationTimer > 0) {
			this.durationTimer--;
		} 
		else if(this.active) {
			this.abilityEnd();
		}
	}
	
	protected void abilityEnd() {
		this.active = false;
		this.inUse = false;
		this.durationTimer = this.duration;
		this.cooldown = cooldownMax;
		if(this.getAbilitySlot() == 2) {
			Gamepanel.mainChar.getStats().setEnergy(0);
		}
	}
	
	protected void extendDuration(int extend) {
		if(durationTimer > 0) {
			this.durationTimer += extend;
		}
	}
	
	public void giveKeyCode(int key) {
		if(this.getType() == USE_ON_SHOT) {
			if(key >= KeyEvent.VK_LEFT && key <= KeyEvent.VK_DOWN) {
				if(this.active && !this.inUse) {
					this.use();
				}
			}
		}
		
		else if(this.getType() == USE_MULTIPLE) {
			if(key >= KeyEvent.VK_LEFT && key <= KeyEvent.VK_DOWN) {
				if(this.active) {
					this.use();
				}
			}
		}
	}
	
	public void reset() {
		this.abilityEnd();
		this.cooldown = 0;
	}
	
	public boolean isReady() {
		return (this.cooldown == 0);
	}
	
	public void setToggle(boolean status) {
		this.active = status;
	}
	
	public boolean getToggle() {
		return this.active;
	}
	
	public boolean getStatus() {
		return this.inUse;
	}
	
	public void setStatus(boolean inUse) {
		this.inUse = inUse;
	}
	
	public void setCooldownMax(int cooldownMax) {
		this.cooldownMax = cooldownMax;
	}
	
	public int getCooldownMax() {
		return this.cooldownMax;
	}
	
	public int getCooldownDefault() {
		return this.cooldownDefault;
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getCooldown() {
		return this.cooldown;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
		this.durationTimer = duration;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public int getDurationTimer() {
		return this.durationTimer;
	}
	
	public void setIndependent(boolean is) {
		this.independent = is;
	}
	
	public boolean isIndependent() {
		return this.independent;
	}

	public int getAbilitySlot() {
		return abilitySlot;
	}

	public void setAbilitySlot(int abilitySlot) {
		this.abilitySlot = abilitySlot;
	}
	
	public boolean hasProjectile() {
		return true;
	}

	public StatusEffect getEffect() {
		return effect;
	}

	public void setEffect(StatusEffect effect) {
		this.effect = effect;
	}
	
	protected abstract byte getType();
}
