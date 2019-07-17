package abilities.Cowboy;

import java.util.ArrayList;

import abilities.Ability;
import abilities.SpawnAbility;
import friendlies.Barricade;
import main.Entity;
import main.Gamepanel;

public class PortableCover extends SpawnAbility {
	
	private static final long serialVersionUID = 1471419035423097336L;

	private static final byte SLOT = 0;
	
	private static int cooldown = 125;
	private static int duration = 400;

	public PortableCover() {
		super(SLOT, cooldown, false, duration);
	}
	
	public static int duration() {
		return duration;
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> createFriend() {
		ArrayList<Entity> bar = new ArrayList<Entity>();
		bar.add(new Barricade(Gamepanel.mainChar.getxCoor(), Gamepanel.mainChar.getyCoor(), duration));
		return bar;
	}

}
