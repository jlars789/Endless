package abilities.Pirate;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.WreckingBall;
import sprite.LightSprite;

public class WreckingCrew extends ProjectileAbility {

	private static final long serialVersionUID = 2929336833873200290L;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/wreckingball.png", 4);
	public static final byte SLOT = 0;
	
	public static int cooldown = 900;
	public static double damage = 120;
	public static float projectileSpeed = 4.5f;

	public WreckingCrew() {
		super(SLOT, cooldown, false);
		super.setDamage(damage);
		super.setProjectileSpeed(projectileSpeed);
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new WreckingBall(c.getxCoor(), c.getyCoor(), c.getDirection(), this));
		return p;
	}
	
	public LightSprite getSprite() {
		return PROJECTILE_SPRITE.copy();
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
