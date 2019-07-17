package hostiles;

public abstract class Force {
	
	private Hostile toHostile;
	private double weight;
	
	private float[] desiredVel;

	public Force(Hostile h, double weight) {
		this.toHostile = h;
		this.weight = weight;
		this.desiredVel = new float[2];
	}
	
	protected abstract void applyForce();
	
	protected abstract void update();
	
	public float[] getVel() {
		return this.desiredVel;
	}
	
	public void setDesiredVel(int index, float velocity) {
		this.desiredVel[index] = (float)(velocity * this.weight);
	}
	
	protected Hostile getHostile() {
		return this.toHostile;
	}
	
	protected double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
