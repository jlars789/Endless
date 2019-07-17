package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Pirate.Haymaker;
import hostiles.Hostile;
import main.Gamepanel;
import statuseffect.Cripple;
import weapons.Fisticuffs;

public class Fist extends Projectile {
	
	private static float range = 70;
	private Cripple cripple;
	private Haymaker abilityRef;

	public Fist(float xCoor, float yCoor, int direction, Haymaker fromAbility) {
		super(xCoor, yCoor, direction, 45, true, 200, fromAbility);
		this.abilityRef = fromAbility;
		this.cripple = fromAbility.getEffect();
	}
	
	public Fist(float xCoor, float yCoor, int direction, Fisticuffs fromWeapon) {
		super(xCoor, yCoor, direction, 45, true, 8, fromWeapon);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect((int)super.getxCoor(), (int)super.getyCoor(), super.getWidth(), super.getHeight());
	}
	
	public void action() {
		super.hit();
		if(abilityRef != null) {
			abilityRef.hit();
			this.destroy();
		}
	}
	
	public void effect(Hostile enemy) {
		Gamepanel.mainChar.getStats().getHealth().heal(30);
		if(cripple != null) {
			enemy.giveStatusEffect(cripple.copy());
		}
		
	}
	
	public void movement() {
		if(super.getDirection() == 0) {
			super.setBounds(Gamepanel.mainChar.getxCoor(), (Gamepanel.mainChar.getyCoor() - range), super.getWidth(), (int)range);
		}
		else if(super.getDirection() == 1) {
			super.setBounds(Gamepanel.mainChar.getxCoor() + Gamepanel.mainChar.getWidth(), (int)Gamepanel.mainChar.getyCoor() + 25, (int)range, super.getHeight());
		}
		else if(super.getDirection() == 2) {
			super.setBounds(Gamepanel.mainChar.getxCoor() + 15, Gamepanel.mainChar.getyCoor() + Gamepanel.mainChar.getHeight(), super.getWidth(), (int)range);
		}
		else if(super.getDirection() == 3) {
			super.setBounds((Gamepanel.mainChar.getxCoor() - range), Gamepanel.mainChar.getyCoor() + 25, (int)range, super.getHeight());
		}
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return true;
	}

}
