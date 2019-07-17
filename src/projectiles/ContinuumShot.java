package projectiles;

import abilities.Cowboy.Continuum;
import characters.Characters;

public class ContinuumShot extends SniperBullet {
	
	private int iterations;
	private Continuum ability;
	private boolean released;

	public ContinuumShot(float xCoor, float yCoor, int direction, Continuum fromAbility, int iterations) {
		super(xCoor, yCoor, direction, fromAbility);
		this.iterations = iterations;
		this.ability = fromAbility;
	}
	
	public void movement() {
		super.movement();
		if(this.hasImpact() && !released) {
			this.releaseProjectile();
		}
	}
	
	private void releaseProjectile() {
		if(iterations > 0) {
			if(super.getxCoor() > Characters.X_BOUNDS) {
				super.recursion(new ContinuumShot(Characters.X_MIN + super.getWidth(), this.getyCoor(), this.getDirection(), this.ability, this.iterations - 1));
				this.released = true;
			}
			else if(super.getyCoor() > Characters.Y_BOUNDS) {
				super.recursion(new ContinuumShot(this.getxCoor(), Characters.Y_MIN + this.getHeight(), this.getDirection(), this.ability, this.iterations - 1));
				this.released = true;
			}
			else if(super.getxCoor() < Characters.X_MIN) {
				super.recursion(new ContinuumShot(Characters.X_BOUNDS - super.getWidth(), this.getyCoor(), this.getDirection(), this.ability, this.iterations - 1));
				this.released = true;
			}
			else if(super.getyCoor() < Characters.Y_MIN) {
				super.recursion(new ContinuumShot(this.getxCoor(), Characters.Y_BOUNDS - super.getHeight(), this.getDirection(), this.ability, this.iterations - 1));
				this.released = true;
			}
		}
	}

}
