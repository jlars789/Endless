package console;

import characters.HUD;
import main.Gamepanel;

public class Teleport extends Command {

	public Teleport() {
	}

	@Override
	public String name() {
		return "teleport";
	}

	@Override
	public boolean validField(String args) {
		
		if(args.contains(",")) {
			int[] location = new int[2];
			int endIndex = args.indexOf(',');
			if(endIndex != -1) {
				location[0] = Integer.parseInt(args.substring(0, endIndex));
				location[1] = Integer.parseInt(args.substring(endIndex + 1));
			}
			
			if(HUD.getMap().validRoom(location[0], location[1])) {
				Gamepanel.mapLocation[0] = location[0];
				Gamepanel.mapLocation[1] = location[1];
				return true;
			} else {
				return false;
			}
		} else {
			if(args.equals("treasure")) {
				Gamepanel.mapLocation[0] = HUD.getMap().getTreaureRoomLocation()[1];
				Gamepanel.mapLocation[1] = HUD.getMap().getTreaureRoomLocation()[0];
				return true;
			}
			else if(args.equals("boss")) {
				Gamepanel.mapLocation[0] = HUD.getMap().getBossRoomLocation()[1];
				Gamepanel.mapLocation[1] = HUD.getMap().getBossRoomLocation()[0];
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void action() {
	}

	@Override
	public boolean forceClose() {
		return true;
	}

}
