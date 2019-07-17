package floor;
import floor.room.NormalRoom;
import floor.room.floor1room.Floor1Room0;
import floor.room.floor1room.Floor1Room1;
import floor.room.floor1room.Floor1Room2;
import floor.room.floor1room.Floor1Room3;
import sprite.LightSprite;
import sprite.Sprite;

public class Floor1 extends Floor {
	
	public static final byte FLOOR_NUM = 1;
	
	public static final int MAX_SIZE = 10;
	public static final int MAX_ROOMS = 10;
	public static final Sprite BACKGROUND = new LightSprite("imgs/rooms/def(16x9).png");
	public static final NormalRoom[] ROOM_POOL = {
			new Floor1Room0(),
			new Floor1Room1(), 
			new Floor1Room2(),
			new Floor1Room3()
	};
	
	public Floor1() {
		super(MAX_SIZE, MAX_ROOMS, ROOM_POOL.length, ROOM_POOL, BACKGROUND.copy());
	}
	
}
