package menus;

import java.awt.Font;
import java.awt.Graphics2D;

public abstract class Menu {
	
	private boolean open;
	private byte selector;
	private byte horiSelector;
	
	private byte horiSelectorMax;
	private byte selectorMax;
	
	public static final Font MAIN = new Font("Verdana", Font.PLAIN, 30);
	public static final Font SMALL = new Font("Verdana", Font.PLAIN, 18);
	public static final Font SMALL_BOLD = new Font("Verdana", Font.BOLD, 18);

	public Menu(byte hMax, byte vMax) {
		this.open = true;
		this.selector = 0;
		this.horiSelector = 0;
		
		this.horiSelectorMax = hMax;
		this.selectorMax = vMax;
	}
	
	public abstract void select();
	
	public abstract void back();
	
	public abstract void draw(Graphics2D g2d);
	
	public boolean isOpen() {
		return this.open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void customKey(int KeyEvent) {
	}
	
	/**
	 * Moves one of the two selectors in the menu
	 * @param move - 0: Up, 1: Right, 2: Down, 3: Left
	 */
	
	public void selectMove(int move) {
		if(move == 0) {
			if(selector > 0) {
				selector--;
			} else {
				selector = selectorMax;
			}
		}
		else if(move == 1) {
			if(horiSelector < horiSelectorMax) {
				horiSelector++;
			} else {
				horiSelector = 0;
			}
		}
		else if(move == 2) {
			if(selector < selectorMax) {
				selector++;
			} else {
				selector = 0;
			}
		}
		else if(move == 3) {
			if(horiSelector > 0) {
				horiSelector--;
			} else {
				horiSelector = horiSelectorMax;
			}
		}
	}
	
	protected byte getSelector() {
		return this.selector;
	}
	
	protected byte getHoriSelector() {
		return this.horiSelector;
	}

}
