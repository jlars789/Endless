package characters;

import java.io.Serializable;

import main.Gamepanel;
import main.HealthBar;

public class StatHandler implements Serializable {
	
	private static final long serialVersionUID = -7071733510241582277L;

	private HealthBar health;
	
	private double speed;
	private double naturalSpeed;
	
	private double damage;
	private double attackSpeed;
	
	private double cooldownModifier;
	
	private boolean invincible;
	
	private double spikeDamage;
	
	private boolean invisible;
	private double resistance;
	
	private int energy;
	
	private boolean infiniteAmmo;
	
	public StatHandler(boolean newStats) {
		
		if(newStats) {
			this.damage = 1;
			this.attackSpeed = Math.E;
			
			this.invincible = false;
			
			this.spikeDamage = 0;
			
			this.cooldownModifier = 1;
			this.energy = 0;
			
			this.resistance = 1;
		}
		
		
	}
	
	public void reset() {
		this.health.heal(9999);
		
		this.damage = 1;
		this.attackSpeed = Math.E;
		
		this.cooldownModifier = 1;
		
		this.energy = 0;
		this.resistance = 1;
	}
	
	public void healthInstantiate(double health) {
		this.health = new HealthBar(health);
	}
	
	public HealthBar getHealth() {
		return this.health;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed, boolean withMult) {
		if(withMult) {
			this.speed = (float)(speed * Gamepanel.sizeMult);
		} else {
			this.speed = speed;
		}
	}
	
	public void increaseSpeed(double speed) {
		this.speed += speed;
	}

	public double getNaturalSpeed() {
		return naturalSpeed;
	}

	public void setNaturalSpeed(double speed2) {
		this.naturalSpeed = (float)(speed2 * Gamepanel.sizeMult);
	}

	public double getDamage() {
		return this.damage;
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public void increaseDamage(double inc) {
		this.damage += inc;
	}
	
	public double getAttackSpeed() {
		return this.attackSpeed;
	}
	
	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public void increaseAttackSpeed(double inc) {
		this.attackSpeed += inc;
	}

	public double getCooldownModifier() {
		return cooldownModifier;
	}

	public void setCooldownModifier(double cooldownModifier) {
		this.cooldownModifier = cooldownModifier;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public double getSpikeDamage() {
		return spikeDamage;
	}

	public void setSpikeDamage(double spikeDamage) {
		this.spikeDamage = spikeDamage;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}
	
	public double getResistance() {
		return this.resistance;
	}
	
	public void setResistance(double resist) {
		this.resistance = resist;
	}
	
	public void giveInfiniteAmmo(boolean give) {
		this.infiniteAmmo = give;
	}
	
	public boolean hasInfiniteAmmo() {
		return this.infiniteAmmo;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void increaseEnergy(int inc) {
		this.energy += inc;
	}

}
