package abilities.Cowboy;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Bullet;
import sprite.LightSprite;

public class Trickshot extends ProjectileAbility {

	private static final long serialVersionUID = -7330004504685743436L;
	public static final int SLOT = 0;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/bullet.png", 4);
	
	public static int cooldown = 700;
	public static int delayMax = 32;
	
	private static double damage = 90;
	
	private static int bounces = 5;

	public Trickshot() {
		super(SLOT, cooldown, false);
		super.setDamage(damage);
		super.setPierce(1);
		super.setProjectileSpeed(20);
	}
	
	public LightSprite getProjectileSprite() {
		return PROJECTILE_SPRITE;
	}
	
	public static void setBulletDamage(double damage) {
		Trickshot.damage = damage;
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new Bullet(c.getxCoor(), c.getyCoor(), c.getDirection(), this, 0, bounces));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
