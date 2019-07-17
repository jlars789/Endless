package hostiles.bosses.minibosses;
import hostiles.bosses.Boss;

public abstract class Miniboss extends Boss {
	
	
	private boolean challenger;
	
	/**
	 * Boss type that appears outside of boss rooms
	 * @param xCoor X-Coordinate of the miniboss
	 * @param yCoor Y-Coordinate of the miniboss
	 * @param challenger Determines if the challenger modifiers apply to the miniboss
	 */
	
	public Miniboss(float xCoor, float yCoor, int size, boolean challenger) {
		super(xCoor, yCoor, size);
		
		this.challenger = challenger;
		
	}
	
	public void makeChallenger() {
		super.setStats(super.getHealth().getValues()[1] * 2, super.getDamage() * 2, super.getSpeed(), 25);
		super.getAttack(0).adjustDamage(super.getAttack(0).getDamage() * 2);
	}
	
	protected boolean isChallenger() {
		return this.challenger;
	}
	
	public void setChallenger() {
		this.challenger = true;
	}

}
