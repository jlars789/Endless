package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;

public class Normal extends StatusEffect {

	public Normal() {
		super(0);
	}

	@Override
	protected void effect() {
	}

	@Override
	protected void init(Hostile hostile) {
	}

	@Override
	protected void finish() {
	}

	@Override
	public void draw(Graphics2D g) {
	}

	@Override
	public StatusEffect copy() {
		return new Normal();
	}
	

}
