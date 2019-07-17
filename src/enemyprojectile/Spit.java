package enemyprojectile;
import java.awt.Graphics2D;
import sprite.Sprite;

public class Spit extends EnemyProjectile {
	
	private static final int SIZE = 16;
	private static final int LIFESPAN = 160;
	private static final double SCALAR = 0.25;
	
	private Sprite sprite; 

	public Spit(float xCoor, float yCoor, float xSpeed, float ySpeed, double damage) {
		super(xCoor, yCoor, SIZE, xSpeed, ySpeed, damage, SCALAR);
		
		super.setLifespan(LIFESPAN);
		this.sprite = EnemyProjectile.getSprite(0);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(sprite.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}

	@Override
	protected void action() {
	}

}
