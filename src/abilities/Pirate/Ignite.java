package abilities.Pirate;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import beneficial.SpeedBoost;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.RingOfFire;
import sprite.LightSprite;
import sprite.Sprite;

public class Ignite extends ProjectileAbility {
	
	private static final long serialVersionUID = -4121193964402890949L;
	private static final byte SLOT = 1;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/ringoffire.png", 4);
	
	public static int cooldown = 1000;
	public static int duration = 120;
	public static float newSpeed = 1.0f;
	public static double damage = 10.5;

	public Ignite() {
		super(SLOT, cooldown, true);
		this.internalInit(damage, 0, 999);
	}
	
	public void use() {
		Characters c = Gamepanel.mainChar;
		c.buff(new SpeedBoost(duration, newSpeed)); 
		super.use();
	}
	
	public Sprite getSprite() {
		return PROJECTILE_SPRITE.copy();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new RingOfFire(c.getxCoor() + c.getWidth()/2, c.getyCoor() + c.getHeight()/2, c.getDirection(), this, duration));
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
