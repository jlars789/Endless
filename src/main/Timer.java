package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Timer {
	
	public static long startTime;
	public static int framesElapsed;
	private static long elapsedTime;
	private static long minutes;
	
	public static void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		Font small = new Font("Verdana", Font.PLAIN, 18);
		
		String tempZero = "";
		
		if((elapsedTime)/1000 < 10) {
			tempZero = "0";
		}
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(small);
		
		g2d.drawString("" + Gamepanel.getFps(), 150, 30);
		g2d.drawString(minutes + ":" + tempZero + (elapsedTime)/1000, 30, 30);
		
		framesElapsed++;
		if(elapsedTime > 59999) minutes++;
		elapsedTime = System.currentTimeMillis() - (startTime + (60000 * minutes));
	}
	
	public static void begin() {
		startTime = System.currentTimeMillis();
		minutes = 0;
	}
	
	public static void increase() {
		
	}
	
	public static long getTotalElapsedTime() {
		return System.currentTimeMillis() - startTime;
	}

	

}
