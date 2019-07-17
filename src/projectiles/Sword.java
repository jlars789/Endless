package projectiles;

import weapons.Shadowblade;
import characters.Ninja;
import hostiles.Hostile;
import sprite.LightSprite;

import java.awt.Graphics2D;

public class Sword extends Projectile {	
	
	private static final int LIFESPAN = 12;
	
	public static int range = 90;
	private static int width = 10;
	private int swipeDistance;
	
	private boolean rotated;
	
	private Ninja charRef;
	private Shadowblade weapon;
	
	private LightSprite sprite;

	public Sword(float xCoor, float yCoor, int direction, Shadowblade fromWeapon, Ninja mainChar) {
		super(xCoor, yCoor, direction, width, true, LIFESPAN, fromWeapon);
		this.weapon = fromWeapon;
		this.charRef = mainChar;
		this.sprite = fromWeapon.getProjectileSprite().copy();
		this.instantiate();
	}

	public void draw(Graphics2D g) {
		g.drawImage(this.sprite.getImage(), (int)super.getxCoor(), (int)super.getyCoor(), null);
	}
	
	public void action() {
		if(this.charRef != null) {
			this.charRef.getStats().getHealth().heal(5);
		}
	}
	
	public void effect(Hostile enemy) {
		
	}
	
	public void movement() {
		if(charRef != null) {
			if(super.getDirection() == 0) {
				if(!rotated) {
					this.sprite.rotateImage(270);
				}
				super.setBounds((int)this.charRef.getxCoor() + (this.charRef.getWidth()/2) + this.swipeDistance, 
						(int)(this.charRef.getyCoor() - range), super.getWidth(), range);
				if(!weapon.isOddSwipe()) {
					if(charRef.getMove()[3]) {
						swipeDistance += (super.getSpeed() + this.charRef.getStats().getSpeed());
					} else {
						swipeDistance += super.getSpeed();
					}
				} else {
					if(charRef.getMove()[1]) {
						swipeDistance -= (super.getSpeed() + this.charRef.getStats().getSpeed());
					} else {
						swipeDistance -= super.getSpeed();
					}
				}
			}
			else if(super.getDirection() == 1) {
				super.setBounds((int)this.charRef.getxCoor() + this.charRef.getWidth(), 
						(int)this.charRef.getyCoor() + (this.charRef.getHeight()/2) + this.swipeDistance, range, super.getHeight());
				if(!weapon.isOddSwipe()) {
					if(charRef.getMove()[0]) {
						swipeDistance += (super.getSpeed() + charRef.getStats().getSpeed());
					} else {
						swipeDistance += super.getSpeed();
					}
				} else {
					if(charRef.getMove()[2]) {
						swipeDistance -= (super.getSpeed() + charRef.getStats().getSpeed());
					} else {
						swipeDistance -= super.getSpeed();
					}
				}
			}
			else if(super.getDirection() == 2) {
				if(!rotated) {
					this.sprite.rotateImage(90);
				}
				super.setBounds((int)this.charRef.getxCoor() + (this.charRef.getWidth()/2) + this.swipeDistance, 
						(int)this.charRef.getyCoor() + this.charRef.getHeight(), super.getWidth(), range);
				if(weapon.isOddSwipe()) {
					if(charRef.getMove()[3]) {
						swipeDistance += (super.getSpeed() + this.charRef.getStats().getSpeed());
					} else {
						swipeDistance += super.getSpeed();
					}
				} else {
					if(charRef.getMove()[1]) {
						swipeDistance -= (super.getSpeed() + this.charRef.getStats().getSpeed());
					} else {
						swipeDistance -= super.getSpeed();
					}
				}
			}
			else if(super.getDirection() == 3) {
				if(!rotated) {
					this.sprite.rotateImage(180);
				}
				super.setBounds((int)(charRef.getxCoor() - range), (int)charRef.getyCoor() + (charRef.getHeight()/2) + swipeDistance, range, super.getHeight());
				if(weapon.isOddSwipe()) {
					if(charRef.getMove()[0]) {
						swipeDistance += (super.getSpeed() + charRef.getStats().getSpeed());
					} else {
						swipeDistance += super.getSpeed();
					}
				} else {
					if(charRef.getMove()[2]) {
						swipeDistance -= (super.getSpeed() + charRef.getStats().getSpeed());
					} else {
						swipeDistance -= super.getSpeed();
					}
				}
			}
			
			if(!weapon.isOddSwipe() && !rotated) {
				this.sprite.reflectImage();
				if(charRef.getDirection() % 2 == 1) this.sprite.rotateImage(180);
			}
			this.rotated = true;
		} 
	}
	
	public void setCharacter(Ninja charRef) {
		this.charRef = charRef;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setRange(int r) {
		range = r;
	}
	
	private void instantiate() {
		if(weapon.isOddSwipe()) {
			if(super.getDirection() < 2) {
				this.swipeDistance = 30;
			} else {
				this.swipeDistance = -16;
			}
		} else {
			if(super.getDirection() < 2) {
				this.swipeDistance = -16;
			} else {
				this.swipeDistance = 30;
			}
		}
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return false;
	}

}
