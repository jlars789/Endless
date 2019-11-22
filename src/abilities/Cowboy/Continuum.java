package abilities.Cowboy;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.ContinuumShot;

public class Continuum extends ProjectileAbility {
	
	private static final long serialVersionUID = -8664063323315116585L;
	private static int cost = 175;
	public static int damage = 300;
	public static int loops = 3;

	public Continuum() {
		super(cost, false);
		this.internalInit(damage, 100, 40);
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_SHOT;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new ContinuumShot(c.getxCoor(), c.getyCoor(), c.getDirection(), this, loops));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}
}
