package abilities.Ninja;

import abilities.Ability;
import abilities.EffectAbility;
import beneficial.Invincible;
import beneficial.Shift;
import characters.Characters;
import main.Gamepanel;

public class Evade extends EffectAbility {
	
	private static final long serialVersionUID = -602056142525085973L;
	public static final byte SLOT = 1;
	public static int cooldown = 250;
	public static float speed = 15;
	public static int duration = 15;

	public Evade() {
		super(SLOT, cooldown, true);
	}
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		c.buff(new Shift(duration, speed, c.moveIndex()));
		c.buff(new Invincible(40));
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
