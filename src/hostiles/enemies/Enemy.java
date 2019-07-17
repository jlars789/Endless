package hostiles.enemies;

import java.awt.Graphics2D;

import hostiles.Hostile;

public abstract class Enemy extends Hostile {
	
	public static int passiveCounter = 45;
	public static final int passiveCounterMax = 45;
	
	private boolean avoiding;
	
	/**
	 * Creates an enemy with a character reference
	 * @param xCoor X location of the enemy
	 * @param yCoor Y location of the enemy
	 * @param size The size of one of the sides of the enemy's square
	 * @param damage The damage upon contacting the hostile
	 * @param health The health of the enemy
	 * @param speed The pixels moved each frame
	 * @param pointVal points awarded upon killing the enemy
	 * @param charRef Character that is given as the character reference
	 */
	
	public Enemy(float xCoor, float yCoor, int size, double damage, int health, float speed, int pointVal) {
		super(xCoor, yCoor, size, damage, health, speed, pointVal);
	}	
	
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	public void knockBack(int distance, int direction) {
		super.shift(direction, distance);
	}
	
	public boolean isAvoiding() {
		return this.avoiding;
	}
	
	public void setAvoid(boolean avoid) {
		this.avoiding = avoid;
	}
	/*
	public AvoidBox getAvoid() {
		return this.avoidBox;
	}
	
	public Rectangle getAvoidBase() {
		return this.avoidBox.getGeneral();
	}
	*/
}
