package abilities.Pirate;

import abilities.Ability;
import abilities.EffectAbility;
import characters.Characters;
import main.Gamepanel;
import weapons.Fisticuffs;
import weapons.Weapon;

public class Bloodrage extends EffectAbility {

	private static final long serialVersionUID = 5864448193842208613L;
	
	public static int cost = 200;
	public static int duration = 500;
	
	private Weapon prevWeapon;
	private static Fisticuffs fisticuffs = new Fisticuffs();

	public Bloodrage() {
		super(cost, true, duration);
	}
	
	public void update() {
		Characters c = Gamepanel.mainChar;
		if(!(c.getHeldWeapon() instanceof Fisticuffs)) {
			this.abilitySalvage();
		}
		super.update();
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		c.setHeldWeapon(prevWeapon);
		c.getStats().setEnergy(0);
		super.abilityEnd();
	}
	
	private void abilitySalvage() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setEnergy(super.getDurationTimer() / 4);
		c.setHeldWeapon(prevWeapon);
		super.abilityEnd();
	}
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		fisticuffs = new Fisticuffs();
		this.prevWeapon = c.getHeldWeapon();
		c.setHeldWeapon(fisticuffs);
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
