package abilities.Cowboy;

import abilities.Ability;
import abilities.EffectAbility;
import characters.Characters;
import main.Gamepanel;
import statuseffect.StatusEffect;
import statuseffect.Stun;

public class AnarchyAbility extends EffectAbility {
	
	private static final long serialVersionUID = -2399052330495342527L;
	
	private static int COST = 250;
	private static int DURATION = 600;
	
	private static int stunDur = 100;
	private static double damageModifier = 0.4;
	private Stun stun;
	
	private double prevDamageMod;
	private StatusEffect[] se;
	
	/*
	 *If weapon retains effects after death, add simple reset feature to weapon's status effects
	 */

	public AnarchyAbility() {
		super(COST, true, DURATION);
		
		this.stun = new Stun(stunDur);
		this.se = new StatusEffect[2];
	}
	
	protected void effect() {
		
		Characters c = Gamepanel.mainChar;
		
		this.prevDamageMod = c.getStats().getDamage();
		this.se[0] = c.getMoveset().getWeapons()[0].getEffect();
		this.se[1] = c.getMoveset().getWeapons()[1].getEffect();
		
		c.getMoveset().getWeapons()[0].setEffect(stun.copy());
		c.getMoveset().getWeapons()[1].setEffect(stun.copy());
		
		c.getStats().increaseDamage(damageModifier);
	}
	
	public void abilityEnd() {
		Characters c = Gamepanel.mainChar;
		
		c.getMoveset().getWeapons()[0].setEffect(se[0]);
		c.getMoveset().getWeapons()[1].setEffect(se[1]);
		c.getStats().setDamage(prevDamageMod);
		
		super.abilityEnd();
	}
	
	public void reset() {
		abilityEnd();
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}

}
