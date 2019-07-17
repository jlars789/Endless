package console;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Gamepanel;
import menus.Menu;

public class Console extends Menu {
	
	private ArrayList<Character> currentLine = new ArrayList<Character>();
	private static final Command[] COMMANDS = {new Teleport(), new SpawnItem(), new ModifyStats()};
	private String response;
	private int ticks;

	public Console() {
		super((byte)0, (byte)0);
		this.response = "Enter Desire: ";
	}
	
	public void customKey(int key) {
		
		switch(key) {
		case KeyEvent.VK_PERIOD:
			type('.');
		break;
		
		case KeyEvent.VK_BACK_SPACE:
			currentLine.remove(currentLine.size() - 1);
		break;
		
		case KeyEvent.VK_SPACE:
			type(' ');
		break;
		
		case KeyEvent.VK_COMMA:
			type(',');
		break;
		
		case KeyEvent.VK_A:
			type('a');
		break;
		
		case KeyEvent.VK_B:
			type('b');
		break;
		
		case KeyEvent.VK_C:
			type('c');
		break;
		
		case KeyEvent.VK_D:
			type('d');
		break;
		
		case KeyEvent.VK_E:
			type('e');
		break;
		
		case KeyEvent.VK_F:
			type('f');
		break;
		
		case KeyEvent.VK_G:
			type('g');
		break;
		
		case KeyEvent.VK_H:
			type('h');
		break;
		
		case KeyEvent.VK_I:
			type('i');
		break;
		
		case KeyEvent.VK_J:
			type('j');
		break;
		
		case KeyEvent.VK_K:
			type('k');
		break;
		
		case KeyEvent.VK_L:
			type('l');
		break;
		
		case KeyEvent.VK_M:
			type('m');
		break;
		
		case KeyEvent.VK_N:
			type('n');
		break;
		
		case KeyEvent.VK_O:
			type('o');
		break;
		
		case KeyEvent.VK_P:
			type('p');
		break;
		
		case KeyEvent.VK_Q:
			type('q');
		break;
		
		case KeyEvent.VK_R:
			type('r');
		break;
		
		case KeyEvent.VK_S:
			type('s');
		break;
		
		case KeyEvent.VK_T:
			type('t');
		break;
		
		case KeyEvent.VK_U:
			type('u');
		break;
		
		case KeyEvent.VK_V:
			type('v');
		break;
		
		case KeyEvent.VK_W:
			type('w');
		break;
		
		case KeyEvent.VK_X:
			type('x');
		break;
		
		case KeyEvent.VK_Y:
			type('y');
		break;
		
		case KeyEvent.VK_Z:
			type('z');
		break;
		
		case KeyEvent.VK_0:
			type('0');
		break;
		
		case KeyEvent.VK_1:
			type('1');
		break;
		
		case KeyEvent.VK_2:
			type('2');
		break;
		
		case KeyEvent.VK_3:
			type('3');
		break;
		
		case KeyEvent.VK_4:
			type('4');
		break;
		
		case KeyEvent.VK_5:
			type('5');
		break;
		
		case KeyEvent.VK_6:
			type('6');
		break;
		
		case KeyEvent.VK_7:
			type('7');
		break;
		
		case KeyEvent.VK_8:
			type('8');
		break;
		
		case KeyEvent.VK_9:
			type('9');
		break;
		}
	}

	
	private void clearLine() {
		this.currentLine.clear();
	}
	
	private void type(char val) {
		this.currentLine.add(val);
	}
	
	private void checkValidity() {
		for(int i = 0; i < COMMANDS.length; i++) {
			int length = COMMANDS[i].name().length();
			try {
				if(trueString().substring(0, length).equals((COMMANDS[i].name()))) {
					runCommand(COMMANDS[i]);
					break;
				} 
				else if(trueString().contains("fuck")) {
					response = "Watch your language, mate";
				}
				else response = "Not a valid command";
			} catch(StringIndexOutOfBoundsException e) {
				response = "Not a valid command";
			}
		}
	}
	
	private void runCommand(Command c) {
		if(c.validField(trueString().substring(c.name().length() + 1))) {
			response = "Granted";
			if(c.forceClose()) this.back();
		} else {
			response = "Cannot";
		}
	}

	@Override
	public void select() {
		this.checkValidity();
		this.clearLine();
	}

	@Override
	public void back() {
		super.setOpen(false);
		Gamepanel.currentMenu = null;
	}
	
	private String trueString() {
		StringBuilder builder = new StringBuilder();
		for (Character value : this.currentLine) {
		    builder.append(value);
		}
		String text = builder.toString();
		return text;
	}

	@Override
	public void draw(Graphics2D g2d) {
		ticks++;
		g2d.setFont(SMALL);
		String loc = " ";
		if(ticks % 40 > 10) loc = "_";
		if(this.trueString().length() > 0) g2d.drawString(this.trueString() + loc, 100, 400);
		else g2d.drawString(response, 100, 400);
	}

}
