package abilities.Cowboy;

import abilities.Ability;
import abilities.EffectAbility;
import characters.Characters;
import main.Gamepanel;
import main.StatTracker;

public class CriticalStrike extends EffectAbility {
	
	private static final long serialVersionUID = -4470602828437909979L;

	private static final byte SLOT = 1;
	
	public static int cooldown = 1200;
	public static int duration = 120;
	
	public static double damageMult = 1.1;
	private double prevDamage;
	private int initKillCount;

	public CriticalStrike() {
		super(SLOT, cooldown, true, duration);
		this.initKillCount = StatTracker.killCount;
	}
	
	public void update() {
		super.update();
		if(initKillCount < StatTracker.killCount) {
			super.extendDuration(30);
			initKillCount = StatTracker.killCount;
		}
	}
	
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		if(!super.getStatus()) {
			this.prevDamage = c.getStats().getDamage();
			c.getStats().increaseDamage((c.getStats().getDamage() * damageMult));
		}
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setDamage(this.prevDamage);
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
