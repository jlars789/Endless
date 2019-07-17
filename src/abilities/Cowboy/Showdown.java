package abilities.Cowboy;

import abilities.Ability;
import abilities.EffectAbility;
import characters.Characters;
import main.Gamepanel;

public class Showdown extends EffectAbility {
	
	private static final long serialVersionUID = -8645538533177237581L;
	private static int cost = 300;
	private static int duration = 400;
	
	private static double fireRateModifier = 2;
	private static int accuracyMod = 2;
	
	private double prevFireMod;

	public Showdown() {
		super(cost, true, duration);
	}

	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		this.prevFireMod = c.getStats().getAttackSpeed();
		c.getStats().increaseAttackSpeed(c.getStats().getAttackSpeed() * fireRateModifier);
		c.getStats().giveInfiniteAmmo(true);
		c.getMoveset().getWeapons()[0].setAccuracy(accuracyMod);
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setAttackSpeed(this.prevFireMod);
		c.getStats().giveInfiniteAmmo(false);
		c.getMoveset().getWeapons()[0].setAccuracy(0);
		super.abilityEnd();
	}
	
	public void reset() {
		abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
