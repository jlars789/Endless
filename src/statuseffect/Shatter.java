package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.HeavySprite;

public class Shatter extends StatusEffect {
	
	private double multiplier;
	private double prevResist;
	
	private HeavySprite sprite;

	public Shatter(int duration, double multiplier) {
		super(duration);
		this.multiplier = multiplier;
		
		this.sprite = StatusEffect.getSprite(4);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		if(super.getHostile() != null) {
			g2d.drawImage(sprite.getImage(), null, (int)(super.getHostile().getxCoor() + (super.getHostile().getWidth()/2) - sprite.getImage().getWidth()/2), (int)super.getHostile().getyCoor() - sprite.getImage().getHeight());
		}
		
		sprite.update();
	}
	
	public Shatter copy() {
		Shatter copy = new Shatter(super.getDuration(), multiplier);
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void effect() {
	}

	@Override
	protected void init(Hostile hostile) {
		this.prevResist = hostile.getResistance();
		super.getHostile().setResistance(super.getHostile().getResistance() * multiplier);
	}

	@Override
	protected void finish() {
		super.getHostile().setResistance(this.prevResist);
	}

}
