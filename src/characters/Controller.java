package characters;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Controller implements Serializable {
	
	private static final long serialVersionUID = 490217962239708812L;
	private Characters charRef;
	
	public Controller(Characters charRef) {
		this.charRef = charRef;
	}
	
	public void passKeyCode(int keycode) {
		if(keycode == KeyEvent.VK_W || keycode == KeyEvent.VK_D || keycode == KeyEvent.VK_S || keycode == KeyEvent.VK_A) {
			this.move(keycode);
		}
		
		if(keycode >= KeyEvent.VK_LEFT && keycode <= KeyEvent.VK_DOWN) {
			this.look(keycode);
		}
		
		this.ability(keycode);
		this.actions(keycode);
	}
	
	private void move(int keycode) {
		if(keycode == KeyEvent.VK_D) {
			charRef.stop(3);
			charRef.move(1);
		}
		if(keycode == KeyEvent.VK_A) {
			charRef.stop(1);
			charRef.move(3);
		}
		if(keycode == KeyEvent.VK_W) {
			charRef.stop(2);
			charRef.move(0);
		}
		if(keycode == KeyEvent.VK_S) {
			charRef.stop(0);
			charRef.move(2);
		}
	}
	
	private void look(int keycode) {
		
		switch (keycode) {
		case KeyEvent.VK_UP:
			charRef.setDirection(0);
			break;
		case KeyEvent.VK_RIGHT:
			charRef.setDirection(1);
			break;
		case KeyEvent.VK_DOWN:
			charRef.setDirection(2);
			break;
		case KeyEvent.VK_LEFT:
			charRef.setDirection(3);
			break;
		}
		
		charRef.setShoot(true);
	}
	
	private void actions(int keycode) {
		if(keycode == KeyEvent.VK_R) {
			charRef.reload();
		}
		else if(keycode == KeyEvent.VK_F) {
			charRef.swapWeapons();
		}
	}
	
	private void ability(int keycode) {
		if(keycode == KeyEvent.VK_E) {
			charRef.abilityActivate(0);
		}
		else if(keycode == KeyEvent.VK_SHIFT) {
			charRef.abilityActivate(1);
		}
		else if(keycode == KeyEvent.VK_Q) {
			charRef.abilityActivate(2);
		}
		charRef.abilityKey(keycode);
	}

}
