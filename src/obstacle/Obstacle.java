package obstacle;

import floor.room.Room;
import main.Hitbox;

public abstract class Obstacle extends Hitbox {

	private static final long serialVersionUID = -2007194713026431636L;
	private static final int WIDTH = Room.X_MAX/12;
	private static final int HEIGHT = Room.Y_MAX/9;
	
	//TODO At some point, make obstacles that block projectiles

	public Obstacle(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

}
