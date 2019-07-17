package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import beneficial.SpeedBoost;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.orbital.OrbitalSword;
import sprite.LightSprite;

public class Blademaster extends ProjectileAbility {
	
	private static final long serialVersionUID = 2982416425538364147L;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/moving_sword.png", 4);
	
	public static int cost = 320;
	public static int projectileSpeed = 30;
	public static double damage = 15;

	public Blademaster() {
		super(cost, true);
		super.setProjectileSpeed(projectileSpeed);
		super.setDamage(damage);
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		c.buff(new SpeedBoost(OrbitalSword.lifespan/2, 1.0f));
		p.add(new OrbitalSword(0, this));
		p.add(new OrbitalSword(projectileSpeed, this));
		return p;
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}
	
	public LightSprite getSprite() {
		return PROJECTILE_SPRITE.copy();
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
