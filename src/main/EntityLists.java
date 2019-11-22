package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import enemyprojectile.EnemyProjectile;
import friendlies.Friendly;
import hostiles.Hostile;
import interactable.Interactable;
import projectiles.Projectile;
import projectiles.ProjectileType;

public class EntityLists {
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public static synchronized final void update(double delta) {
		Projectile.setDelta(delta);
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update(delta);
			if(entities.size() > 0 && entities.get(i).isExpired()) {
				entities.remove(i);
				if(i > 0) i--;
			}
		}
	}
	
	public static synchronized void removeEntities(boolean withDiscrimination) {
		if(withDiscrimination) {
			for(int i = 0; i < entities.size(); i++) {
				if(entities.get(i) instanceof Projectile) {
					if(!(((Projectile)entities.get(i)).getType().compareTo(ProjectileType.PERSISTENT_PROJECTILE) < 0)) {
						entities.remove(i);
					}
				} else {
					entities.remove(i);
				}
			}
		}
	}
	
	public static void drawInteracts(Graphics2D g2d) {
		for(int i = 0; i < getInteractableList().size(); i++) {
			getInteractableList().get(i).draw(g2d);
		}
	}
	
	public static void drawEntities(Graphics2D g2d) {
		for(int i = 0; i < getFriendList().size(); i++) {
			getFriendList().get(i).draw(g2d);
		}
		for(int i = 0; i < getEnemyProjectileList().size(); i++) {
			getEnemyProjectileList().get(i).draw(g2d);
		}
		for(int i = 0; i < getHostileList().size(); i++) {
			getHostileList().get(i).draw(g2d);
		}
	}
	
	public static void addNew(ArrayList<Entity> entity) {
		for(int i = 0; i < entity.size(); i++) {
			entities.add(entity.get(i));
		}
	}
	
	public static void addNew(Entity e) {
		entities.add(e);
	}
	
	public static void addNew(Entity[] e) {
		for(int i = 0; i < e.length; i++) {
			entities.add(e[i]);
		}
	}
	
	public static ArrayList<Friendly> getFriendList() {
		ArrayList<Friendly> list = new ArrayList<Friendly>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Friendly) list.add((Friendly)entities.get(i)); 
		}
		return list;
	}
	
	public static ArrayList<Hostile> getHostileList() {
		ArrayList<Hostile> list = new ArrayList<Hostile>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Hostile) list.add((Hostile)entities.get(i)); 
		}
		return list;
	}
	
	public static ArrayList<Projectile> getProjectileList() {
		ArrayList<Projectile> list = new ArrayList<Projectile>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Projectile) list.add((Projectile)entities.get(i)); 
		}
		return list;
	}
	
	public static ArrayList<Interactable> getInteractableList(){
		ArrayList<Interactable> list = new ArrayList<Interactable>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Interactable) list.add((Interactable)entities.get(i));
		}
		return list;
	}
	
	public static ArrayList<EnemyProjectile> getEnemyProjectileList(){
		ArrayList<EnemyProjectile> list = new ArrayList<EnemyProjectile>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof EnemyProjectile) list.add((EnemyProjectile)entities.get(i));
		}
		return list;
	}
	
	public static void removeHostiles() {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Hostile) {
				entities.remove(i);
				//if(i > 0) i--;
			}
		}
	}
}
