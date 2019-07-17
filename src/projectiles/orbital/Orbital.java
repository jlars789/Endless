package projectiles.orbital;

import abilities.ProjectileAbility;
import characters.Characters;
import main.Gamepanel;
import projectiles.Projectile;
import projectiles.ProjectileType;

public abstract class Orbital extends Projectile {
	
	private float orbitRadius;
	private float orbitSpeed;
	private int ticks;
	private int initVal;

	public Orbital(int initPos, int size, int lifespan, ProjectileAbility fromAbility, float radius) {
		super(0, 0, 0, size, false, lifespan, fromAbility);
		super.setPierce(1);
		this.orbitRadius = radius;
		this.orbitSpeed = (float)(Math.PI / fromAbility.getProjectileSpeed());
		this.ticks = initPos;
		this.initVal = initPos;
	}

	@Override
	protected void movement() {
		Characters c = Gamepanel.mainChar;
		
	    double radian = orbitSpeed * ticks;
	    super.setxCoor((c.getCenterPoint()[0] + orbitRadius * (float)Math.cos(radian)) - (super.getWidth() / 2)); 
	    super.setyCoor((c.getCenterPoint()[1] + orbitRadius * (float)Math.sin(radian)) - (super.getHeight() / 2));
	    
	    this.ticks++;
	    
	    if((ticks - initVal) > super.getLifespan()) {
	    	this.destroy();
	    }
	}
	
	@Override
	public ProjectileType getType() {
		return ProjectileType.ORBITAL_PROJECTILE;
	}

	@Override
	protected boolean lowLayer() {
		return false;
	}
	
	protected int getTicks() {
		return this.ticks;
	}

}
