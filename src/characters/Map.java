package characters;

import java.awt.Color;
import java.awt.Graphics2D;

import floor.room.BossRoom;
import floor.room.TreasureRoom;
import main.Gamepanel;
import sprite.LightSprite;

public class Map {
	
	private static final LightSprite MAP_BG = new LightSprite("imgs/hud/mapBG.png");
	
	private static final int WIDTH = (int)(100 * Gamepanel.sizeMult);
	private int size;

	public Map(int size) {
		this.size = (int)(size * Gamepanel.sizeMult);
	}
	
	//TODO create icons for rooms
	
	public void draw(Graphics2D g2d) {

		g2d.drawImage(MAP_BG.getImage(), null, Gamepanel.WIDTH - WIDTH, 0);
		
		for(int i = 0; i < Gamepanel.currentFloor.getRoomList().length; i++) {
			for(int j = 0; j < Gamepanel.currentFloor.getRoomList().length; j++) {
				if(i == Gamepanel.mapLocation[1] && j == Gamepanel.mapLocation[0]) {
					g2d.setColor(Color.YELLOW);
					g2d.fillRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				else if(Gamepanel.currentFloor.getRoomList()[i][j] != null && Gamepanel.currentFloor.getRoomList()[i][j].isCleared()) {
					g2d.setColor(Color.WHITE);
					g2d.fillRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				else if(Gamepanel.currentFloor.getRoomList()[i][j] != null && !Gamepanel.currentFloor.getRoomList()[i][j].isCleared()) {
					g2d.setColor(Color.GRAY);
					g2d.fillRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				if(Gamepanel.currentFloor.getRoomList()[i][j] != null && Gamepanel.currentFloor.getRoomList()[i][j] instanceof BossRoom) {
					g2d.setColor(Color.RED);
					g2d.fillRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				if(Gamepanel.currentFloor.getRoomList()[i][j] != null && Gamepanel.currentFloor.getRoomList()[i][j] instanceof TreasureRoom) {
					g2d.setColor(Color.ORANGE);
					g2d.fillRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				
				if(Gamepanel.currentFloor.getRoomList()[i][j] != null) {
					g2d.setColor(Color.BLACK);
					g2d.drawRect((j * size) + Gamepanel.WIDTH - WIDTH, i * size, size, size);
				}
				g2d.setColor(Color.BLACK);
				g2d.drawRect(Gamepanel.WIDTH - WIDTH, 0, WIDTH, WIDTH);
			}
		}	
	}
	
	public boolean validRoom(int x, int y) {
		return (Gamepanel.currentFloor.getRoomList()[y][x] != null);
	}
	
	public int[] getBossRoomLocation() {
		int[] arr = new int[2];
		for(int i = 0; i < Gamepanel.currentFloor.getRoomList().length; i++) {
			for(int j = 0; j < Gamepanel.currentFloor.getRoomList().length; j++) {
				if(Gamepanel.currentFloor.getRoomList()[i][j] != null && Gamepanel.currentFloor.getRoomList()[i][j] instanceof BossRoom) {
					arr[0] = i;
					arr[1] = j;
					break;
				}
			}
		}
		return arr;
	}
	
	public int[] getTreaureRoomLocation() {
		int[] arr = new int[2];
		for(int i = 0; i < Gamepanel.currentFloor.getRoomList().length; i++) {
			for(int j = 0; j < Gamepanel.currentFloor.getRoomList().length; j++) {
				if(Gamepanel.currentFloor.getRoomList()[i][j] != null && Gamepanel.currentFloor.getRoomList()[i][j] instanceof TreasureRoom) {
					arr[0] = i;
					arr[1] = j;
					break;
				}
			}
		}
		return arr;
	}

}
