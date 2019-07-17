package abilities.Pirate;

import java.util.Random;

import abilities.Ability;
import abilities.EffectAbility;
import floor.room.Room;
import main.EntityLists;
import projectiles.Explosion;

public class Barrage extends EffectAbility {

	private static final long serialVersionUID = 663326499441315557L;
	
	public static int cost = 350;
	public static int duration = 180;
	
	public static int size = 200;
	public static int interval = 15;
	public static double damage = 120;

	public Barrage() {
		super(cost, true, duration);
	}
	
	public void update() {
		super.update();
		if(this.getDuration() % interval == 0) {
			float x = randomFloat(Room.X_MIN, Room.X_MAX);
			float y = randomFloat(Room.Y_MIN, Room.Y_MAX);
			float[] xy = {x + (size/2), y + (size/2)};
			EntityLists.addNew(new Explosion(x, y, size, damage, xy));
		}
	}

	@Override
	protected byte getType() {
		return Ability.USE_ON_PRESS;
	}
	
	private float randomFloat(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * (max - min)) + min;
	}

	@Override
	protected void effect() {
	}

}
