package main;

import java.io.Serializable;

public class HealthBar implements Serializable {
	
	private static final long serialVersionUID = 3820428084041585164L;
	
	private double maxHealth;
	private double currentHealth;

	public HealthBar(double maxHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}
	
	public HealthBar() {
		
	}
	
	public void changeMax(double max) {
		this.maxHealth = max;
		if(currentHealth > maxHealth) currentHealth = max;
	}
	
	public void incMax(double inc) {
		this.changeMax(this.maxHealth + inc);
	}
	
	public void damage(double damage) {
		this.currentHealth -= damage;
	}
	
	public void heal(double heal) {
		if((currentHealth + heal) < this.maxHealth) this.damage(-heal);
		else this.currentHealth = this.maxHealth;
	}
	
	public void setHealth(double health) {
		this.currentHealth = health;
	}
	
	public boolean dead() {
		return (currentHealth <= 0);
	}
	
	/**
	 * Returns array of two health values
	 * @return 0: Current Health, 1: Max Health
	 */
	public double[] getValues() {
		double[] arr = {currentHealth, maxHealth};
		return arr;
	}

}
