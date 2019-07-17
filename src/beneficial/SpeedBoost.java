package beneficial;

import characters.Characters;
import main.Gamepanel;

public class SpeedBoost extends Beneficial {
	
	//private float prevSpeed;
	private float speedBoost;

	public SpeedBoost(int duration, float speedBoost) {
		super(duration);
		//this.prevSpeed = Gamepanel.mainChar.getStats().getSpeed();
		this.speedBoost = speedBoost;
	}

	@Override
	protected void effect() {
	}

	@Override
	public void init() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setSpeed(c.getStats().getSpeed() + speedBoost, false);
	}

	@Override
	public Beneficial copy() {
		return new SpeedBoost(super.getDuration(), this.speedBoost);
	}

	@Override
	protected void finish() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setSpeed(c.getStats().getSpeed() - speedBoost, false);
	}

}
