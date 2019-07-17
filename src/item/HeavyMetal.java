package item;
import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class HeavyMetal extends Item {
	
	private static final int ID = 2;
	private static final String ITEM_NAME = "Heavy Metal";
	private static final Sprite SPRITE = new LightSprite("imgs/items/heavyMetal.png", 4);

	public HeavyMetal() {
		super(Item.STAT_BOOST, SPRITE);
	}
	
	@Override
	protected void instantEffect(Characters mainChar) {
		mainChar.getStats().increaseDamage(1.2);
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
