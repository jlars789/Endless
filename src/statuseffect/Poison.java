package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.HeavySprite;

public class Poison extends StatusEffect {
	
	private double damage;
	private int damageInterval;
	private HeavySprite sprite;
	private static final String NAME = "poison";
	
	public Poison(double damage) {
		super(750);
		this.damage = damage;
		this.damageInterval = 16;
		
		this.sprite = AnimLoader.loadSEAnim(NAME, 3);
	}
	
	public void draw(Graphics2D g2d) {
		
		if(super.getHostile() != null) {
			g2d.drawImage(sprite.getImage(), null, (int)(super.getHostile().getxCoor() + (super.getHostile().getWidth()/2) - sprite.getImage().getWidth()/2), (int)super.getHostile().getyCoor() - sprite.getImage().getHeight());
		}
		
		sprite.update();
	}
	
	public Poison copy() {
		Poison copy = new Poison(damage);
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void effect() {
		if(super.onEnemy()) {
			if(super.getDuration() % damageInterval == 0) {
				super.getHostile().damage(this.damage);
			}
		}
	}

	@Override
	protected void init(Hostile hostile) {
	}

	@Override
	protected void finish() {
	}

}
