package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.HeavySprite;

public class Weaken extends StatusEffect {
	
	private double multiplier;
	private double prevMult;
	private HeavySprite sprite;

	public Weaken(int duration, double multiplier) {
		super(duration);
		this.multiplier = multiplier;
		
		this.sprite = StatusEffect.getSprite(3);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		if(super.getHostile() != null) {
			g2d.drawImage(sprite.getImage(), null, (int)(super.getHostile().getxCoor() + (super.getHostile().getWidth()/2) - sprite.getImage().getWidth()/2), (int)super.getHostile().getyCoor() - sprite.getImage().getHeight());
		}
		
		sprite.update();
	}
	
	public Weaken copy() {
		Weaken copy = new Weaken(super.getDuration(), multiplier);
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void effect() {
		if(super.onEnemy()) {
			super.getHostile().setDamageMult(super.getHostile().getDamageMult() * multiplier);
		}
	}

	@Override
	protected void init(Hostile hostile) {
		this.prevMult = hostile.getDamageMult();
	}

	@Override
	protected void finish() {
		super.getHostile().setDamageMult(this.prevMult);
	}

}
