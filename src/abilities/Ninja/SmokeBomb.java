package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import beneficial.Invisibility;
import main.Entity;
import main.Gamepanel;
import projectiles.Smoke;

public class SmokeBomb extends ProjectileAbility {
	
	private static final long serialVersionUID = -3801343639005760291L;

	private static final byte SLOT = 1;
	
	public static int cooldown = 800;
	public static int duration = 180;
	public static double damageMod = 1.2;
	
	public SmokeBomb() {
		super(SLOT, cooldown, true, duration);
		super.setPierce(9999);
	}
	
	public void use() {
		Gamepanel.mainChar.buff(new Invisibility(duration, damageMod));
		super.use();
	}
	
	public void reset() {
		this.abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		ArrayList<Entity> sm = new ArrayList<Entity>();
		sm.add(new Smoke(this));
		return sm;
	}

	@Override
	protected boolean hasEffect() {
		return true;
	}

}
