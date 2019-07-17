package projectiles;

import java.awt.Graphics2D;

import hostiles.Hostile;
import sprite.LightSprite;

public class Explosion extends Projectile {
	
	private static final int LIFESPAN = 3;
	private static final LightSprite PROJECTILE_SPRITE = new LightSprite("imgs/projectiles/explosion.png");
	
	private int[] offsetCoor = new int[2];
	private float centerCoor[] = new float[2];
	private LightSprite projectileSprite;
	private double blastDamage;

	public Explosion(float xCoor, float yCoor, int size, double damage, float[] centerPoint) {
		super(xCoor, yCoor, 0, size, true, LIFESPAN);
		this.instantiate(xCoor, yCoor, size, centerPoint);
		this.blastDamage = damage/2;
	}
	
	private void instantiate(float xCoor, float yCoor, int size, float[] centerPoints) {
		this.projectileSprite = PROJECTILE_SPRITE.copy();
		this.offsetCoor[0] = (int)(centerPoints[0] - (size/2));
		this.offsetCoor[1] = (int)(centerPoints[1] - (size/2));
		
		this.centerCoor[0] = xCoor + (size/2);
		this.centerCoor[1] = yCoor + (size/2);
		
		this.projectileSprite.resizeImage(size, size);
		super.setxCoor(offsetCoor[0]);
		super.setyCoor(offsetCoor[1]);
	}

	@Override
	public void action() {
	}

	@Override
	public void movement() {
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.projectileSprite.getImage(), null, offsetCoor[0], offsetCoor[1]);
	}

	@Override
	public void effect(Hostile enemy) {
		double damage = ((super.getWidth()/calculateDistance(enemy)) * this.blastDamage)/LIFESPAN;
		if((damage * LIFESPAN) > this.blastDamage) damage = (blastDamage/LIFESPAN);
		enemy.damage(damage);
	}
	
	private double calculateDistance(Hostile enemy) {
		float[] dist = new float[2];
		dist[0] = Math.abs(enemy.getCenterPoint()[0] - centerCoor[0]);
		dist[1] = Math.abs(enemy.getCenterPoint()[1] - centerCoor[1]);
		
		return Math.sqrt((dist[0] * dist[0]) + (dist[1] * dist[1]));
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.BEAM_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return true;
	}

}
