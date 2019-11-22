package hostiles.enemies;

import hostiles.Behavior;
import hostiles.Hostile;
import main.EntityLists;
import sprite.LightSprite;

public class FlameSpewer extends Enemy {
	
	private static int size = 60;
	private static double damage = 25;
	private static int health = 120;
	private static final float speed = 0;
	private static final int pointVal = 0;
	
	private static final LightSprite D = new LightSprite("imgs/hostile/enemy/flamespew.png");

	public FlameSpewer(float xCoor, float yCoor) {
		super(xCoor, yCoor, size, damage, health, speed, pointVal);
		super.setResistance(1);
		super.setSprite(D.copy());
	}

	@Override
	protected void behavior() {
		boolean friends = false;
		for(int i = 0; i < EntityLists.getHostileList().size(); i++) {
			if(!(EntityLists.getHostileList().get(i) instanceof FlameSpewer)) {
				friends = true;
				break;
			}
		}
		if(!friends) {
			this.kill();
		}
		System.out.println(super.getHealth().getValues()[0]);
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.PHASE;
	}

	@Override
	public Hostile copy() {
		return new FlameSpewer(super.getxCoor(), super.getyCoor());
	}

}
