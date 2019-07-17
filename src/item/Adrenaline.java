package item;

import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class Adrenaline extends Item {
	
	private static final int ID = 10;
	private static final String NAME = "Adrenaline";
	private static final Sprite SPRITE = new LightSprite("imgs/items/bloodVial.png");
	
	private double prevHealth;

	public Adrenaline() {
		super(Item.CONSTANT_MOD, SPRITE);
	}

	@Override
	protected void instantEffect(Characters mainChar) {
		prevHealth = mainChar.getStats().getHealth().getValues()[1];
	}

	@Override
	public void lingerEffect(Characters mainChar) {
		double health = mainChar.getStats().getHealth().getValues()[0];
		if(health != prevHealth) {
			double diff = ((prevHealth - health)/30);
			mainChar.getStats().increaseDamage(diff);
			if(mainChar.getStats().getDamage() < 1) mainChar.getStats().setDamage(1);
			prevHealth = health;
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
