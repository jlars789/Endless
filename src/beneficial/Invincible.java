package beneficial;

public class Invincible extends Beneficial {

	public Invincible(int duration) {
		super(duration);
	}

	@Override
	protected void effect() {
		if(!Beneficial.mainChar.getStats().isInvincible()) {
			Beneficial.mainChar.getStats().setInvincible(true);
		}
	}

	@Override
	public void init() {
		Beneficial.mainChar.getStats().setInvincible(true);
	}

	@Override
	public Beneficial copy() {
		return new Invincible(super.getDuration());
	}

	@Override
	protected void finish() {
		Beneficial.mainChar.getStats().setInvincible(false);
	}

}
