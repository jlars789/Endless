package item;

import characters.Characters;
import sprite.Sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Item {
	
	public static final byte STAT_BOOST = 0;
	public static final byte CONSTANT_MOD = 1;
	
	private byte itemType;
	
	private int displayX;
	private int displayY;
	
	private Sprite sprite;
	
	private boolean collected;
	private boolean added;
	
	public Item(byte type, Sprite sprite) {
		this.itemType = type;
		this.sprite = sprite;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(sprite.getImage(), null, displayX, displayY);
	}
	
	public void add(Characters mainChar) {
		instantEffect(mainChar);
		this.added = true;
	}
	
	protected abstract void instantEffect(Characters mainChar);
	
	public abstract void lingerEffect(Characters mainChar);
	
	public abstract boolean challengeItem();
	
	public int getItemType() {
		return this.itemType;
	}
	
	public abstract int getID();
	
	public byte getType() {
		return this.itemType;
	}
	
	public abstract String getName();
	
	public abstract void drawCostume(Graphics2D g2d);
	
	public boolean isCollected() {
		return this.collected;
	}
	
	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	public boolean isAdded() {
		return this.added;
	}
	
	public void setAdded(boolean added) {
		this.added = added;
	}
	
	public void setDisplayCoor(int xCoor, int yCoor) {
		this.displayX = xCoor;
		this.displayY = yCoor;
	}

}
