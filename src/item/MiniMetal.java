package item;
import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class MiniMetal extends Item {
	
	private static final int ID = 3;
	private static final String ITEM_NAME = "Mini Metal";
	private static final Sprite SPRITE = new LightSprite("imgs/items/miniMetal.png", 4);

	public MiniMetal() {
		super(Item.STAT_BOOST, SPRITE);
	}
	
	@Override
	protected void instantEffect(Characters mainChar) {
		if(mainChar.getStats().getDamage() <= 1) {
			mainChar.getStats().setDamage(0.4);
		} else {
			mainChar.getStats().increaseDamage(-0.5);
		}
		
		mainChar.getStats().increaseAttackSpeed(6);
		mainChar.getMoveset().getWeapons()[0].setMagazineMax(mainChar.getMoveset().getWeapons()[0].getMagazineMax() * 2);
		mainChar.getMoveset().getWeapons()[1].setMagazineMax(mainChar.getMoveset().getWeapons()[1].getMagazineMax() * 2);
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
