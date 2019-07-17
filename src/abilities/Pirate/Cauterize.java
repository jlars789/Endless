package abilities.Pirate;

import characters.Characters;
import main.Gamepanel;
import abilities.EffectAbility;
import beneficial.Resist;

public class Cauterize extends EffectAbility {
	
	private static final long serialVersionUID = 7726987642126539959L;

	private static final byte SLOT = 0;
	
	public static int cooldown = 1000;
	public static int duration = 180;
	public static double resistMod = 1.5;
	public static double healMult = 0.2;
	
	public Cauterize() {
		super(SLOT, cooldown, true, duration);
	}
	public void update() {
		super.update();
		Characters c = Gamepanel.mainChar;
		if(c.getDamageInFrame() > 0) {
			c.heal(c.getDamageInFrame() * healMult);
		}
	}
	
	protected void effect() {
		if(!super.getStatus()) {
			Characters c = Gamepanel.mainChar;
			c.buff(new Resist(duration, resistMod));
		} 
	}

	@Override
	protected byte getType() {
		return USE_ON_PRESS;
	}

}
