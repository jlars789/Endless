package abilities.Ninja;

import characters.Characters;
import main.Gamepanel;
import abilities.Ability;
import abilities.EffectAbility;
import beneficial.Invincible;
import beneficial.Shift;

public class ShadowSlice extends EffectAbility {
	
	private static final long serialVersionUID = -1897788460093315632L;
	private static final byte SLOT = 1;
	private static final int DURATION = 8;
	private static final float SPEED = 50;
	
	public static int invincibleDuration = 30;
	public static int cooldown = 700;
	public static double damage = 20;
	private double prevDamage;
	
	private int direction;

	public ShadowSlice() {
		super(SLOT, cooldown, false);
	}
	
	protected void effect() {
		Characters chars = Gamepanel.mainChar;
		this.direction = chars.getDirection();
		this.prevDamage = chars.getStats().getSpikeDamage();
		chars.buff(new Invincible(invincibleDuration));
		chars.buff(new Shift(DURATION, SPEED, this.direction));
		chars.getStats().setSpikeDamage((damage * chars.getStats().getDamage()));
	}
	
	public void abilityEnd() {
		Characters chars = Gamepanel.mainChar;
		super.abilityEnd();
		chars.getStats().setSpikeDamage(this.prevDamage);
	}
	
	public void reset() {
		this.abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
