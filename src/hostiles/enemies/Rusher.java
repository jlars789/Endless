package hostiles.enemies;
import hostiles.Behavior;
import hostiles.Hostile;
import sprite.LightSprite;

public class Rusher extends Enemy {
	
	private static final LightSprite S = new LightSprite("imgs/hostile/enemy/rusher.png");
	
	public Rusher(float xCoor, float yCoor) {
		super(xCoor, yCoor, 56, 25, 100, 1.5f, 10);
		super.setResistance(1);
		super.setSprite(S.copy());
	}

	@Override
	public Hostile copy() {
		return new Rusher(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.CHASING;
	}
	
}
