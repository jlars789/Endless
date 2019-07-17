package hostiles.enemies;
import java.util.ArrayList;
import java.util.Random;

import enemyprojectile.Spit;
import hostiles.Behavior;
import hostiles.Hostile;
import hostiles.Shooter;
import main.Entity;
import sprite.LightSprite;

public class Bouncer extends Enemy implements Shooter {
	
	public static final int SIZE = 72;
	private static final double DAMAGE = 15;
	private static final int HEALTH = 200;
	private static final float SPEED = 1;
	private static final int POINTS = 25;
	
	private static final LightSprite D = new LightSprite("imgs/hostile/enemy/bouncer.png");
	
	private static int projectilesReleased = 4;
	private int direction = 2;
	private int ticks;

	public Bouncer(float xCoor, float yCoor) {
		super(xCoor, yCoor, SIZE, DAMAGE, HEALTH, SPEED, POINTS);
		
		Attack[] a = {new Attack(90, 25)};
		this.createAttacks(a);
		super.setResistance(0.9);
		super.setSprite(D.copy());
	}
	
	private void movement() {
		Attack a = super.getAttack(0);
		if(a.getTimer() < 10 && a.getTimer() > 5) {
			super.shiftY(-14);
		}
		else if(a.getTimer() < 5 && a.getTimer() > 0) {
			super.shiftY(14);
		} 
		else if(!super.outOfBounds(90)[direction]) {
			super.shift(direction, super.getSpeed());
		}
		else {
			ticks = 119;
		}
		
		ticks++;
		
		if(ticks % 120 == 0) {
			this.direction = randomRange(0, 3);
			ticks = 0;
		}
		
		if(super.getAttack(0).canAttack()) {
			super.getAttack(0).attack(this.shoot());
		}
	}
	
	private ArrayList<Entity> shoot() {
		double angle;
		float ySpeed = 12;
		float xSpeed = 0;
		double baseSpeed = 12;
		ArrayList<Entity> e = new ArrayList<Entity>();
		for(int i = 0; i < projectilesReleased; i++) {
			angle = (Math.PI / (projectilesReleased / 2)) * i;
			baseSpeed = Math.sqrt((ySpeed * ySpeed) + (xSpeed * xSpeed));
			xSpeed = (float) (baseSpeed * (Math.cos(angle)));
			ySpeed = (float) (baseSpeed * (Math.sin(angle)));
			e.add(new Spit(super.getxCoor() + (super.getWidth()/2), super.getyCoor() + (super.getHeight()/2), 
					xSpeed, ySpeed, (super.getAttack(0).getDamage() * super.getDamageMult())));
		}
		return e;
	}
	
	private static int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	@Override
	public Hostile copy() {
		return new Bouncer(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
		this.movement();
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.CUSTOM;
	}

	@Override
	public void createAttacks(Attack[] attacks) {
		super.setAttack(attacks);
	}

}
