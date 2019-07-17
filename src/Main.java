import main.Gamepanel;
import main.ResourceLoader;
import sprite.ImageScalar;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	
	private JFrame frame;
	
	public Main() {
		frame = new JFrame();
		
		System.setProperty("-Dsun.java2d.opengl", "True");
		Gamepanel gamepanel = new Gamepanel();
		
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Endless");
		frame.setExtendedState(JFrame.NORMAL);
		//frame.setUndecorated(true);
		
		frame.setIconImage(createIcon());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new Main();
	}
	
	private BufferedImage createIcon() {
		ImageScalar scl = new ImageScalar();
		BufferedImage img = null;
		try {
			img = ResourceLoader.getImage("imgs/menu/chronus.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scl.getScaledImage(img, 2);
	}
	
	public void setFullScreen() {
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
	}
}