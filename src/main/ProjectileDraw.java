package main;

import java.awt.Graphics2D;

import projectiles.Projectile;

public class ProjectileDraw {
	
	public static void draw(Graphics2D g2d, int layer) {
		for(int i = 0; i < EntityLists.getList(EntityLists.PROJ_IND).size(); i++) {
			if(((Projectile) EntityLists.getList(EntityLists.PROJ_IND).get(i)).getLayer() == layer) {
				EntityLists.getList(EntityLists.PROJ_IND).get(i).draw(g2d);
			}
		}
	}

}
