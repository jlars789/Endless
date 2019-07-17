package hostiles;

import java.util.ArrayList;
import java.util.Random;

import characters.Characters;
import friendlies.Friendly;
import main.Gamepanel;

public class Seek extends Force {
	
	private Path path;
	private Path potentialPath;
	
	//private int indexInPack;
	
	private boolean avoided;
	private boolean collided;
	
	private float[] desiredLocation;

	public Seek(Hostile h, double weight) {
		super(h, weight);
		this.desiredLocation = new float[2];
		this.path = new Path(h, desiredLocation[0], desiredLocation[1]);
		this.potentialPath = new Path(h, Gamepanel.mainChar.getxCoor(), Gamepanel.mainChar.getyCoor());
		this.chase();
	}
	
	@Override
	public void applyForce() {
		
		if(!super.getHostile().isDistracted()) {
			Characters c = super.getHostile().getCharRef();
			if(!c.getStats().isInvisible()) {
				
				float x = super.getHostile().getCenterPoint()[0] - this.desiredLocation[0];
				float y = super.getHostile().getCenterPoint()[1] - this.desiredLocation[1];
				double t = 0;
				
				if(x != 0) {
					t = Math.atan(y/x);
				}
				
				else if(x < 0.001 && x > -0.01) {
					t = Math.PI/2;
				}
				
				float cos = (float)Math.abs(Math.cos(t));
				float sin = (float)Math.abs(Math.sin(t));
				
				if(super.getHostile().getCenterPoint()[0] >= this.desiredLocation[0]) super.setDesiredVel(0, -super.getHostile().getSpeed() * cos);
				else if(super.getHostile().getCenterPoint()[0] < this.desiredLocation[0]) super.setDesiredVel(0, super.getHostile().getSpeed() * cos);
				if(super.getHostile().getCenterPoint()[1] >= this.desiredLocation[1]) super.setDesiredVel(1, -super.getHostile().getSpeed() * sin);
				else if(super.getHostile().getCenterPoint()[1] < this.desiredLocation[1]) super.setDesiredVel(1, super.getHostile().getSpeed() * sin);
				
			}
		} else if (super.getHostile().getAllyRef() != null){
			
			Friendly f = super.getHostile().getAllyRef();
			if(super.getHostile().getCenterPoint()[0] >= f.getCenterPoint()[0]) super.setDesiredVel(0, -super.getHostile().getSpeed());
			else if(super.getHostile().getCenterPoint()[0] < f.getCenterPoint()[0]) super.setDesiredVel(0, super.getHostile().getSpeed());
			if(super.getHostile().getCenterPoint()[1] >= f.getCenterPoint()[1]) super.setDesiredVel(1, -super.getHostile().getSpeed());
			else if(super.getHostile().getCenterPoint()[1] < f.getCenterPoint()[1]) super.setDesiredVel(1, super.getHostile().getSpeed());
		}
		
		this.update();
	}
	
	public void chase() {
		Characters c = Gamepanel.mainChar;
		this.desiredLocation[0] = c.getCenterPoint()[0];
		this.desiredLocation[1] = c.getCenterPoint()[1];
		this.avoided = false;
		this.collided = false;
	}
	
	public void separate() {
		
	}
	
	public void avoid() {
		if(!avoided) {
			this.setRandomLocation();
			this.avoided = true;
			this.collided = false;
		}
	}
	
	public void collide(Hostile h1, Hostile h2) {
		if(h2.getBehavior() != Behavior.PHASE) {
			this.collided = true;
			int xsign = 1;
			int ysign = 1; //used for changing direction depending on impact
			int side = h1.getFullHitbox().intersectsSide(h2.getFullHitbox());
			if(Gamepanel.mainChar.getCenterPoint()[0] < h1.getCenterPoint()[0]) xsign = -1;
			if(Gamepanel.mainChar.getCenterPoint()[1] < h1.getCenterPoint()[1]) ysign = -1;
			h1.knockback(inverse(side), 2);
			if(side == 0) {
				this.desiredLocation[0] = h1.getxCoor() + (randomRange(15, 30) * xsign);
				this.desiredLocation[1] = h1.getyCoor();
			}
			else if(side == 1) {
				this.desiredLocation[0] = h1.getxCoor();
				this.desiredLocation[1] = h1.getyCoor() + (randomRange(15, 30) * ysign);
			}
			else if(side == 2) {
				this.desiredLocation[0] = h1.getxCoor() + (randomRange(15, 30) * xsign);
				this.desiredLocation[1] = h1.getyCoor();
			}
			else if(side == 3) {
				this.desiredLocation[0] = h1.getxCoor();
				this.desiredLocation[1] = h1.getyCoor() + (randomRange(15, 30) * ysign);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private boolean evalRange(float point, float location) {
		boolean inRange = false;
		return inRange;
	}
	
	private int randomRange(int min, int max) { //creates a random integer range
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public boolean pathIntersects(Hostile h) {
		return path.checkIntersect(h);
	}
	
	public boolean freePath(ArrayList<Hostile> h) {
		boolean path = true;
		
		for(int i = 0; i < h.size(); i++) {
			if(this.potentialPath.checkIntersect(h.get(i)) && h.get(i).getBehavior() != Behavior.PHASE) path = false;
		}
		
		return path;
	}
	
	private int inverse(int side) {
		if(side > 1) {
			return side - 2;
		} else {
			return side + 2;
		}
	}
	
	private boolean withinRange(float p1, float p2, double range) {
		return (p1 <= (p2 + range) && p1 >= (p2 - range));
	}

	@Override
	protected void update() {
		
		if(this.desiredLocation[0] < Characters.X_MIN) this.desiredLocation[0] = Characters.X_MIN;
		else if(this.desiredLocation[0] > Characters.X_BOUNDS) this.desiredLocation[0] = Characters.X_BOUNDS;
		
		if(this.desiredLocation[1] < Characters.Y_MIN) this.desiredLocation[1] = Characters.Y_MIN;
		else if(this.desiredLocation[1] > Characters.Y_BOUNDS) this.desiredLocation[1] = Characters.Y_BOUNDS;
		
		if(withinRange(super.getHostile().getCenterPoint()[0], this.desiredLocation[0], 10) && withinRange(super.getHostile().getCenterPoint()[1], this.desiredLocation[1], 10)) {
			this.setRandomLocation();
		}
		
		//this.collided = false;
		
		path.update(desiredLocation[0], desiredLocation[1]);
	}
	
	public void giveLocationInPack(int location) {
		
	}
	
	private void setRandomLocation() {
		Characters c = Gamepanel.mainChar;
		this.desiredLocation[0] = c.getCenterPoint()[0] + randomRange(-200, 200);
		this.desiredLocation[1] = c.getCenterPoint()[1] + randomRange(-200, 200);
	}
	
	public float[] desiredLocation() {
		return this.desiredLocation;
	}
	
	public double[] terminus() {
		return path.endpoints();
	}
	
	protected boolean isColliding() {
		return this.collided;
	}

}
