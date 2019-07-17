package abilities.Pirate;

import abilities.EffectAbility;
import characters.Characters;
import main.Gamepanel;
import weapons.Flamethrower;
import weapons.Weapon;

public class InfernoAbility extends EffectAbility {
	
	private static final long serialVersionUID = 271629461699315931L;
	public static int cost = 280;
	public static int duration = 1000;
	
	private Weapon prevWeapon;
	private static Flamethrower flamethrower = new Flamethrower();
	
	public InfernoAbility() {
		super(cost, true, duration);
	}
	
	public void update() {
		Characters c = Gamepanel.mainChar;
		if(flamethrower.getMagazine() == 0) {
			this.abilityEnd();
		}
		else if(!(c.getHeldWeapon() instanceof Flamethrower)) {
			this.abilitySalvage();
		}
		
		if(c.isShoot()) {
			super.durationTimer();
		}
		super.update();
	}
	
	//FIXME Adjust location
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		flamethrower = new Flamethrower();
		this.prevWeapon = c.getHeldWeapon();
		c.setHeldWeapon(flamethrower);
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setEnergy(0);
		c.setHeldWeapon(this.prevWeapon);
		super.abilityEnd();
	}
	
	public void abilitySalvage() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setEnergy(super.getDurationTimer() / 4);
		c.setHeldWeapon(prevWeapon);
		super.abilityEnd();
	}
	
	public void reset() {
		this.abilityEnd();
	}

	@Override
	protected byte getType() {
		return USE_ON_PRESS;
	}

}
