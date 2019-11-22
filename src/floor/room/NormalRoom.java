package floor.room;

import floor.Floor;
import hostiles.Hostile;
import main.EntityLists;
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
	}
	
	public void action() {
		
	}
	
	@Override
	protected void enterEffect() {
		EntityLists.addNew(copyList());
	}
	
	protected Hostile[] copyList() {
		Hostile[] newH = new Hostile[giveEnemyList().length];
		for(int i = 0; i < giveEnemyList().length; i++) {
			newH[i] = giveEnemyList()[i].copy();
		}
		return newH;
	}
	
	public abstract NormalRoom copy();
	
	protected abstract Hostile[] giveEnemyList();

}
