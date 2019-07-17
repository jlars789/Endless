package floor.room;

import floor.Floor;
import hostiles.Hostile;
import sprite.Sprite;

public abstract class NormalRoom extends Room implements Cloneable {
	

	public NormalRoom(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
	}
	
	public NormalRoom() {
		super();
	}
	
	public void instantiate(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super.instantiate(floor, xLocation, yLocation, sprite);
		super.setEnemyList(giveEnemyList());
	}
	
	public abstract NormalRoom copy();
	
	protected abstract Hostile[] giveEnemyList();

}
