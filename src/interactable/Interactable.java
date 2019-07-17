package interactable;
import java.awt.Graphics2D;

import main.Entity;
import main.Gamepanel;
import main.Hitbox;

public abstract class Interactable implements Entity {
	
	private boolean used;
	private Hitbox contactBox;

	public Interactable(int xCoor, int yCoor, int width, int height) {
		this.contactBox = new Hitbox((int)xCoor, (int)yCoor, width, height);
	}
	
	public void update(double delta) {
		if(Gamepanel.mainChar.getPlayerHitbox().intersects(this.contactBox)) {
			this.interact();
		}
	}
	
	protected abstract void use(); 
	
	protected abstract void end();
	
	public abstract void draw(Graphics2D g2d);
	
	protected void setExpired() {
		this.used = true;
	}
	
	public boolean isExpired() {
		return this.used;
	}
	
	public void interact() {
		use();
		this.used = true;
		end();
	}
	
	public Hitbox getContactBox() {
		return this.contactBox;
	}
	
	public void setLocation(float x, float y) {
		this.contactBox.setLocation((int)x, (int)y);
	}

}
