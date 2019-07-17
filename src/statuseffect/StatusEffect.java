package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.HeavySprite;

/**
 * Used for applying an effect to enemies. 
 */

public abstract class StatusEffect {
	
	private int duration;
	private boolean active;
	
	private Hostile hostile;
	
	private static HeavySprite seSprite[] = new HeavySprite[5];

	/**
	 * StatusEffects are an effect that can happen to enemies on either initialization
	 * or on a set interval of frames for the entire duration it is applied.
	 * @param duration Duration of the effect.
	 */
	
	public StatusEffect(int duration) {
		this.duration = duration;
		this.active = false;
	}
	
	public static void loadImages() {
		seSprite[0] = new HeavySprite("imgs/statuseffects/poison.png", 8, 8, 1, 3, 16, true, 4);
		seSprite[1] = new HeavySprite("imgs/statuseffects/stun.png", 8, 8, 1, 2, 16, true, 4);
		seSprite[2] = new HeavySprite("imgs/statuseffects/cripple.png", 8, 8, 1, 1, 0, true, 4);
		seSprite[3] = new HeavySprite("imgs/statuseffects/weaken.png", 8, 8, 1, 1, 0, true, 4);
		seSprite[4] = new HeavySprite("imgs/statuseffects/shatter.png", 8, 8, 1, 1, 0, true, 4);
	}
	
	public static HeavySprite getSprite(int index) {
		return seSprite[index].copy();
	}
	
	public abstract void draw(Graphics2D g);
	
	public void activate() {
		this.active = true;
	}
	
	/**
	 * Used for giving an effect to hostiles. This will give the {@code hostile} field a value, along with
	 * activating the effect and initializing the subclass.
	 * @param hostile
	 */
	public void giveEffect(Hostile hostile) {
		this.hostile = hostile;
		this.activate();
		this.init(hostile);
	}
	
	/**
	 * Called every frame if applied to a hostile
	 * reduces duration timer and applies the effect (if applicable) each frame
	 */
	
	public void update() {
		this.effect();
		if(active && duration > 0) {
			duration--;
		} else {
			this.end();
		}
	}
	
	/**
	 * The effect the ability has each frame
	 */
	
	protected abstract void effect();
	
	/**
	 * Effect the {@code StatusEffect} has on a hostile upon initialization.
	 * @param hostile Hostile that is to be affected
	 */
	
	protected abstract void init(Hostile hostile);
	
	/**
	 * Returns a copy of the StatusEffect. 
	 * @return direct copy of StatusEffect
	 */
	public abstract StatusEffect copy();
	
	public void end() {
		this.active = false;
		this.finish();
	}
	
	protected abstract void finish();
	
	public boolean isActive() {
		return this.active;
	}
	
	public boolean onEnemy() {
		return (this.active && this.duration > 0);
	}
	
	protected Hostile getHostile() {
		return this.hostile;
	}
	
	public int getDuration() {
		return this.duration;
	}

}
