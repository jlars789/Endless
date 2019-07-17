package item;

import java.awt.Graphics2D;

import characters.Characters;
import main.Gamepanel;
import sprite.LightSprite;
import sprite.Sprite;

public class VeryHot extends Item {
	
	public static final int ID = 8;
	private static final String ITEM_NAME = "Veryhot";
	private static final Sprite SPRITE = new LightSprite("imgs/items/veryhot.png", 4);

	public VeryHot() {
		super(Item.CONSTANT_MOD, SPRITE);
	}

	@Override
	protected void instantEffect(Characters mainChar) {
		mainChar.getStats().increaseDamage(0.05);
	}

	@Override
	public void lingerEffect(Characters mainChar) {
		boolean move = false;
		for(int i = 0; i < 4; i++) {
			if(mainChar.getMove()[i]) {
				move = true;
				break;
			}
		}
		if(!move) {
			Gamepanel.speedScalar = 0;
			
		} 
		else {
			Gamepanel.speedScalar = 1;
		}
	}

	@Override
	public boolean challengeItem() {
		return true;
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
	public void drawCostume(Graphics2D g2d) {}

}
