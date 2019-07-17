package beneficial;

public class Invisibility extends Beneficial {
	
	private double damageBoost;
	//private double prevDamage;

	public Invisibility(int duration, double damageInc) {
		super(duration);
		this.damageBoost = damageInc;
	}

	@Override
	protected void effect() {
		if(!Beneficial.mainChar.getStats().isInvisible()) {
			Beneficial.mainChar.getStats().setInvisible(true);
		}
	}

	@Override
	public void init() {
		Beneficial.mainChar.getStats().setInvisible(true);
		Beneficial.mainChar.getStats().increaseDamage(this.damageBoost);
	}

	@Override
	public Beneficial copy() {
		return new Invisibility(super.getDuration(), this.damageBoost);
	}

	@Override
	protected void finish() {
		Beneficial.mainChar.getStats().setInvisible(false);
		Beneficial.mainChar.getStats().increaseDamage(-this.damageBoost);
	}

}
