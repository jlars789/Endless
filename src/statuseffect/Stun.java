package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import hostiles.Shooter;
import sprite.HeavySprite;

public class Stun extends StatusEffect {
	
	private HeavySprite sprite;
	private float prevSpeed;
	public Stun(int duration) {
		super(duration);
		this.sprite = StatusEffect.getSprite(1);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		if(super.getHostile() != null) {
			g2d.drawImage(sprite.getImage(), null, (int)(super.getHostile().getxCoor() + (super.getHostile().getWidth()/2) - sprite.getImage().getWidth()/2), (int)super.getHostile().getyCoor() - sprite.getImage().getHeight());
		}
		
		sprite.update();
	}
	
	@Override
	protected void effect() {
		if(super.onEnemy()) {
			super.getHostile().setSpeed(0);
			if(super.getHostile() instanceof Shooter) {
				super.getHostile().setLock(true);
			}
		} 
	}
	
	public Stun copy() {
		Stun copy = new Stun(super.getDuration());
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void finish() {
		super.getHostile().setSpeed(this.prevSpeed);
		super.getHostile().setLock(false);
	}

	@Override
	protected void init(Hostile hostile) {
		this.prevSpeed = hostile.getSpeed();
		super.activate();
	}
}
