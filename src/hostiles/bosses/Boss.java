package hostiles.bosses;
import java.awt.Color;
import java.awt.Graphics2D;

import hostiles.Hostile;

public abstract class Boss extends Hostile{
	
	public Boss(float xCoor, float yCoor, int size, double damage, int health, float speed, int pointVal) {
		super(xCoor, yCoor, size, damage, health, speed, pointVal);
	}
	
	public Boss(float xCoor, float yCoor, int size) {
		super(xCoor, yCoor, size);
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(120, 20, 120, 20);
		g2d.setColor(Color.RED);
		g2d.fillRect(120, 20, (int)(super.getHealth().getValues()[0] * 120/ super.getHealth().getValues()[1]), 20);
		
		super.draw(g2d);
		
	}

}
