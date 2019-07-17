package main;

import java.awt.Graphics2D;

public interface Entity {
	
	public abstract void update(double delta);
	
	public abstract void draw(Graphics2D g2d);
	
	public abstract boolean isExpired();

}
