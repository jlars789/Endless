package abilities.Cowboy;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.DynamiteProjectile;
import sprite.LightSprite;

public class Dynamite extends ProjectileAbility {
	
	private static final long serialVersionUID = 332477336882595667L;
	public static final double DAMAGE = 180;
	private static final float PROJECTILE_SPEED = 5;
	private static final int COOLDOWN = 1000;
	private static final byte SLOT = 1;
	
	private LightSprite projectileSprite;

	public Dynamite() {
		super(SLOT, COOLDOWN, false);
		
		super.setDamage(0);
		super.setPierce(1);
		super.setProjectileSpeed(PROJECTILE_SPEED);
		
		this.projectileSprite = new LightSprite("imgs/projectiles/dynamite1.png", 2);
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_SHOT;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new DynamiteProjectile(c.getxCoor() + 15, c.getyCoor() + 25, c.getDirection(), this, this.projectileSprite));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
