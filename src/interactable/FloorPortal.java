package interactable;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Gamepanel;

public class FloorPortal extends Interactable {
	
	private static final int SIZE = 75;

	private boolean pressed;
	
	private Color mainColor;

	public FloorPortal(int xCoor, int yCoor) {
		super(xCoor, yCoor, SIZE, SIZE);
		this.mainColor = Color.PINK;
	}
	
	public void draw(Graphics2D g) {	
		g.setColor(mainColor);
		g.fillRect((int)super.getContactBox().getX(), (int)super.getContactBox().getY(), (int)super.getContactBox().getWidth(), (int)super.getContactBox().getHeight());
	}
	
	public void use() {
		Gamepanel.floorGenerate();
		this.pressed = true;
	}
	
	public void end() {
		this.pressed = false;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}

}
