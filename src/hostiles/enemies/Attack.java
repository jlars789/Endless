package hostiles.enemies;

import java.util.ArrayList;

import main.Entity;
import main.EntityLists;

public class Attack {
	
	private int tempo;
	private double damage;
	private int counter;
	private int count;
	
	//private boolean lock;

	public Attack(int tempo, double damage) {
		this.tempo = tempo;
		this.damage = damage;
		this.count = 1;
	}
	
	public Attack(int tempo, double damage, int count) {
		this(tempo, damage);
		this.count = count;
	}
	
	public void update() {
		if(this.counter > 0) this.counter--;
	}
	
	public boolean canAttack() {
		return (counter == 0);
	}
	
	public void attack(ArrayList<Entity> shots) {
		this.counter = tempo;
		EntityLists.addNew(shots);
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public int getTimer() {
		return this.counter;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void adjustDamage(double damage) {
		this.damage = damage;
	}

}
