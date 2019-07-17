package hostiles.enemies;
import hostiles.Behavior;
import hostiles.Hostile;
import sprite.LightSprite;

public class Stomper extends Enemy {
	
	private static final LightSprite D = new LightSprite("imgs/hostile/enemy/stomper.png", 4);

	public Stomper(float xCoor, float yCoor) {
		super(xCoor, yCoor, 80, 50, 300, 1, 25);
		
		super.setResistance(1.2);
		super.setSprite(D.copy());
	}

	@Override
	public Hostile copy() {
		return new Stomper(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.CHASING;
	}
	
}
