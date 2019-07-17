package item;

import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;
import weapons.DeathRay;

public class RedDoom extends Item {
	
	private static final int ID = 7;
	private static final String ITEM_NAME = "Red Doom";
	private static final Sprite SPRITE = new LightSprite("imgs/items/redDoom.png", 4);

	public RedDoom() {
		super(Item.STAT_BOOST, SPRITE);
	}
	
	@Override
	protected void instantEffect(Characters mainChar) {
		if(mainChar.getHeldWeapon().equals(mainChar.getMoveset().getWeapons()[0])) {
			mainChar.getMoveset().setWeapon(new DeathRay(), 0);
			mainChar.setHeldWeapon(mainChar.getMoveset().getWeapons()[0]);
		}
		else if(mainChar.getHeldWeapon().equals(mainChar.getMoveset().getWeapons()[1])) {
			mainChar.getMoveset().setWeapon(new DeathRay(), 1);
			mainChar.setHeldWeapon(mainChar.getMoveset().getWeapons()[1]);
		}
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
		return true;
	}

	@Override
	public void drawCostume(Graphics2D g2d) {}

}
