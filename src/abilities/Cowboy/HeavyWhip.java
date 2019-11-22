package abilities.Cowboy;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Whip;
import sprite.LightSprite;
import statuseffect.Shatter;


public class HeavyWhip extends ProjectileAbility {
	
	private static final long serialVersionUID = 1668018186151679001L;

	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/whip.png", 2);
	
	private static final byte SLOT = 0;
	
	private static double damage = 30;
	private static int cooldown = 500;
	
	private static int duration = 200;
	private static double damageMod = 0.5;
	private Shatter shatter;

	public HeavyWhip() {
		super(SLOT, cooldown, false);
		
		super.internalInit(damage, 0, 1);
		this.shatter = new Shatter(duration, damageMod);
	}
	
	public LightSprite getSprite() {
		return PROJECTILE_SPRITE.copy();
	}
	
	public Shatter getEffect() {
		return this.shatter.copy();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new Whip(c.getxCoor() + c.getWidth(), c.getyCoor()/2 + c.getHeight()/2, c.getDirection(), this));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
