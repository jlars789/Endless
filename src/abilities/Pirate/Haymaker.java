package abilities.Pirate;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import beneficial.Invincible;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Fist;
import statuseffect.Cripple;

public class Haymaker extends ProjectileAbility {
	
	private static final long serialVersionUID = -2669732034767062211L;

	private static final byte SLOT = 1;
	
	public static double damage = 125;
	public static int cooldown = 1250;
	public static int duration = 200;
	private Cripple cripple;
	
	private boolean impacted;
	private int initDir;

	public Haymaker() {
		super(SLOT, cooldown, false, 200);
		
		super.setDamage(damage);
		super.setPierce(1);
		super.setProjectileSpeed(0);
		
		this.cripple = new Cripple(duration);
	}
	
	public void update() {
		super.update();
		Characters c = Gamepanel.mainChar;
		if(!impacted) c.shift(initDir, 20);
		else this.abilityEnd();
	}
	
	public void use() {
		Characters c = Gamepanel.mainChar;
		this.initDir = c.getDirection();
		c.buff(new Invincible(25));
		super.use();
	}
	
	public Cripple getEffect() {
		return this.cripple.copy();
	}
	
	public void abilityEnd() {
		this.impacted = false;
		super.abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add(new Fist(c.getxCoor() + c.getWidth()/2, c.getyCoor() + c.getHeight()/2, c.getDirection(), this));
		return p;
	}
	
	public void hit() {
		this.impacted = true;
	}

	@Override
	protected boolean hasEffect() {
		return true;
	}

}
