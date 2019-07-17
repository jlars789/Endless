package floor;

import java.util.Random;

import floor.room.*;
import sprite.Sprite;

public abstract class Floor {
	
	private int maxSize;
	private int maxRooms;
	private int[][] map;
	
	private int startLocation;
	
	private Room[][] roomList;
	private int floorNumber;
	
	private NormalRoom[] roomPool;
	private int uniqueRoomCount;
	
	private Sprite roomBackground;
	private int treasureRoomCounter;
	
	/**
	 * Creates a floor that contains rooms
	 * @param maxSize Maximum x and y coordinates of the map
	 * @param maxRooms Maximum amount of rooms created
	 */

	public Floor(int maxSize, int maxRooms, int uniqueRoomCount, NormalRoom[] roomPool, Sprite background) {
		this.maxSize = maxSize;
		this.maxRooms = maxRooms;
		this.startLocation = (int)(Math.floor(maxSize/2));
		this.map = createArray(maxSize);
		this.roomList = new Room[maxSize][maxSize];
		this.roomBackground = background.copy();
		this.uniqueRoomCount = uniqueRoomCount;
		this.roomPool = roomPool;
		
		this.generator();
	}
	
	/**
	 * Generates a floor given the set amount of rooms and size. Modifies the 2DArray map variable
	 */
	
