package abilities.Ninja;

import abilities.Ability;
import abilities.EffectAbility;
import beneficial.SpeedBoost;
import characters.Characters;
import main.Gamepanel;
import weapons.ShurikenWeapon;
import weapons.Weapon;

public class Ninjutsu extends EffectAbility {

	private static final long serialVersionUID = 2518736225355836795L;
	
	private static int cost = 250;
	private static int duration = 400;
	private static int projCount = 3;
	private Weapon prevWeapon;
	private ShurikenWeapon shuriken;

	public Ninjutsu() {
		super(cost, true, duration);
		shuriken = new ShurikenWeapon(projCount);
	}
	
	public void update() {
		Characters c = Gamepanel.mainChar;
		if(shuriken.getMagazine() == 0) {
			this.abilityEnd();
		}
		else if(!(c.getHeldWeapon().equals(shuriken))) {
			this.abilitySalvage();
		}
		
		if(c.isShoot()) {
			super.durationTimer();
		}
		super.update();
	}
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		this.shuriken = new ShurikenWeapon(projCount);
		this.prevWeapon = c.getHeldWeapon();
		c.buff(new SpeedBoost(duration, 1.0f));
		c.setHeldWeapon(this.shuriken);
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
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
		return Ability.USE_ON_PRESS;
	}

}
