package floor.room.floor1room;

import floor.*;
import floor.room.NormalRoom;
import floor.room.Room;
import hostiles.Hostile;
import hostiles.enemies.Bouncer;
import hostiles.enemies.Spitter;
import sprite.Sprite;

public class Floor1Room1 extends NormalRoom implements Cloneable {
	
	public static final Hostile[] ENEMY_LIST = { 
			new Spitter(Room.CENTER_X + 60, Room.CENTER_Y - 90), 
			new Spitter(Room.CENTER_X + 60, Room.CENTER_Y + 90),
			new Spitter(Room.CENTER_X - 60, Room.CENTER_Y - 90), 
			new Spitter(Room.CENTER_X - 60, Room.CENTER_Y + 90), 
			new Bouncer(Room.CENTER_X, Room.CENTER_Y)	
	};

	public Floor1Room1(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
		super.setEnemyList(ENEMY_LIST);
	}
	
	public Floor1Room1() {
		super();
	}

	@Override
	public NormalRoom copy() {
		return new Floor1Room1();
	}

	@Override
	protected Hostile[] giveEnemyList() {
		return ENEMY_LIST;
	}

	@Override
	protected void action() {
		
	}

}
