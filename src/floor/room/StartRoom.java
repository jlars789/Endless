package floor.room;

import floor.*;
import sprite.Sprite;

public class StartRoom extends Room {
	
	public StartRoom(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
		super.setEntered(true);
		super.clear();
	}

	@Override
	protected void action() {
		
	}

	@Override
	protected void enterEffect() {
	}

}
