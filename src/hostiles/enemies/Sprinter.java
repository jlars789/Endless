package hostiles.enemies;
import hostiles.Behavior;
import hostiles.Hostile;
import sprite.LightSprite;

public class Sprinter extends Enemy {
	
	private static final float SPEED = 2;
	
	private int sprintTimer;
	private static final int sprintTimerMax = 160;
	private float prevSpeed;
	
	private static final LightSprite S = new LightSprite("imgs/hostile/enemy/sprinter.png", 4);
	
	public Sprinter(float xCoor, float yCoor) {
		super(xCoor, yCoor, 56, 25, 100, SPEED, 10);
		super.setResistance(1);
		super.setSprite(S.copy());
		
		this.sprintTimer = 0;
	}

	@Override
	public Hostile copy() {
		return new Sprinter(super.getxCoor(), super.getyCoor());
	}
	
	@Override
	protected void behavior() {
		if(this.sprintTimer > 0) sprintTimer--;
		
		if(sprintTimer > 0 && sprintTimer < 60) {
			this.prevSpeed = SPEED;
			super.setSpeed(4);
		}
		else if(sprintTimer <= 0) {
			super.setSpeed(this.prevSpeed);
			sprintTimer = sprintTimerMax;
		}
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.CHASING;
	}

}
