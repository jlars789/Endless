package interactable;
import java.awt.Color;
import java.awt.Graphics2D;

import floor.room.TreasureRoom;
import hostiles.Hostile;
import hostiles.bosses.minibosses.Miniboss;
import main.EntityLists;
import main.Gamepanel;

public class Summoner extends Interactable {
	
	private static final int SIZE = 75;
	
	private boolean pressed;
	private Hostile[] spawn;
	
	private Color mainColor;

	public Summoner(int xCoor, int yCoor, boolean reusable, Color color, Hostile spawn) {
		super(xCoor, yCoor, SIZE, SIZE);
		
		//TODO Change constructor argument to Array of Hostiles
		this.spawn = new Hostile[1];
		this.spawn[0] = spawn;
		
		this.mainColor = color;
	}
	
	public void draw(Graphics2D g) {	
		g.setColor(mainColor);
		g.fillRect((int)super.getContactBox().getX(), (int)super.getContactBox().getY(), (int)super.getContactBox().getWidth(), (int)super.getContactBox().getHeight());
	}
	
	@Override
	public void use() {
		this.pressed = true;
		Gamepanel.currentRoom.close();
		for(int i = 0; i < EntityLists.getList(EntityLists.INTR_IND).size(); i++) {
			if(EntityLists.getList(EntityLists.INTR_IND).get(i) instanceof Summoner) {
				((Interactable) EntityLists.getList(EntityLists.INTR_IND).get(i)).setExpired();
			}
		}
		
		TreasureRoom.bossSpawned = true;
		
		if(this.mainColor == Color.MAGENTA) {
			((Miniboss)(spawn[0])).makeChallenger();
			TreasureRoom.challengeSpawn = true;
		}
		
		this.spawn();
	}
	
	@Override
	public void end() {
		this.pressed = false;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}
	
	private void spawn() {
		this.spawn[0].setCharacter(Gamepanel.mainChar);
		EntityLists.addNew(this.spawn);
	}
	
	public Hostile[] getHostiles() {
		return this.spawn;
	}

}
