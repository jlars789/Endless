package beneficial;

import characters.Characters;
import main.Gamepanel;

public class Shift extends Beneficial {
	
	private float speed;
	private int direction;

	public Shift(int duration, float speed, int direction) {
		super(duration);
		this.speed = speed;
		this.direction = direction;
	}

	@Override
	protected void effect() {
		Characters c = Gamepanel.mainChar;
		c.shift(direction, speed);
	}

	@Override
	protected void init() {
	}

	@Override
	public Beneficial copy() {
		return new Shift(super.getDuration(), this.speed, this.direction);
	}

	@Override
	protected void finish() {
	}

}
