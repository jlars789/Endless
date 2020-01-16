package floor.room;
import java.util.Random;

import characters.Characters;
import floor.Floor;
import hostiles.bosses.Boss;
import hostiles.bosses.MegaTurret;
import hostiles.bosses.WallGrabberBoss;
import interactable.FloorPortal;
import interactable.Interactable;
import sprite.Sprite;

public class BossRoom extends Room {
	
	private static final Boss[] BOSS_LIST = {
		new MegaTurret(Room.CENTER_X - (MegaTurret.SIZE/2), Room.CENTER_Y - (MegaTurret.SIZE/2)), 
		new WallGrabberBoss((Characters.START_XCOOR) - 150, 0)
	};
	private static final Interactable INTERACT = new FloorPortal(Room.CENTER_X, Room.CENTER_Y);
	private boolean bossDefeated;

	public BossRoom(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
		INTERACT.setLocation(Room.CENTER_X, Room.CENTER_Y);
	}
	
	@Override
	protected void enterEffect() {
		if(!bossDefeated) this.bossCreate();
		else super.addObj(INTERACT);
	}
	
	private void bossCreate() {
		int randomBoss = randomRange(0, BOSS_LIST.length - 1);
		super.addObj((BOSS_LIST[randomBoss].copy()));
	}
	
	public int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	@Override
	protected void action() {
		bossDefeated = true;
		super.addObj(INTERACT);
	}

}
