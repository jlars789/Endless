package item;

import java.awt.Graphics2D;

import characters.Characters;
import sprite.LightSprite;
import sprite.Sprite;

public class DarkHeart extends Item {
	
	private static final int ID = 5;
	private static final String ITEM_NAME = "Dark Heart";
	private static final Sprite SPRITE = new LightSprite("imgs/items/darkheart.png", 4);
	
	private boolean damageAdded;
	//private double prevDamage;
	public static double damageBoost = 7;
	private boolean healthTaken;
	
	public DarkHeart() {
		super(Item.CONSTANT_MOD, SPRITE);
	}
	
	@Override
	public void lingerEffect(Characters character) {
		itemEffect(character);
	}
	
	private void itemEffect(Characters character) {
		if(character.getMoveset().getWeapons()[0].getMagazine() <= 1 && character.getHeldWeapon().equals(character.getMoveset().getWeapons()[0])) {
			if(!damageAdded) {
				character.getStats().increaseDamage(damageBoost);
				damageAdded = true;
			}
			if(!character.getMoveset().getWeapons()[0].isChambered() && character.isShoot()) {
				if(!healthTaken) {
					character.getStats().getHealth().damage(((double)(character.getMoveset().getWeapons()[0].getFireRate())/character.getStats().getHealth().getValues()[1] * 75));
				}
			}
			
			if(character.getMoveset().getWeapons()[0].isChambered() && character.isShoot()) {
				character.getMoveset().getWeapons()[0].reload();
				character.getMoveset().getWeapons()[0].setMagazine(1);
			}
			
		} else if(damageAdded && !(character.getHeldWeapon().equals(character.getMoveset().getWeapons()[0]))) {
			character.getStats().increaseDamage(-damageBoost);
			damageAdded = false;
		}
	}

	@Override
	protected void instantEffect(Characters mainChar) {}

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
