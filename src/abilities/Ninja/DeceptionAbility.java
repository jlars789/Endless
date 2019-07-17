package abilities.Ninja;

import java.util.ArrayList;

import abilities.Ability;
import abilities.SpawnAbility;
import beneficial.Invisibility;
import characters.Characters;
import friendlies.ShadowDouble;
import main.Entity;
import main.Gamepanel;

public class DeceptionAbility extends SpawnAbility {

	private static final long serialVersionUID = 7018508761477741948L;
	private static int cost = 240;
	private static int duration = 500;
	
	private static double damageMod = 0.6;
	private boolean created;

	public DeceptionAbility() {
		super(cost, true, duration);
		this.created = false;
	}
	
	public void update() {
		super.update();
		if(super.getDurationTimer() == 0 && super.getStatus()) {
			this.abilityEnd();
		}
	}
	
	public void activate() {
		super.activate();
		this.use();
	}
	
	public void use() {
		if(!this.created) {
			Gamepanel.mainChar.buff(new Invisibility(duration, damageMod));
			this.created = true;
		}
		
		super.use();
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setEnergy(0);
		super.abilityEnd();
		this.created = false;
	}
	
	public void reset() {
		abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

	@Override
	protected ArrayList<Entity> createFriend() {
		ArrayList<Entity> doub = new ArrayList<Entity>();
		doub.add(new ShadowDouble());
		return doub;
	}

}
