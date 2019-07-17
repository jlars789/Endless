package abilities.Pirate;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Gunpowder;

public class GunpowderAbility extends ProjectileAbility {

	private static final long serialVersionUID = -2327528358410283623L;
	public static final byte SLOT = 1;
	
	public static int cooldown = 1000;
	public static int explosionSize = 140;
	public static double damageMult = 0.8;
	public static double explosionDamage = 100;
	public static int duration = 180;

	public GunpowderAbility() {
		super(SLOT, cooldown, false);
		super.setPierce(999);
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new Gunpowder(c.getxCoor(), c.getyCoor(), c.getDirection(), this));
		return p;
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
