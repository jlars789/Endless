package item;
import java.awt.Color;
import java.awt.Graphics2D;

import interactable.Interactable;
import main.Gamepanel;

public class ItemHolder extends Interactable {
	
	private static final int HEIGHT = 30;
	private static final int WIDTH = 40;
	
	private boolean grabbed;
	private Item heldItem;
	
	public ItemHolder(int xCoor, int yCoor, Item heldItem) {
		super(xCoor, yCoor, WIDTH, HEIGHT);
		this.heldItem = heldItem;
		this.heldItem.setDisplayCoor(xCoor - 10, yCoor - 70);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect((int)super.getContactBox().getX(), (int)super.getContactBox().getY(), (int)super.getContactBox().getWidth(), (int)super.getContactBox().getHeight());
		if(grabbed == false) {
			heldItem.draw(g);
		}
	}
	
	public void interact() {
		this.use();
	}
	
	@Override
	protected void use() {
		if(!grabbed) Gamepanel.mainChar.addItem(heldItem);
		this.grabbed = true;
	}
	
	public void move(int x, int y) {
		super.getContactBox().setLocation(x, y);
		this.heldItem.setDisplayCoor(x, y);
	}
	
	public Item getHeldItem() {
		return this.heldItem;
	}
	
	public void setHeldItem(Item item) {
		this.heldItem = item;
	}
	
	public boolean itemTaken() {
		return this.grabbed;
	}

	@Override
	public void end() {
	}

}
