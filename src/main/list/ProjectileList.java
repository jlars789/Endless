package main.list;

import java.awt.Graphics2D;

import projectiles.Projectile;

public class ProjectileList extends EntityList {

	public ProjectileList() {
		super();
	}
	
	public Projectile get(int index) {
		return (Projectile)super.get(index);
	}
	
	public void draw(Graphics2D g2d, int layer) {
		for(int i = 0; i < super.size(); i++) {
			if(this.get(i).getLayer() == layer) {
				this.get(i).draw(g2d);
			}
		}
	}

}
