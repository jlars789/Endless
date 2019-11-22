package console;

import floor.room.TreasureRoomPool;
import item.ItemHolder;
import main.Gamepanel;

public class SpawnItem extends Command {

	public SpawnItem() {
	}

	@Override
	public String name() {
		return "spawnitem";
	}

	@Override
	public boolean validField(String args0) {
		try {
			int id = Integer.parseInt(args0);
			if(id <= TreasureRoomPool.getFullList().length - 1) {
				for(int i = 0; i < TreasureRoomPool.getFullList().length; i++) {
					if(TreasureRoomPool.getFullList()[i].getID() == id) {
						Gamepanel.currentRoom.addObj(new ItemHolder((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getyCoor() + 100, 
								TreasureRoomPool.getFullList()[i]));
						break;
					}
				}
				return true;
			}
			else return false;
		} catch (NumberFormatException e) {
			if(args0.equals("all")) {
				for(int i = 0; i < TreasureRoomPool.getFullList().length; i++) {
					Gamepanel.currentRoom.addObj(new ItemHolder((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getyCoor() + 100, 
								TreasureRoomPool.getFullList()[i]));
				}
				return true;
			} 
			else return false;
		}
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean forceClose() {
		return false;
	}

}
