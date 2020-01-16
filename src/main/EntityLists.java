package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import enemyprojectile.EnemyProjectile;
import friendlies.Friendly;
import hostiles.Hostile;
import interactable.Interactable;
import main.list.EnemyProjectileList;
import main.list.EntityList;
import main.list.FriendlyList;
import main.list.HostileList;
import main.list.InteractableList;
import main.list.ProjectileList;
import projectiles.Projectile;

public class EntityLists {
	
	//public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public static final int PROJ_IND = 0;
	public static final int EPROJ_IND = 1;
	public static final int FRND_IND = 2;
	public static final int HOST_IND = 3;
	public static final int INTR_IND = 4;
	
	public static EntityList[] lists = {new ProjectileList(), new EnemyProjectileList(), new FriendlyList(), new HostileList(), new InteractableList()};
	
	public static synchronized final void update(double delta) {
		Projectile.setDelta(delta);
	}
	
	public static synchronized void removeEntities(boolean withDiscrimination) {
		if(withDiscrimination) {
			for(int i = 0; i < lists.length; i++) {
				lists[i].clearWithDis();
			}
		} else {
			for(int i = 0; i < lists.length; i++) {
				lists[i].clear();
			}
		}
	}
	
	public static void drawInteracts(Graphics2D g2d) {
		lists[EntityLists.INTR_IND].draw(g2d);
	}
	
	public static void drawEntities(Graphics2D g2d) {
		lists[EntityLists.FRND_IND].draw(g2d);
		lists[EntityLists.EPROJ_IND].draw(g2d);
		lists[EntityLists.HOST_IND].draw(g2d);
	}
	
	public static void addNew(ArrayList<Entity> entity) {
		for(int i = 0; i < entity.size(); i++) {
			if(entity.get(i) instanceof Projectile) lists[EntityLists.PROJ_IND].add(entity.get(i));
			else if(entity.get(i) instanceof EnemyProjectile) lists[EntityLists.EPROJ_IND].add(entity.get(i));
			else if(entity.get(i) instanceof Hostile) lists[EntityLists.HOST_IND].add(entity.get(i));
			else if(entity.get(i) instanceof Friendly) lists[EntityLists.FRND_IND].add(entity.get(i));
			else if(entity.get(i) instanceof Interactable) lists[EntityLists.INTR_IND].add(entity.get(i));
		}
	}
	
	public static void addNew(Entity e) {
		if(e instanceof Projectile) lists[EntityLists.PROJ_IND].add(e);
		else if(e instanceof EnemyProjectile) lists[EntityLists.EPROJ_IND].add(e);
		else if(e instanceof Hostile) lists[EntityLists.HOST_IND].add(e);
		else if(e instanceof Friendly) lists[EntityLists.FRND_IND].add(e);
		else if(e instanceof Interactable) lists[EntityLists.INTR_IND].add(e);
	}
	
	public static void addNew(Entity[] e) {
		for(int i = 0; i < e.length; i++) {
			if(e[i] instanceof Projectile) lists[EntityLists.PROJ_IND].add(e[i]);
			else if(e[i] instanceof EnemyProjectile) lists[EntityLists.EPROJ_IND].add(e[i]);
			else if(e[i] instanceof Hostile) lists[EntityLists.HOST_IND].add(e[i]);
			else if(e[i] instanceof Friendly) lists[EntityLists.FRND_IND].add(e[i]);
			else if(e[i] instanceof Interactable) lists[EntityLists.INTR_IND].add(e[i]);
		}
	}
	
	public static EntityList getList(int index) {
		return lists[index];
	}
	
	/*
	public static ArrayList<Friendly> getFriendList() {
		return lists[EntityLists.FRND_IND];
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
	*/
	
	public static void removeHostiles() {
		/*
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Hostile) {
				entities.remove(i);
				//if(i > 0) i--;
			}
		}
		*/
		lists[EntityLists.HOST_IND].clear();
	}
	
	public static boolean isListEmpty(int listIndex) {
		return lists[listIndex].isEmpty();
	}
}
