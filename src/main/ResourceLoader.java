package main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public static BufferedImage getImage(String uri) throws IOException {
		BufferedImage image = null;	
		
		image = ImageIO.read(ResourceLoader.class.getResource("/" + uri));
		
		return image;
		
	}
	
}

//System.out.println(ResourceLoader.class.getResource(""));
