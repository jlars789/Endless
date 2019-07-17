package hostiles.enemies;
import java.util.ArrayList;

import characters.Characters;
import enemyprojectile.Spit;
import friendlies.Friendly;
import hostiles.Behavior;
import hostiles.Hostile;
import hostiles.Shooter;
import main.Entity;
import sprite.LightSprite;

public class Spitter extends Enemy implements Shooter {
	
	private static final float SPEED = 2;
	private static final LightSprite D = new LightSprite("imgs/hostile/enemy/spitter.png");

	public Spitter(float xCoor, float yCoor) {
		super(xCoor, yCoor, 48, 15, 100, 2, 15);
		
		Attack[] a = {new Attack(80, 25)};
		this.setAttack(a);
		
		super.setResistance(0.9);
		super.setSprite(D.copy());
	}
	
	private void movement() {
		if(super.getAttack(0).getTimer() == 40) {
			this.move();
		}
		if(super.getAttack(0).canAttack()) {
			super.getAttack(0).attack(shoot());
		}
	}
	
	private ArrayList<Entity> shoot() {
		super.setSpeed(0);
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
		ArrayList<Entity> shot = new ArrayList<Entity>();
		shot.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], xSpeed, ySpeed, (super.getAttack(0).getDamage() * super.getDamageMult())));
		return shot;
	}
	
	private void move() {
		if(super.getSpeed() == 0) {
			super.setSpeed(SPEED);
		}
	}

	@Override
	public Hostile copy() {
		return new Spitter(super.getxCoor(), super.getyCoor());
	}

	@Override
	protected void behavior() {
		this.movement();
	}

	@Override
	protected Behavior getBehavior() {
		return Behavior.CHASING;
	}

	@Override
	public void createAttacks(Attack[] attacks) {
		super.setAttack(attacks);
	}

}
