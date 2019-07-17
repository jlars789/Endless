package enemyprojectile;

import sprite.Sprite;

import java.awt.Graphics2D;

public class BigBall extends EnemyProjectile {
	
	private static final int SIZE = 24;
	private static final int LIFESPAN = 160;
	private static final double SCALAR = 0.4;
	
	private Sprite sprite;

	public BigBall(float xCoor, float yCoor, float xSpeed, float ySpeed, double damage) {
		super(xCoor, yCoor, SIZE, xSpeed, ySpeed, damage, SCALAR);
		
		super.setLifespan(LIFESPAN);
		this.sprite = EnemyProjectile.getSprite(1);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.sprite.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}

	@Override
	protected void action() {
	}

}