	private void generator() {
		
		for(int i = 0; i < maxSize; i++) {
			for(int j = 0; j < maxSize; j++) {
				this.roomList[j][i] = null;
				this.map[j][i] = 0;
			}
		}
		
		int roomTag = 1; //tags the room as created
		
		int currentX = startLocation; //the column that navigates the map
		int currentY = startLocation;
		
		map[currentY][currentX] = roomTag;
		
		int rooms = 0;
		
		while(rooms < maxRooms) {
			int direction = randomRange(0, 4);
			
			currentX = turn(direction, currentX, currentY)[0];
			currentY = turn(direction, currentX, currentY)[1];
			if(currentX > 0 && currentY > 0  && currentX < maxSize && currentY < maxSize) {
				if(map[currentY][currentX] != roomTag) {
					map[currentY][currentX] = roomTag;
					//roomList[currentY][currentX] = new NormalRoom(this, currentX, currentY, rooms);
					rooms++;
				}
			} else {
				currentX = startLocation;
				currentY = startLocation;
			}
		}
		
		perimeterScanner();
		bossRoomCreator();
		if(treasureRoomCounter < 1) {
			treasureRoomCreator();
		}
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[j][i] == 1 || map[j][i] == 2) {
					int randomChoice = randomRange(0, (uniqueRoomCount - 1));
					this.roomList[j][i] = roomPool[randomChoice].copy();
					this.roomList[j][i].instantiate(this, i, j, roomBackground.copy());
				} 
				else if(map[j][i] == 3) {
					roomList[j][i] = new BossRoom(this, i, j, this.roomBackground.copy());
				}
				else if(map[j][i] == 4) {
					if(treasureRoomCounter > 0) {
						map[j][i] = 0;
					} else {
						this.roomList[j][i] = new TreasureRoom(this, i, j, this.roomBackground.copy());
						this.treasureRoomCounter++;
					}
				}
			}
		}
		
		roomList[startLocation][startLocation] = new StartRoom(this, startLocation, startLocation, this.roomBackground.copy());
	}
	
	private int[] turn(int direction, int xIndex, int yIndex) {
		int[] newLocation = new int[2];
		newLocation[0] = xIndex;
		newLocation[1] = yIndex;
		switch (direction) {
		case 0: newLocation[1]--;
		break;
		case 1: newLocation[0] ++;
		break;
		case 2: newLocation[1] ++;
		break;
		case 3: newLocation[0]--;
		break;
		default: newLocation[0] = xIndex;
		newLocation[1] = yIndex;
		break;
		}
		
		return newLocation;
	}
	
	private int[][] createArray(int dimensions) {
		int[][] array = new int[dimensions][dimensions];
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				array[i][j] = 0;
			}
		}
		return array;
	}
	
	private void perimeterScanner() {
		for(int i = 1; i < map.length - 1; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[j][i - 1] < 1 && map[j][i] == 1) {
					map[j][i] = 2;
				}
				if(map[i - 1][j] < 1 && map[i][j] == 1) {
					map[i][j] = 2;
				}
			}
		}
		
		for(int i = map.length - 2; i > 1; i--) {
			for(int j = map.length - 1; j > 0; j--) {
				if(map[j][i + 1] < 1 && map[j][i] == 1) {
					map[j][i] = 2;
				}
				if(map[i + 1][j] < 1 && map[i][j] == 1) {
					map[i][j] = 2;
				}
			}
		}
	}
	
	private void bossRoomCreator() {
		double maxDistance = 0;
		int farX = 0;
		int farY = 0;
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[j][i] == 2) {
					int y = Math.abs(j - startLocation);
					int x = Math.abs(i - startLocation);
					double distance = Math.sqrt((x * x) + (y * y));
					if(distance >= maxDistance) {
						maxDistance = distance;
						farY = j;
						farX = i;
					}
				}
			}
		}
		
		boolean bossRoomMade = false;
		int counter = 0;
		while(bossRoomMade == false) {
			
			int randInt = randomRange(0, 4);
			
			switch(randInt) {
			case 0:
				if(farY + 1 < maxSize) {
					if(map[farY + 1][farX] == 0) {
						map[farY + 1][farX] = 3;
						bossRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 1: 
				if(farY - 1 > 0) {
					if(map[farY - 1][farX] == 0) {
						map[farY - 1][farX] = 3;
						bossRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 2:
				if(farX + 1 < maxSize) {
					if(map[farY][farX + 1] == 0) {
						map[farY][farX + 1] = 3;
						bossRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 3:
				if(farX - 1 > 0) {
					if(map[farY][farX - 1] == 0) {
						map[farY][farX - 1] = 3;
						bossRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
		   default: break;					
			}
			
			counter++;
			if(counter > 20) {
				counter = 0;
				this.generator();
				break;
			}
		}
	}
	
	private void treasureRoomCreator() {
		int tresX = 0;
		int tresY = 0;
		boolean tresCoorFound = false;
		
		while(tresCoorFound == false) {
			int randX = randomRange(0, maxSize-1);
			int randY = randomRange(0, maxSize-1);
			
			if(map[randY][randX] == 2) {
				tresX = randX;
				tresY = randY;
				tresCoorFound = true;
			}
		}
		
		
		
		int counter = 0;
		boolean tresRoomMade = false;
		while(tresRoomMade == false) {
			
			int randInt = randomRange(0, 4);
			
			switch(randInt) {
			case 0:
				if(tresY + 1 < maxSize) {
					if(map[tresY + 1][tresX] == 0) {
						map[tresY + 1][tresX] = 4;
						tresRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 1: 
				if(tresY - 1 > 0) {
					if(map[tresY - 1][tresX] == 0) {
						map[tresY - 1][tresX] = 4;
						tresRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 2:
				if(tresX + 1 < maxSize) {
					if(map[tresY][tresX + 1] == 0) {
						map[tresY][tresX + 1] = 4;
						tresRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			case 3:
				if(tresX - 1 > 0) {
					if(map[tresY][tresX - 1] == 0) {
						map[tresY][tresX - 1] = 4;
						tresRoomMade = true;
						break;
					} else {
						break;
					}
				} else {
					break;
				}
		   default: break;					
			}
			
			counter++;
			if(counter > 20) {
				tresX = 0;
				tresY = 0;
				treasureRoomCreator();
				counter = 0;
				break;
			}
		}
	}
	
	private int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public int[][] getMap(){
		return this.map;
	}
	
	public Room[][] getRoomList() {
		return this.roomList;
	}
	
	public int getFloorNumber() {
		return this.floorNumber;
	}
	
	public int getStartLocation() {
		return this.startLocation;
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}
	
}
