package item;
import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class BloodVial extends Item {
	
	private static final int ID = 0;
	private static final String ITEM_NAME = "Blood Vial";
	private static final Sprite SPRITE = new LightSprite("imgs/items/bloodVial.png");
	
	public BloodVial() {
		super(Item.STAT_BOOST, SPRITE);
	}
	
	@Override
	protected void instantEffect(Characters mainChar) {
		mainChar.getStats().getHealth().incMax(100);
		mainChar.getStats().getHealth().heal(100);
	}
	
	public void lingerEffect(Characters mainChar) {
		
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return ITEM_NAME;
	}

	@Override
	public boolean challengeItem() {
		return false;
	}

	@Override
	public void drawCostume(Graphics2D g2d) {}

}
