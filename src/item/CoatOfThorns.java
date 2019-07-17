package item;
import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class CoatOfThorns extends Item {
	
	private static final int ID = 4;
	private static final String ITEM_NAME = "Coat of Thorns";
	private static final Sprite SPRITE = new LightSprite("imgs/items/coatOfThorns.png", 4);

	public CoatOfThorns() {
		super(Item.STAT_BOOST, SPRITE);
	}
	
	@Override
	protected void instantEffect(Characters mainChar) {
		mainChar.getStats().setSpikeDamage(mainChar.getStats().getSpikeDamage() + 5);
	}

	@Override
	public void lingerEffect(Characters mainChar) {}

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
