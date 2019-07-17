package beneficial;

import characters.Characters;
import main.Gamepanel;

public class Resist extends Beneficial {
	
	private double resist;
	private double prevResist;
	
	public Resist(int duration, double resist) {
		super(duration);
		this.resist = resist;
		this.prevResist = Gamepanel.mainChar.getStats().getResistance();
	}

	@Override
	protected void effect() {

	}

	@Override
	protected void init() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setResistance(resist);
	}

	@Override
	public Beneficial copy() {
		return new Resist(super.getDuration(), this.resist);
	}

	@Override
	protected void finish() {
		Characters c = Gamepanel.mainChar;
		c.getStats().setResistance(prevResist);
	}

}
