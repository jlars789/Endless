package abilities.Cowboy;

import abilities.Ability;
import abilities.EffectAbility;
import beneficial.Invincible;
import beneficial.Shift;
import characters.Characters;
import main.Gamepanel;

public class QuickHand extends EffectAbility {
	
	private static final long serialVersionUID = -6550547100835707432L;
	public static final byte SLOT = 1;
	public static int cooldown = 700;

	public QuickHand() {
		super(SLOT, cooldown, false);
	}
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		c.buff(new Shift(6, 18, c.moveIndex()));
		c.stop(c.moveIndex());
		for(int i = 0; i < 2; i++) {
			c.getMoveset().getWeapons()[i].reload();
		}
		c.buff(new Invincible(30));
		
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
