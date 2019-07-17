package floor.room;

import java.util.ArrayList;

import item.*;
import main.Gamepanel;

public class TreasureRoomPool extends ItemPool {
	
	private static Item[] NORMAL_LIST = {
			new BloodVial(), 
			new HeavyMetal(), 
			new Sprockets(), 
			new MiniMetal(), 
			new CoatOfThorns(), 
			new DarkHeart(), 
			new Spinnigun(), 
			new RedDoom(), 
			new VeryHot(), 
			new Accelerant(), 
			new Adrenaline(),
			new LaserSight()
	};
	
	public static ArrayList<Item> getNormalList() {
		ArrayList<Item> list = new ArrayList<Item>();
		
		for(int i = 0; i < NORMAL_LIST.length; i++) {
			if(!inPool(NORMAL_LIST[i]) && !NORMAL_LIST[i].challengeItem()) {
				list.add(NORMAL_LIST[i]);
			}
		}
		
		return list;
		
	}
	
	public static ArrayList<Item> getChallengeList() {
		ArrayList<Item> list = new ArrayList<Item>();
		
		for(int i = 0; i < NORMAL_LIST.length; i++) {
			if(!inPool(NORMAL_LIST[i]) && NORMAL_LIST[i].challengeItem()) {
				list.add(NORMAL_LIST[i]);
			}
		}
		
		return list;
		
	}
	
	public static Item[] getFullList() {
		return NORMAL_LIST;
	}
	
	private static boolean inPool(Item item) {
		boolean hasItem = false;
		for(int i = 0; i < Gamepanel.mainChar.getInventory().size(); i++) {
			if(item.getID() == Gamepanel.mainChar.getInventory().get(i).getID()) {
				hasItem = true;
			}
		}
		
		return hasItem;
	}

}
