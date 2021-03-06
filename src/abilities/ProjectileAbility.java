package abilities;

import java.util.ArrayList;

import main.Entity;
import main.EntityLists;
import main.Gamepanel;

public abstract class ProjectileAbility extends Ability {
	
	private static final long serialVersionUID = 5234700573357939892L;
	
	private double damage;
	private float projectileSpeed;
	private int pierce;

	public ProjectileAbility(int slot, int cooldown, boolean independent) {
		super();
		
		super.setAbilitySlot(slot);
		super.setCooldownMax(cooldown);
		super.setIndependent(independent);
	}
	
	public ProjectileAbility(int cost, boolean independent) {
		super();
		
		super.setAbilitySlot(2);
		super.setCost(cost);
		super.setIndependent(independent);
	}
	
	public ProjectileAbility(int slot, int cooldown, boolean independent, int duration) {
		super();
		
		super.setAbilitySlot(slot);
		super.setCooldownMax(cooldown);
		super.setIndependent(independent);
		super.setDuration(duration);
	}
	
	public ProjectileAbility extendInit(double damage, float projSpeed, int pierce) {
		this.damage = damage;
		this.projectileSpeed = projSpeed;
		this.pierce = pierce;
		return this;
	}
	
	protected void internalInit(double damage, float projSpeed, int pierce) {
		this.damage = damage;
		this.projectileSpeed = projSpeed;
		this.pierce = pierce;
	}
	
	public void activate() {
		Gamepanel.mainChar.setLock(true);
		super.activate();
	}
	
	public void use() {
		this.addProjectiles();
		super.use();
		if(this.getType() != Ability.USE_MULTIPLE && this.getType() != Ability.EFFECT_PROJECTILE && !this.hasEffect()) {
			super.abilityEnd();
		}
	}
	
	public double getDamage() {
		if(Gamepanel.mainChar != null) {
			return this.damage * Math.sqrt(Gamepanel.mainChar.getStats().getDamage()/5);
		} else {
			return Math.sqrt(this.damage);
		}
	}
	
	public float getProjectileSpeed() {
		return this.projectileSpeed;
	}
	
	public int getPierce() {
		return this.pierce;
	}
	
	protected void addProjectiles() {
		EntityLists.addNew(this.abilityProjectiles());
	}
	
	protected abstract ArrayList<Entity> abilityProjectiles();
	
	protected abstract boolean hasEffect();

}
