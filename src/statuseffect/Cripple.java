package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.HeavySprite;

public class Cripple extends StatusEffect {
	
	private HeavySprite sprite;
	private float prevSpeed;
	private static final String NAME = "cripple";

	public Cripple(int duration) {
		super(duration);
		this.sprite = AnimLoader.loadSEAnim(NAME, 1);
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
			super.getHostile().setSpeed(1);
		} 
	}
	
	public Cripple copy() {
		Cripple copy = new Cripple(super.getDuration());
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void finish() {
		super.getHostile().setSpeed(this.prevSpeed);
	}

	@Override
	protected void init(Hostile hostile) {
		this.prevSpeed = hostile.getSpeed();
	}

}
