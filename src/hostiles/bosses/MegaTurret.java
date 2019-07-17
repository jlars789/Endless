package hostiles.bosses;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import characters.Characters;
import enemyprojectile.BigBall;
import enemyprojectile.Spit;
import friendlies.Friendly;
import hostiles.Behavior;
import hostiles.Hostile;
import hostiles.Shooter;
import hostiles.enemies.Attack;
import main.Entity;
import sprite.LightSprite;

public class MegaTurret extends Boss implements Shooter {
	
	public static final int SIZE = 120;
	private static final int HEAD_HEIGHT = 60;
	private static final int HEAD_WIDTH = 60;
	private static final LightSprite HEAD = new LightSprite("imgs/hostile/boss/megaturret-head.png");
	private static final LightSprite BODY = new LightSprite("imgs/hostile/boss/megaturretbase.png");

	public MegaTurret(float xCoor, float yCoor) {
		super(xCoor, yCoor, SIZE, 25, 1000, 0, 50);
		
		Attack[] att = {new Attack(35, 25), new Attack(140, 25, 8)};
		this.createAttacks(att);
		
		super.setResistance(1.5);
		super.setSprite(BODY.copy());
	}
	
	private void fire() {
		if(super.getAttack(0).canAttack()) {
			super.getAttack(0).attack(mainShoot());
		}
		if(super.getAttack(1).canAttack()) {
			super.getAttack(0).attack(secondaryShoot());
		}
	}
	
	private ArrayList<Entity> mainShoot() {
		ArrayList<Entity> e = new ArrayList<Entity>();
		float ySpeed = 0;
		float xSpeed = 0;
		Characters c = super.getCharRef();
		if(super.isDistracted()) {
			Friendly f = super.getAllyRef();
			ySpeed = super.getCenterPoint()[1] - (f.getCenterPoint()[1]);
			xSpeed = super.getCenterPoint()[0] - (f.getCenterPoint()[0]);
		} else if(!c.getStats().isInvisible()) {
			ySpeed = super.getCenterPoint()[1] - (c.getCenterPoint()[1]);
			xSpeed = super.getCenterPoint()[0] - (c.getCenterPoint()[0]);
		}
		
		/*
		int randChoice = randomRange(0, 2);
	
		int diff = 0;
		
		switch(randChoice) {
		case 0: diff = 0;
		break;
		case 1: diff = 40;
		break;
		case 2: diff = -40;
		break;
		default: break;
		}
		*/
		
		double dmg = super.getAttack(0).getDamage() * super.getDamageMult();
		
		e.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], xSpeed, ySpeed, dmg));
		/*
		e.add(new Spit(super.getCenterPoint()[0] - (xSpeed / 10), super.getCenterPoint()[1] - (ySpeed / 10), xSpeed - diff, ySpeed, dmg));
		e.add(new Spit(super.getCenterPoint()[0] - (xSpeed / 10), super.getCenterPoint()[1] - (ySpeed / 10), xSpeed, ySpeed - diff, dmg));
		*/
		
		return e;
	}
	
	public ArrayList<Entity> secondaryShoot() {
		ArrayList<Entity> e = new ArrayList<Entity>();
		double angle;
		float ySpeed = 12;
		float xSpeed = 0;
		double BaseSpeed = 12;
		
		double dmg = super.getAttack(1).getDamage() * super.getDamageMult();
		
		for(int i = 0; i < super.getAttack(1).getCount(); i++) {
			angle = (Math.PI/(super.getAttack(1).getCount() / 2)) * (i);
			BaseSpeed = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));
			xSpeed = (float)(BaseSpeed * (Math.cos(angle)));
			ySpeed = (float)(BaseSpeed * (Math.sin(angle)));
			e.add(new BigBall(super.getCenterPoint()[0], super.getCenterPoint()[1], xSpeed, ySpeed, dmg));
		}
		return e;
		
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		int x = (int)(super.getCenterPoint()[0] - HEAD_WIDTH/2);
		int y = (int)(super.getCenterPoint()[1] - HEAD_HEIGHT/2);
		double sin = Math.sin(Math.toRadians(super.getAngleFacing()));
		double cos = Math.cos(Math.toRadians(super.getAngleFacing()));
		
		int x1 = (int)super.getCenterPoint()[0];
		int y1 = (int)super.getCenterPoint()[1];
		
		int x2 = x1;
		int y2 = y1 + 12;
		
		int x3 = (int)(x1 + ((x2 - x1)* cos) - ((y2 - y1) * sin));
		int y3 = (int)(y1 - ((x2 - x1) * sin) + ((y2 - y1) * cos));
		
		int difX = x + (x1 - x3);
		int difY = y + (y1 - y3);
		
		g2d.drawImage(HEAD.currentRotated(super.getAngleFacing()), null, difX, difY);
		//TODO Make the rotation axis more centered
	}
	
	public static int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	@Override
	public Hostile copy() {
		return new MegaTurret(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
		this.fire();	
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
