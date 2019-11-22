package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Kunai;
import sprite.LightSprite;
import statuseffect.Weaken;

public class KunaiToss extends ProjectileAbility {
	
	private static final long serialVersionUID = -1981640200117922299L;
	private static final byte SLOT = 0;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/kunai.png");
	
	private static int cooldown = 580;
	private static double damage = 25; 
	private static int pierce = 5;
	private static int projectileSpeed = 9;
	public static int projectilesReleased = 3;
	
	private static int duration = 200;
	private static double weakenVal = 0.5;
	private Weaken weaken;

	public KunaiToss() {
		super(SLOT, cooldown, false);
		this.internalInit(damage, projectileSpeed, pierce);
		
		this.weaken = new Weaken(duration, weakenVal);
	}
	
	public Weaken getEffect() {
		return this.weaken.copy();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		for(float i = (-projectilesReleased); i <= projectilesReleased; i+= ((2 * (float)projectilesReleased)/((float)projectilesReleased - 1))) {
			p.add(new Kunai(c.getxCoor() + c.getWidth()/2, c.getyCoor() + c.getHeight()/2, c.getDirection(), this, i, PROJECTILE_SPRITE.copy()));
		}
		return p;
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
