package floor.room;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import floor.Floor;
import hostiles.Hostile;
import hostiles.bosses.minibosses.Bruiser;
import interactable.Summoner;
import item.Item;
import item.ItemHolder;
import main.Entity;
import sprite.Sprite;

public class TreasureRoom extends Room {
	
	public static boolean bossSpawned;
	
	private int defItemX;
	private int defItemY;
	
	private boolean itemAdded;
	
	private ArrayList<Entity> interactables;
	private Hostile hostileList[];
	
	public static boolean challengeSpawn;

	public TreasureRoom(Floor floor, int xLocation, int yLocation, Sprite sprite) {
		super(floor, xLocation, yLocation, sprite);
		this.hostileList = new Hostile[1];
		bossPooler();
		
		this.interactables = new ArrayList<Entity>();
		this.interactCreate();
		
		bossSpawned = false;
		challengeSpawn = false;
	}
	
	private void itemCreate() {
		Item randomItem;
		
		if(challengeSpawn) {
			randomItem = TreasureRoomPool.getChallengeList().get(this.randomRange(0, TreasureRoomPool.getChallengeList().size() - 1));
		} else {
			randomItem = TreasureRoomPool.getNormalList().get(this.randomRange(0, TreasureRoomPool.getNormalList().size() - 1));
			//randomItem = TreasureRoomPool.getNormalList().get(TreasureRoomPool.getNormalList().size() -1);
		}
		
		this.defItemX = Room.CENTER_X - 40;
		this.defItemY = Room.CENTER_Y - 64;
		
		//randomItem.setDisplayCoor(defItemX - 10, defItemY - 70);
		interactables.add(new ItemHolder(defItemX, defItemY, randomItem));
	}
	
	private Hostile bossCreate() {
		int randomBoss = randomRange(0, (hostileList.length - 1));
		return hostileList[randomBoss];
	}
	
	public void bossPooler() {
		this.hostileList[0] = new Bruiser(Room.CENTER_X - 50, Room.CENTER_Y - 150, false);
	}
	
	public void enter() {
		if(!bossSpawned) super.setEntered(false);
		super.enter();
		super.addObj(interactables);
	}
	
	private void interactCreate() {
		interactables.add(new Summoner(Room.CENTER_X - 150, Room.CENTER_Y + 100, false, Color.GREEN, bossCreate()));
		interactables.add(new Summoner(Room.CENTER_X + 150, Room.CENTER_Y + 100, false, Color.MAGENTA, bossCreate()));
	}
	
	
	private int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	@Override
	protected void action() {
		if(!itemAdded && bossSpawned) {
			interactables.clear();
			this.itemCreate();
			super.addObj(interactables);
		}
	}

	@Override
	protected void enterEffect() {
		// TODO Auto-generated method stub
		
	}

}
