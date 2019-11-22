package floor.room.floor1room;

import floor.Floor;
import floor.room.NormalRoom;
import floor.room.Room;
import hostiles.Hostile;
import hostiles.enemies.Enemy;
import hostiles.enemies.FlameSpewer;
import hostiles.enemies.Spitter;
import sprite.Sprite;

public class Floor1Room3 extends NormalRoom {
	
	private static final Enemy[] ENEMY_LIST = {
			new FlameSpewer(Room.CENTER_X - 40, 175),
			new FlameSpewer(Room.CENTER_X - 40, 235),
			new FlameSpewer(Room.CENTER_X - 40, 295),
			new FlameSpewer(Room.CENTER_X - 40, 355),
			new FlameSpewer(Room.CENTER_X - 40, 415),
			new FlameSpewer(Room.CENTER_X - 40, 475),
			new FlameSpewer(Room.CENTER_X - 40, 535),
			new Spitter(Room.CENTER_X - 60, Room.CENTER_Y + 90), 
	};
	
	public Floor1Room3(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
	}

	public Floor1Room3() {
		super();
	}

	@Override
	public NormalRoom copy() {
		return new Floor1Room3();
	}

	@Override
	protected Hostile[] giveEnemyList() {
		return ENEMY_LIST;
	}

}
