package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.PoisonDart;
import sprite.LightSprite;
import statuseffect.Poison;

public class PoisonDartAbility extends ProjectileAbility {
	
	private static final long serialVersionUID = 1550883282079195917L;
	private static final byte SLOT = 0;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/poisondart.png", 2);
	
	public static int cooldown = 700;
	public static int damage = 5;
	public static int projectileSpeed = 12;
	public static double tickDamage = 6.666;
	
	private Poison poison;

	public PoisonDartAbility() {
		super(SLOT, cooldown, false);
		this.internalInit(damage, projectileSpeed, 1);
		this.poison = new Poison(tickDamage);
	}
	
	public Poison getEffect() {
		return this.poison.copy();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new PoisonDart(c.getxCoor() + c.getWidth()/2, c.getyCoor() + c.getHeight()/2, c.getDirection(), this, PROJECTILE_SPRITE.copy()));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
