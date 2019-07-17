package floor.room.door;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import characters.Characters;
import floor.room.Room;
import main.Gamepanel;
import main.Hitbox;
import sprite.HeavySprite;

public class Door {
	
	private int displayX;
	private int displayY;
	
	private static final int WIDTH = 100;
	private static final int HEIGHT = 10;
	
	private static final HeavySprite DEFAULT = new HeavySprite("imgs/doors/door.png", 25, 20, 1, 2, 0, true);
	private HeavySprite door = DEFAULT.copy();
	
	private boolean open;
	private int direction;
	
	private Hitbox contactBox;
	
	/**
	 * Creates a door that allows the player to be transported to a new room
	 * @param direction The direction in which the door will bring the player
	 */
	
	public Door(int direction) {
		this.open = false;
		this.direction = direction;
		this.contactBox = new Hitbox();
	}
	
	public void update() {
		if(Gamepanel.mainChar.getPlayerHitbox().intersects(this.contactBox) && this.open) {
			switch(this.direction) {
			case 0: Gamepanel.mapLocation[1]--;
					Gamepanel.mainChar.setLocation((Room.X_MAX/2) - 40, Room.Y_MAX - 100);
					break;
			case 1: Gamepanel.mapLocation[0]++;
					Gamepanel.mainChar.setLocation(Room.X_MIN + 50, (Room.Y_MAX/2) - 40);
					break;
			case 2: Gamepanel.mapLocation[1]++;
					Gamepanel.mainChar.setLocation((Room.X_MAX/2) - 40, Room.Y_MIN + 50);
					break;
			case 3: Gamepanel.mapLocation[0]--;
					Gamepanel.mainChar.setLocation(Room.X_MAX - 70, (Room.Y_MAX/2) - 40);
					break;
			default: break;
			}
			
			Gamepanel.leaveRoom();
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		if(!open) {
			g2d.drawImage(door.getRotated(1, 0, 90 * direction), null, displayX, displayY);
		}
		else g2d.drawImage(door.getRotated(0, 0, 90* direction), null, displayX, displayY);
	}
	
	/**
	 * Creates the door's contact box and location to align with its direction
	 */
	
	public void instantiate() {
		switch(direction) {
		case 0: 	
				this.contactBox.setX((Characters.START_XCOOR) - (WIDTH/2)); //up
				this.contactBox.setY(Room.Y_MIN);
				this.contactBox.setWidth(WIDTH);
				this.contactBox.setHeight(HEIGHT);
				displayY = 5;
				displayX = (int)this.contactBox.getX();
		break;
		
		case 1: 
			this.contactBox.setY((Characters.START_YCOOR) - (WIDTH/2));
			this.contactBox.setWidth(HEIGHT);
			this.contactBox.setX(Room.X_MAX - 5);
			this.contactBox.setHeight(WIDTH);
			displayX = (int)this.contactBox.getX();
			displayY = (int)this.contactBox.getY();
		break;
		
		case 2: this.contactBox.setX((Characters.START_XCOOR) - (WIDTH/2)); //down
				this.contactBox.setHeight(HEIGHT);
				this.contactBox.setY(Characters.Y_BOUNDS - HEIGHT);
				this.contactBox.setWidth(WIDTH);
				displayX = (int)this.contactBox.getX();
				displayY = (int)this.contactBox.getY();
		break;
		
		case 3: this.contactBox.setX(Characters.X_MIN); //left
				this.contactBox.setY((Characters.START_YCOOR) - (WIDTH/2));
				this.contactBox.setWidth(HEIGHT);
				this.contactBox.setHeight(WIDTH);
				displayX = 5;
				displayY = (int)this.contactBox.getY();
		break;
		
		}
		this.contactBox.update();
		open = false;
	}
	
	/**
	 * Opens the door, allowing it to be used
	 */
	
	public void open() {
		this.open = true;
	}
	
	/**
	 * Closes the door, preventing it from being used
	 */
	
	public void close() {
		this.open = false;
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public Rectangle getContactBox() {
		return this.contactBox;
	}
	
}
