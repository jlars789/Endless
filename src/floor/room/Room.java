package floor.room;
import java.awt.Graphics2D;
import java.util.ArrayList;

import characters.Characters;
import floor.Floor;
import floor.room.door.Door;
import hostiles.Hostile;
import main.Entity;
import main.EntityLists;
import main.Gamepanel;
import sprite.Sprite;

public abstract class Room {
	
	private int xLocation;
	private int yLocation;
	
	public static final int CENTER_X = (Gamepanel.WIDTH - (Characters.CHARACTER_WIDTH/2))/2;
	public static final int CENTER_Y = (Gamepanel.HEIGHT - (Characters.CHARACTER_HEIGHT/2))/2;
	
	public static final int ROOM_WIDTH = (int)(80 * Gamepanel.sizeMult);
	public static final int ROOM_HEIGHT = (int)(80 * Gamepanel.sizeMult);
	
	//1280 x 640
	
	public static final int X_MIN = ROOM_WIDTH;
	public static final int Y_MIN = ROOM_HEIGHT;
	public static final int X_MAX = Gamepanel.WIDTH - ROOM_WIDTH;
	public static final int Y_MAX = Gamepanel.HEIGHT - ROOM_HEIGHT;
	
	private Floor floor;
	
	private Door[] door;
	private int doorCount;
	
	private ArrayList<Entity> entity;
	
	private boolean created;
	private boolean entered;
	
	private boolean extraSpawn;
	private boolean spawnAdded;
	
	private Sprite background;
	
	private boolean cleared;
	
	private Characters charRef; 
	
	public Room(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		this.floor = floor;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		
		this.instance();
		
		this.background = sprite;
	}
	
	public Room() {
		this.created = false;
	}
	
	public void instantiate(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		this.floor = floor;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		
		this.instance();
		
		this.background = sprite;
	}
	
	private void instance() {
		roomScan();
		this.door = new Door[doorCount];
		this.created = true;
		roomScan();
	
		this.entity = new ArrayList<Entity>();
		this.charRef = Gamepanel.mainChar;
	}
	
	protected void addComponents(ArrayList<Entity> entities) {
		this.entity = entities;
	}
	
	public void enter() {
		this.enemyCreate();
		this.entered = true;
	}
	
	public void exit() {
		this.entered = false;
	}
	
	protected void enemyCreate() {
		this.addEntities(entity);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.background.getImage(), null, 0, 0);
		for(int i = 0; i < door.length; i++) {
			door[i].draw(g2d);
		}
		/*
		int w = 12;
		int h = 9;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				g2d.drawLine(((i * Room.X_MAX)/w) + Room.ROOM_WIDTH, Room.X_MIN,
						((i * Room.X_MAX)/w) + Room.ROOM_WIDTH, Room.Y_MAX);
				g2d.drawLine(Room.X_MIN, (j * Room.Y_MAX)/h, Room.X_MAX, (j * Room.Y_MAX)/h);
			}
		}
		*/
	}
	
	private void roomScan() {
		int doorIndex = doorCount - 1;
		int[][] tempMap = floor.getMap();
		
		if(yLocation > 0) {
			if(tempMap[yLocation - 1][xLocation] >= 1) {
				if(created) {
					door[doorIndex] = new Door(0);
					doorIndex--;
				} else {
					doorCount++;
				}
			}
		}
		if(yLocation < floor.getMap().length - 1) {
			if(tempMap[yLocation + 1][xLocation] >= 1) {
				if(created) {
					door[doorIndex] = new Door(2);
					doorIndex--;
				} else {
					doorCount++;
				}
			}
		}
		if(xLocation < floor.getMap().length - 1) {
			if(tempMap[yLocation][xLocation + 1] >= 1) {
				if(created) {
					door[doorIndex] = new Door(1);
					doorIndex--;
				} else {
					doorCount++;
				}
			}
		}
		if(xLocation > 0) {
			if(tempMap[yLocation][xLocation - 1] >= 1) {
				if(created) {
					door[doorIndex] = new Door(3);
				} else {
					doorCount++;
				}
			}
		}
		
		if(created) {
			for(int i = 0; i < door.length; i++) {
				door[i].instantiate();
			}
		}
	}
	
	private void removeHostiles() {
		for(int i = 0; i < entity.size(); i++) {
			if(entity.get(i) instanceof Hostile) {
				entity.remove(i);
				if(i != 0) i--;
			}
		}
	}
	
	protected void setEnemyList(Hostile[] h) {
		for(int i = 0; i < h.length; i++) {
			entity.add(h[i].copy());
		}
	}
	
	protected void setEnemy(Hostile h) {
		entity.add(h);
	}
	
	protected void addObject(Entity e) {
		entity.add(e);
	}
	
	public void addObjDirect(Entity e) {
		entity.add(e);
		EntityLists.entities.add(e);
	}
	
	public void addObjDirect(ArrayList<Entity> entity) {
		for(int i = 0; i < entity.size(); i++) {
			this.entity.add(entity.get(i));
		}
		EntityLists.addNew(entity);
	}
	
	public void clear() {
		this.cleared = true;
		for(int i = 0; i < door.length; i++){
			door[i].open();
		}
		this.removeHostiles();
		this.action();
	}
	
	protected abstract void action();
	
	public void close() {
		this.cleared = false;
		for(int i = 0; i < door.length; i++) {
			door[i].close();
		}
	}
	
	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	public int getDoorAmount() {
		return this.doorCount;
	}
	
	public Door[] getDoors() {
		return this.door;
	}
	
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	public boolean isEntered() {
		return this.entered;
	}
	
	public void setEntered(boolean entered) {
		this.entered = entered;
	}
	
	public boolean hasExtraSpawns() {
		return this.extraSpawn;
	}
	
	public boolean spawnAdded() {
		return this.spawnAdded;
	}
	
	public Characters getCharacter() {
		return this.charRef;
	}
	
	public void addEntities(ArrayList<Entity> e) {
		EntityLists.addNew(e);
	}

}
