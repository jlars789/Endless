package sprite;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * Class used for simplifying BufferedImage modification. Is created by default with every sprite object.
 */

public class ImageScalar {

	public ImageScalar() {
	}
	
	/**
	 * Creates and resizes an image based on the scale given
	 * @param image the BufferedImage intended to scale
	 * @param scaleFactor The multiplier for the scale intended
	 * @return Resized image
	 */
	
	public BufferedImage getScaledImage(BufferedImage image, double scaleFactor) {
		return resize(image, (int)(image.getWidth() * scaleFactor), (int)(image.getHeight() * scaleFactor));
	}
	
	/**
	 * Resizes the image based on a width and height inputed
	 * @param img The BufferedImage intended to scale
	 * @param newW The new width intended for the image
	 * @param newH The new height intended for the image
	 * @return BufferedImage that is scaled with the parameters given
	 */
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	/**
	 * Rotates the image given by the angle (in degrees) given
	 * @param img The BufferedImage intended to rotate
	 * @param angle The angle (in degrees) the rotation will happen
	 * @return Rotated instance of the image parameter
	 */

	public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = img;
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        
        at.rotate(rads, x, y);
        
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		rotated = atop.filter(rotated, null);

        return rotated;
    }
	
	/**
	 * Reflects the image across the Y-axis 
	 * @param img Image intended to be reflected
	 * @return reflected instance of image
	 */
	
	public BufferedImage reflectImage(BufferedImage img) {
		BufferedImage reflected = img;
		AffineTransform at = new AffineTransform();
	    at.concatenate(AffineTransform.getScaleInstance(1, -1));
	    at.concatenate(AffineTransform.getTranslateInstance(0, -img.getHeight()));
	    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    reflected = atop.filter(reflected, null);
	    reflected = rotateImageByDegrees(reflected, 180);
	    return reflected;
	}

}
