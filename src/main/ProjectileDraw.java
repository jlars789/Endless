package main;

import java.awt.Graphics2D;

public class ProjectileDraw {
	
	public static void draw(Graphics2D g2d, int layer) {
		for(int i = 0; i < EntityLists.getProjectileList().size(); i++) {
			if(EntityLists.getProjectileList().get(i).getLayer() == layer) {
				EntityLists.getProjectileList().get(i).draw(g2d);
			}
		}
	}

}
