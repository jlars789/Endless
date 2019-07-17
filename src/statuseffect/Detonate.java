package statuseffect;

import java.awt.Graphics2D;

import hostiles.Hostile;
import main.EntityLists;
import projectiles.Explosion;

public class Detonate extends StatusEffect {
	
	private int size;
	private double damage;

	public Detonate(int size, double damage) {
		super(9999);
		this.size = size;
		this.damage = damage;
	}

	@Override
	public void draw(Graphics2D g) {
	}

	@Override
	protected void effect() {
		if(super.getHostile().isExpired()) {
			EntityLists.addNew(new Explosion(super.getHostile().getxCoor(), super.getHostile().getyCoor(), size, damage, super.getHostile().getCenterPoint()));
		}
	}
	
	public StatusEffect copy() {
		Detonate copy = new Detonate(size, damage);
		if(super.isActive()) copy.activate();
		return copy;
	}

	@Override
	protected void init(Hostile hostile) {
		super.activate();
	}

	@Override
	protected void finish() {
	}

}
