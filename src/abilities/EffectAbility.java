package abilities;

public abstract class EffectAbility extends Ability {

	private static final long serialVersionUID = 8007579817710950964L;

	public EffectAbility(int slot, int cooldown, boolean independent) {
		super();
		super.setAbilitySlot(slot);
		super.setCooldownMax(cooldown);
		super.setIndependent(independent);
	}
	
	public EffectAbility(int slot, int cooldown, boolean independent, int duration) {
		super();
		
		super.setAbilitySlot(slot);
		super.setCooldownMax(cooldown);
		super.setIndependent(independent);
		super.setDuration(duration);
	}
	
	public EffectAbility(int cost, boolean independent) {
		super();
	}
	
	public EffectAbility(int cost, boolean independent, int duration) {
		super();
		
		super.setCost(cost);
		super.setAbilitySlot(2);
		super.setIndependent(independent);
		super.setDuration(duration);
	}
	
	public void use() {
		this.effect();
		super.use();
	}
	
	protected abstract void effect();
		
}
