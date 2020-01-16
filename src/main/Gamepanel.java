package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import characters.Characters;
import console.Console;
import enemyprojectile.EnemyProjectile;
import floor.Floor;
import floor.Floor1;
import floor.room.Room;
import hostiles.enemies.Enemy;
import menus.GameOver;
import menus.Menu;
import menus.Title;
import statuseffect.StatusEffect;
import weapons.Weapon;

public class Gamepanel extends JPanel implements Runnable, KeyListener { 
	
	private static final long serialVersionUID = 1L;
	public static final int DEFWIDTH = 1440, DEFHEIGHT = 810;
	public static final int WIDTH = 1440, HEIGHT = 810; //window size (16:9)
	 
	//public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width, HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static double sizeMult = 1;
	
	private Thread thread;
	
	private static boolean running; //allows the game to be started/stopped
	public static boolean gameRunning;
	
	private final int TARGET_FPS = 60;
	private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	
	private int fps;
	private int lastFpsTime;
	private static int recordedFps;
	
	public static Menu currentMenu;
	
	public static Characters mainChar; 
	
	public static Floor currentFloor;
	public static Room currentRoom;
	public static int[] mapLocation = new int[2];
	
	public static double speedScalar = 1;
	
	public Gamepanel() {
		
		setFocusable(true);
		double wRatio = (double)WIDTH/(double)DEFWIDTH;
		double hRatio = (double)HEIGHT/(double)DEFHEIGHT;
		if(wRatio <= hRatio) sizeMult = wRatio;
		else sizeMult = hRatio;
		
		//TODO create offset for initial location in width or height
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT)); //window size
		addKeyListener(this); //allows key input from user
		
		EnemyProjectile.loadImages();
		StatusEffect.loadImages();
		currentMenu = new Title();
		
		start();
		
	}
	
	/**
	 * Method that begins the game
	 */
	
	private synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();		
	}
	
	/**
	 * Method that ends the game
	 */
	
	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Primary method of the game
	 */
	
	public synchronized void tick(double delta) {
		
		if(gameRunning) {
			
			mainChar.update(delta);
			Timer.increase();
			
			if(mainChar.isExpired()) {
				currentMenu = new GameOver();
				gameRunning = false;
			}
			
			if(mapLocation[0] != currentRoom.getxLocation() || mapLocation[1] != currentRoom.getyLocation()) {
				currentRoom = currentFloor.getRoomList()[mapLocation[1]][mapLocation[0]];
				currentRoom.enter();
			}
			
			currentRoom.update();
			enemyScan();
			EntityLists.update(delta);
		}
	}
	
	/**
	 * Graphics display for the game
	 * @param g Graphics import
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(gameRunning) this.layerDraw(g2d);
		else if(currentMenu != null) {
			this.drawBlackBackground(g2d);
			g2d.setColor(Color.WHITE);
			currentMenu.draw(g2d);
		}
	}
	
	/**
	 * Iterates through the ArrayList of enemies that currently exist
	 */
	
	public void enemyScan() {

		if(EntityLists.isListEmpty(EntityLists.HOST_IND)){
			if(!currentRoom.isCleared() && currentRoom instanceof Room) {
				mainChar.getStats().getHealth().heal(50);
				currentRoom.clear();
				System.out.println("Hit");
			}
		} else {
			if(Enemy.passiveCounter > 0) {
				Enemy.passiveCounter--;
			}
		}
	}

	@Override
	public synchronized void run() {
		long lastLoopTime = System.nanoTime();
		double delta = 0;
		while(running) {
			
			long now = System.nanoTime();
		    long updateLength = now - lastLoopTime;
		    
		    delta += updateLength / ((double)OPTIMAL_TIME);
		    lastLoopTime = now;
		    lastFpsTime += updateLength;
		   
			
		    if(delta >= 1) {
		    	tick(speedScalar);
		    	repaint(0, 0, WIDTH, HEIGHT);
		    	delta--;
		    	
				fps++;
		    	if(lastFpsTime >= 1000000000) {
			         recordedFps = fps;
			         lastFpsTime = 0;
			         fps = 0;
		    	}
		    	try {
					Thread.sleep((long) 16.66667); 
				} catch(Exception e) {
					e.printStackTrace();
				} 
		    }
			
		}
		stop();
	}
	

	@Override
	public void keyPressed(KeyEvent e) { //controls
		int key = e.getKeyCode();
		if(gameRunning && currentMenu == null) {
			if(key == KeyEvent.VK_I) {
				reset();
			}
			if(key == KeyEvent.VK_C) {
				currentMenu = new Console();
			}
			mainChar.giveKeyCode(key);
		}
		
		else if(currentMenu != null) {
			if(key == KeyEvent.VK_UP) currentMenu.selectMove(0);
			else if(key == KeyEvent.VK_DOWN) currentMenu.selectMove(2);
			
			if(key == KeyEvent.VK_RIGHT) currentMenu.selectMove(1);
			else if(key == KeyEvent.VK_LEFT) currentMenu.selectMove(3);
			
			if(key == KeyEvent.VK_ENTER) currentMenu.select();
			else if(key == KeyEvent.VK_ESCAPE) currentMenu.back();
			
			else if(key > 0) currentMenu.customKey(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(mainChar != null && gameRunning) {
			if(key == KeyEvent.VK_D) mainChar.stop(1);
			if(key == KeyEvent.VK_S) mainChar.stop(2);
			if(key == KeyEvent.VK_W) mainChar.stop(0);
			if(key == KeyEvent.VK_A) mainChar.stop(3);
			
			
			if(key == KeyEvent.VK_UP) mainChar.setShoot(false);
			if(key == KeyEvent.VK_RIGHT) mainChar.setShoot(false);
			if(key == KeyEvent.VK_DOWN) mainChar.setShoot(false);
			if(key == KeyEvent.VK_LEFT) mainChar.setShoot(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public synchronized static void gameStart() {
		floorGenerate();
		Timer.begin();
		gameRunning = true;
		Weapon.setCharacter(mainChar);
	}
	
	public synchronized static void floorGenerate() {
		currentFloor = new Floor1();
		mapLocation[0] = currentFloor.getStartLocation();
		mapLocation[1] = currentFloor.getStartLocation();
		currentRoom = currentFloor.getRoomList()[mapLocation[1]][mapLocation[0]];
		EntityLists.removeEntities(true);
	}
	
	
	public static void reset() {
		
		mainChar.reset();
		EntityLists.removeEntities(false);
		
		for(int i = 0; i < 4; i++) {
			mainChar.stop(i);
		}
		
		StatTracker.reset();
		
		Timer.begin();
		floorGenerate();
	}
	
	public static void leaveRoom() {
		
		EntityLists.removeEntities(true);
		Enemy.passiveCounter = Enemy.passiveCounterMax;
		mainChar.setDoorImmune(true);
		currentRoom.exit();
	}
	
	public static int getFps() {
		return Gamepanel.recordedFps;
	}
	
	private void layerDraw(Graphics2D g2d) {
		currentRoom.draw(g2d);
		Timer.draw(g2d);
		
		EntityLists.drawInteracts(g2d);
		
		ProjectileDraw.draw(g2d, 0);
		
		mainChar.draw(g2d);
		
		EntityLists.drawEntities(g2d);
		
		ProjectileDraw.draw(g2d, 1);
		
		if(currentMenu != null) {
			currentMenu.draw(g2d);
		}
		
		ProjectileDraw.draw(g2d, 2);
	}
	
	private void drawBlackBackground(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
}