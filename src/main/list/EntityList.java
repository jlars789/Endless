package main.list;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Entity;
import projectiles.Projectile;
import projectiles.ProjectileType;

public abstract class EntityList implements Runnable {
	
	private ArrayList<Entity> entityArr;
	private Thread thread;
	
	public EntityList() {
		entityArr = new ArrayList<Entity>();
		thread = new Thread(this);
		thread.start();
	}
	
	public void start() {
		if(!thread.isAlive()) {
			thread.run();
		}
	}
	
	@Override
	public synchronized void run() {
		while(true) {
		for(int i = 0; i < entityArr.size(); i++) {
			entityArr.get(i).update(1);
			if(entityArr.size() > 0 && entityArr.get(i).isExpired()) {
				entityArr.remove(i);
				if(i > 0) i--;
			}
		}
		try {
			Thread.sleep(16, 66);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		//System.out.println("Called");
	}
	
	public void draw(Graphics2D g2d) {
		for(int i = 0 ; i < entityArr.size(); i++) {
			entityArr.get(i).draw(g2d);
		}
	}
	
	public ArrayList<Entity> list(){
		return entityArr;
	}
	
	public boolean isEmpty() {
		return entityArr.isEmpty();
	}
	
	public void add(Entity e) {
		entityArr.add(e);
	}
	
	public void clear() {
		for(int i = 0 ; i < entityArr.size(); i++) {
			entityArr.remove(i);
		}
	}
	
	public Entity get(int index) {
		if(index < entityArr.size()) return entityArr.get(index);
		else return null;
	}
	
	public int size() {
		return entityArr.size();
	}
	
	public void clearWithDis() {
		for(int i = 0; i < entityArr.size(); i++) {
			if(entityArr.get(i) instanceof Projectile) {
				if(!(((Projectile)entityArr.get(i)).getType().compareTo(ProjectileType.PERSISTENT_PROJECTILE) < 0)) {
					entityArr.remove(i);
				}
			} else {
				entityArr.remove(i);
			}
		}
	}
	
	//public abstract ArrayList<Entity> getList();

}
