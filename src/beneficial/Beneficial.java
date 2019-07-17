package beneficial;

import characters.Characters;
import main.Gamepanel;

public abstract class Beneficial {
	
	private int duration;
	private boolean active;
	
	protected static Characters mainChar = Gamepanel.mainChar;

	public Beneficial(int duration) {
		this.duration = duration;
		this.active = false;
	}
	
	public void update() {
		this.effect();
		if(active && duration > 0) {
			duration--;
		} else {
			this.end();
		}
	}
	
	public void start() {
		this.active = true;
		this.init();
	}
	
	protected abstract void effect();
	
	protected abstract void init();
	
	public abstract Beneficial copy();
	
	public void end() {
		this.active = false;
		this.finish();
	}
	
	protected abstract void finish();
	
	public boolean isActive() {
		return this.active;
	}
	
	public boolean onChar() {
		return (this.active && this.duration > 0);
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	

}
