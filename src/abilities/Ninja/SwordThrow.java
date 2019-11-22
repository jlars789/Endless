package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.MovingSword;
import sprite.LightSprite;

public class SwordThrow extends ProjectileAbility {
	
	private static final long serialVersionUID = -5410371648100018595L;

	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/moving_sword.png");
	
	public static final byte SLOT = 1;
	public static int cooldown = 900;
	public static float projectileSpeed = 7.5f;
	public static double damage = 5;

	public SwordThrow() {
		super(SLOT, cooldown, false);
		this.internalInit(damage, projectileSpeed, 1);
	}
	
	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new MovingSword(c.getxCoor(), c.getyCoor(), c.getDirection(), this));
		return p;
	}
	
	public LightSprite getProjectileSprite() {
		return PROJECTILE_SPRITE.copy();
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
