package floor.room.floor1room;

import floor.Floor;
import floor.room.NormalRoom;
import floor.room.Room;
import hostiles.Hostile;
import hostiles.enemies.Rusher;
import hostiles.enemies.Spitter;
import hostiles.enemies.Stomper;
import sprite.Sprite;

public class Floor1Room0 extends NormalRoom implements Cloneable {
	
	public static final Hostile[] ENEMY_LIST = {
			new Rusher(Room.CENTER_X, Room.CENTER_Y + 100), 
			new Spitter(Room.CENTER_X + 100, Room.CENTER_Y), 
			new Stomper(Room.CENTER_X, Room.CENTER_Y), 
			new Spitter(Room.CENTER_X - 100, Room.CENTER_Y), 
			new Rusher(Room.CENTER_X, Room.CENTER_Y - 100)
	};

	public Floor1Room0(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
	}
	
	public Floor1Room0() {
		super();
	}
	
	@Override
	public NormalRoom copy() {
		return new Floor1Room0();
	}

	@Override
	protected Hostile[] giveEnemyList() {
		return ENEMY_LIST;
	}

}
