package hostiles.bosses;

import java.awt.Graphics2D;
import java.util.ArrayList;

import characters.Characters;
import enemyprojectile.BigBall;
import hostiles.Behavior;
import hostiles.Hostile;
import hostiles.Shooter;
import hostiles.enemies.Attack;
import main.Entity;
import sprite.LightSprite;

public class WallGrabberBoss extends Boss implements Shooter {
	
	private int wall;
	private static final int SIZE = 152;
	private static final LightSprite S = new LightSprite("imgs/hostile/boss/wallgrabber.png");
	private LightSprite hostileSprite;

	public WallGrabberBoss(float xCoor, float yCoor) {
		super(xCoor, yCoor, SIZE, 50, 1000, 4, 50);
		Attack[] att = {new Attack(40, 50)};
		this.setAttack(att);
		
		super.setResistance(1.8);
		this.hostileSprite = S.copy();
		
		this.wall = 0;
	}
	
	private void movement() {
		
		switch(wall) {
		case 0:
			super.shiftX(super.getSpeed());
			if(super.getCenterPoint()[0] >= Characters.X_BOUNDS) wall = 1;
		break;
		
		case 1: 
			super.shiftY(super.getSpeed());
			if(super.getCenterPoint()[1] >= Characters.Y_BOUNDS) wall = 2;
		break;
		
		case 2:
			super.shiftX(-super.getSpeed());
			if(super.getCenterPoint()[0] <= Characters.X_MIN) wall = 3;
		break;
		
		case 3: 
			super.shiftY(-super.getSpeed());
			if(super.getCenterPoint()[1] <= Characters.Y_MIN) wall = 0;
		break;
		}
		
		if(super.getAttack(0).canAttack()) {
			super.getAttack(0).attack(this.shoot());
		}
		
	}
	
	private ArrayList<Entity> shoot() {
		ArrayList<Entity> e = new ArrayList<Entity>();
		float startX;
		float startY;
		float xSpeed;
		float ySpeed;
		
		float absSpeed = 14;
		
		switch(wall) {
		case 0:
			startX = super.getCenterPoint()[0];
			startY = super.getyCoor() + super.getHeight();
			
			xSpeed = 0;
			ySpeed = -absSpeed;
		break;
		
		case 1:
			startX = super.getxCoor();
			startY = super.getCenterPoint()[1];
			
			xSpeed = absSpeed;
			ySpeed = 0;
		break;
		
		case 2:
			startX = super.getCenterPoint()[0];
			startY = super.getyCoor();
			
			xSpeed = 0;
			ySpeed = absSpeed;
		break;
		
		case 3:
			startX = super.getxCoor() + super.getWidth();
			startY = super.getCenterPoint()[1];
			
			xSpeed = -absSpeed;
			ySpeed = 0;
		break;
		
		default:
			startX = -4;
			startY = 0;
			
			xSpeed = 0;
			ySpeed = 0;
		}
		
		double dmg = super.getAttack(0).getDamage() * super.getDamageMult();
		
		e.add(new BigBall(startX, startY, xSpeed, ySpeed, dmg));
		return e;
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		int angle = wall;
		if(wall > 1) angle = wall - 2;
		else angle = wall + 2;
		g2d.drawImage(this.hostileSprite.cardinalRotate(angle), null, (int)super.getxCoor(), (int)super.getyCoor());
	}

	@Override
	public Hostile copy() {
		return new WallGrabberBoss(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
		this.movement();
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.PHASE;
	}

	@Override
	public void createAttacks(Attack[] attacks) {
		super.setAttack(attacks);
	}

}