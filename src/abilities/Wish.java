package abilities;

import console.Console;
import main.Gamepanel;

public class Wish extends EffectAbility {
	
	private static final long serialVersionUID = 3205257361584431656L;
	private static final byte SLOT = 0;
	private static final int COOLDOWN = 2;

	public Wish() {
		super(SLOT, COOLDOWN, true);
	}


	@Override
	protected void effect() {
		if(Gamepanel.currentMenu == null) {
			Gamepanel.currentMenu = new Console();
		}
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
