package hostiles.bosses.minibosses;
import java.util.ArrayList;

import characters.Characters;
import enemyprojectile.Spit;
import friendlies.Friendly;
import hostiles.Behavior;
import hostiles.Hostile;
import hostiles.Shooter;
import hostiles.enemies.Attack;
import main.Entity;
import sprite.LightSprite;

public class Bruiser extends Miniboss implements Shooter {
	
	private static final LightSprite S = new LightSprite("imgs/hostile/miniboss/bruiser.png");

	public Bruiser(float xCoor, float yCoor, boolean challenger) {
		super(xCoor, yCoor, 92, challenger);
		
		int challengeModifier = 1;
		if(challenger) challengeModifier = 2;
		
		Attack[] attacks = {new Attack(40, (25 * challengeModifier))};  
		
		this.createAttacks(attacks);
		
		super.setStats(750 * challengeModifier, 25 * challengeModifier, 2.4f, 50);
		super.setResistance(1.5);
		super.setSprite(S.copy());
		
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
		
		float minSpreadX = xSpeed*2;
		float maxSpreadX = xSpeed*6;
		
		float minSpreadY = ySpeed*2;
		float maxSpreadY = ySpeed*6;
		
		double dmg = super.getAttack(0).getDamage() * super.getDamageMult();
		
		e.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], (float)(xSpeed + minSpreadX), ySpeed, dmg));
		e.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], (float)(xSpeed), (float)(ySpeed + minSpreadY), dmg));
		e.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], (float)(xSpeed + maxSpreadX), ySpeed, dmg));
		e.add(new Spit(super.getCenterPoint()[0], super.getCenterPoint()[1], (float)(xSpeed), (float)(ySpeed + maxSpreadY), dmg));
		
		System.out.println("shot");
		
		return e;
	}

	@Override
	public Hostile copy() {
		return new Bruiser(super.getxCoor(), super.getyCoor(), super.isChallenger());
	}

	@Override
	protected void behavior() {
		if(super.getAttack(0).canAttack()) {
			this.mainShoot();
		}
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
