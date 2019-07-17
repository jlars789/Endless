package item;

import java.awt.Graphics2D;

import characters.Characters;
import main.Gamepanel;
import main.StatTracker;
import sprite.LightSprite;

public class Accelerant extends Item {
	
	private static final int ID = 9;
	private static final String NAME = "Accelerant";
	private static final LightSprite SPRITE = new LightSprite("imgs/items/accelerant.png", 4);
	
	private double cumulativeInc;
	private int cumulativeShotsHit;
	private int shotsHitInRoom;
	private int comp;
	private static final double ATTACK_MULT = 0.2;
	private int[] prevLocation = new int[2];

	public Accelerant() {
		super(Item.CONSTANT_MOD, SPRITE);
		super.setAdded(false);
	}

	@Override
	protected void instantEffect(Characters mainChar) {
		this.prevLocation[0] = Gamepanel.mapLocation[0];
		this.prevLocation[1] = Gamepanel.mapLocation[1];
		this.cumulativeShotsHit = StatTracker.shotsHit;
		this.shotsHitInRoom = 0;
		this.comp = 0;
	}

	@Override
	public void lingerEffect(Characters mainChar) {
		this.shotsHitInRoom = StatTracker.shotsHit - cumulativeShotsHit;
		
		if(shotsHitInRoom > comp) {
			mainChar.getStats().increaseAttackSpeed(ATTACK_MULT);
			this.comp = shotsHitInRoom;
			this.cumulativeInc += ATTACK_MULT;
		}
		if(prevLocation[0] != Gamepanel.mapLocation[0] || prevLocation[1] != Gamepanel.mapLocation[1]) {
			this.cumulativeShotsHit = StatTracker.shotsHit;
			this.prevLocation[0] = Gamepanel.mapLocation[0];
			this.prevLocation[1] = Gamepanel.mapLocation[1];
			this.shotsHitInRoom = 0;
			this.comp = 0;
			mainChar.getStats().increaseAttackSpeed(-cumulativeInc);
			this.cumulativeInc = 0;
		}

	}

	@Override
	public boolean challengeItem() {
		return false;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void drawCostume(Graphics2D g2d) {}

}
