package friendlies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import abilities.Cowboy.PortableCover;
import hostiles.Hostile;
import main.Entity;
import main.Gamepanel;

public class Barricade extends Friendly {
	
	private static int maxSize = 140;
	private static int width = 40;

	public Barricade(float xCoor, float yCoor, int duration) {
		super(newXCoor(xCoor), newYCoor(yCoor), newDimensions()[0], newDimensions()[1], duration);
	}
	
	private static float newXCoor(float xCoor) {
		switch(Gamepanel.mainChar.getDirection()) {
		case 0:
			xCoor-=35;
		break;
		case 1:
			xCoor += 100;
		break;
		case 2:
			xCoor -= 30;
		break;
		case 3:
			xCoor -= 35;
		break;
		}
		return xCoor;
	}
	
	private static float newYCoor(float yCoor) {
		switch(Gamepanel.mainChar.getDirection()) {
		case 0:
			yCoor -= 50;
		break;
		case 1:
			yCoor -= 20;
		break;
		case 2:
			yCoor += 120;
		break;
		case 3:
			yCoor -= 20;
		break;
		}
		return yCoor;
	}
	
	private static int[] newDimensions() {
		int[] dim = new int[2];
		if(Gamepanel.mainChar.getDirection() % 2 == 1) {
			dim[0] = width;
			dim[1] = maxSize;
		} else {
			dim[0] = maxSize;
			dim[1] = width;
		}
		return dim;
	}
	
	public void directionFind(int direction) {
		if(direction == 0) {
			super.setWidth(maxSize);
			super.shift(-35, -50);
		}
		else if(direction == 1) {
			super.setHeight(maxSize);
			super.shift(100, -20);
		}
		else if(direction == 2) {
			super.setWidth(maxSize);
			super.shift(-30, 120);
		}
		else if(direction == 3) {
			super.setHeight(maxSize);
			super.shift(-45, -20);
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)super.getxCoor(), (int)super.getyCoor(), super.getWidth(), super.getHeight());
	}

	@Override
	public Friendly copy() {
		return new Barricade(super.getxCoor(), super.getyCoor(), PortableCover.duration());
	}

	@Override
	protected ArrayList<Entity> getProjectile() {
		return null;
	}

	@Override
	public void action(Hostile enemy) {
	}

}
