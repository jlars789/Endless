package floor.room.floor1room;

import floor.*;
import floor.room.NormalRoom;
import floor.room.Room;
import hostiles.Hostile;
import hostiles.enemies.Rusher;
import hostiles.enemies.Sprinter;
import sprite.Sprite;

public class Floor1Room2 extends NormalRoom implements Cloneable {
	
	public static final Hostile[] ENEMY_LIST = { 
			new Sprinter(Room.CENTER_X + 150, Room.CENTER_Y + 150), 
			new Sprinter(Room.CENTER_X - 150, Room.CENTER_Y + 150),
			new Rusher(Room.CENTER_X - 150, Room.CENTER_Y - 150), 
			new Rusher(Room.CENTER_X + 150, Room.CENTER_Y - 150)
	};

	public Floor1Room2(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
	}
	
	public Floor1Room2() {
		super();
	}

	@Override
	public NormalRoom copy() {
		return new Floor1Room2();
	}

	@Override
	protected Hostile[] giveEnemyList() {
		return ENEMY_LIST;
	}

}
