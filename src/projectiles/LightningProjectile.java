package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import hostiles.Hostile;
import main.Gamepanel;
import sprite.LightSprite;
import weapons.StormSurge;

public class LightningProjectile extends Projectile {
	
	private static final int LIFESPAN = 4;
	private static int width = 50;
	private static double damage = 10;
	private static int range = 125;
	
	private LightSprite proj;
	
	private ArrayList<Hostile> enemiesHit;

	public LightningProjectile(float xCoor, float yCoor, int direction, StormSurge fromWeapon) {
		super(xCoor, yCoor, direction, width, true, LIFESPAN, fromWeapon);
		this.enemiesHit = new ArrayList<Hostile>();
		this.proj = fromWeapon.getProjectileSprite();
	}

	@Override
	protected void action() {
		for(int i = 0; i < enemiesHit.size(); i++) {
			ArrayList<Hostile> hlist = enemiesHit.get(i).hostilesInProximity(range);
			for(int j = 0; j < hlist.size(); j++) {
				this.addToList(hlist.get(j));
			}
			enemiesHit.get(i).damage(damage);
		}
	}

	@Override
	protected void movement() {
		if(super.getDirection() == 0) {
			super.setBounds((int)Gamepanel.mainChar.getxCoor(), (int)(Gamepanel.mainChar.getyCoor() - range), super.getWidth(), (int)range);
		}
		else if(super.getDirection() == 1) {
			super.setBounds((int)Gamepanel.mainChar.getxCoor() + Gamepanel.mainChar.getWidth(), (int)Gamepanel.mainChar.getCenterPoint()[1] - 15, (int)range, super.getHeight());
		}
		else if(super.getDirection() == 2) {
			super.setBounds((int)Gamepanel.mainChar.getCenterPoint()[0] - 10, (int)Gamepanel.mainChar.getyCoor() + Gamepanel.mainChar.getHeight(), super.getWidth(), (int)range);
		}
		else if(super.getDirection() == 3) {
			super.setBounds((int)(Gamepanel.mainChar.getxCoor() - range), (int)(int)Gamepanel.mainChar.getCenterPoint()[1] - 15, (int)range, super.getHeight());
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		
		drawShock(g2d);
		
		for(int i = 0; i < enemiesHit.size(); i++) {
			ArrayList<Hostile> hlist = enemiesHit.get(i).hostilesInProximity(range);
			for(int j = 0; j < hlist.size(); j++) {
				g2d.drawLine((int)enemiesHit.get(i).getCenterPoint()[0], (int)enemiesHit.get(i).getCenterPoint()[1], 
						(int)hlist.get(j).getCenterPoint()[0], (int)hlist.get(j).getCenterPoint()[1]);
			}
		}

	}
	
	private void drawShock(Graphics2D g2d) {
		int dir = Gamepanel.mainChar.getDirection();
		g2d.drawImage(proj.currentRotated(90 * dir), null, (int)this.getxCoor(), (int)this.getyCoor());
	}
	
	/*
	private void drawShock(Graphics2D g2d) {
		int dir = Gamepanel.mainChar.getDirection();
		switch(dir) {
		case 0:
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], (int)super.getxCoor(), (int)super.getyCoor());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], (int)super.getxCoor() + width/2, (int)super.getyCoor());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], (int)super.getxCoor() + width, (int)super.getyCoor());
		break;
		case 1:
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor() + super.getWidth(), (int)super.getyCoor());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor() + super.getWidth(), (int)super.getyCoor() + width/2);
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor() + super.getWidth(), (int)super.getyCoor() + width);
		break;
		
		case 2:
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor(), (int)super.getyCoor() + super.getHeight());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor() + width/2, (int)super.getyCoor() + super.getHeight());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor() + width, (int)super.getyCoor() + super.getHeight());
		break;
		
		case 3:
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor(), (int)super.getyCoor());
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor(), (int)super.getyCoor() + width/2);
			g2d.drawLine((int)Gamepanel.mainChar.getCenterPoint()[0], (int)Gamepanel.mainChar.getCenterPoint()[1], 
					(int)super.getxCoor(), (int)super.getyCoor() + width);
		break;
		}
	}
		*/

	@Override
	public void effect(Hostile enemy) {
		enemiesHit.add(enemy);
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.BEAM_PROJECTILE;
	}
	
	private void addToList(Hostile h) {
		if(!enemiesHit.contains(h)) {
			enemiesHit.add(h);
		}
	}

	@Override
	public boolean lowLayer() {
		return false;
	}
	
	@Override
	public byte getLayer() {
		return 2;
	}

}
