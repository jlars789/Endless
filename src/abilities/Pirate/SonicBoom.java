package abilities.Pirate;

import java.util.ArrayList;

import abilities.ProjectileAbility;
import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Sonic;
import statuseffect.Stun;

public class SonicBoom extends ProjectileAbility {

	private static final long serialVersionUID = -1223109796349915132L;

	private static final byte SLOT = 0;
	
	public static int cooldown = 600;
	public static double damage = 15;
	public static int pierce = 5;
	public static float projectileSpeed = 10;
	public static int duration = 100;
	private Stun stun;

	public SonicBoom() {
		super(SLOT, cooldown, false);
		this.internalInit(damage, projectileSpeed, pierce);
		this.stun = new Stun(duration);
	}
	
	public Stun getEffect() {
		return this.stun.copy();
	}

	@Override
	protected byte getType() {
		return USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> abilityProjectiles() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> p = new ArrayList<Entity>();
		if(c.getDirection() == 0) {
			p.add(new Sonic((c.getxCoor() + c.getWidth()/2) - 20, c.getyCoor() - 40, c.getDirection(), this));
		} else if(c.getDirection() == 1) {
			p.add(new Sonic(c.getxCoor() + c.getWidth(), (c.getyCoor() + c.getHeight()/2) - 20, c.getDirection(), this));
		} else if(c.getDirection() == 2) {
			p.add(new Sonic((c.getxCoor() + c.getWidth()/2) - 20, (c.getyCoor() + c.getHeight()), c.getDirection(), this));
		} else {
			p.add(new Sonic((c.getxCoor()) - 40, (c.getyCoor() + c.getHeight()/2) - 20, c.getDirection(), this));
		}
		
		return p;
		
	}

	@Override
	protected boolean hasEffect() {
		return false;
	}

}
