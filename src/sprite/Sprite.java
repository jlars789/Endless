package sprite;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

import main.ResourceLoader;

/**
 * Abstract class used for Sprites
 */

public abstract class Sprite {
	
	private BufferedImage image;
	private ImageScalar imageScalar;
	
	/**
	 * Will load the image from storage into memory. 
	 * @param path
	 */

	public Sprite(String path) {
		
		try {
			this.image = ResourceLoader.getImage(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.imageScalar = new ImageScalar();
		
	}
	
	public Sprite(BufferedImage bi) {
		this.image = deepCopy(bi);
		this.imageScalar = new ImageScalar();
	}
	/**
	 * Retrieves the current frame of the animation tied to the sprite
	 * @return Current frame of the animation in the form of a Buffered Image
	 */
	public BufferedImage getImage() {
		return this.image;
	}
	
	/**
	 * Used for scaling the image. It is better to use {@code getScaled()} in most cases.
	 * @param scaleValue Multiplier for image size
	 */
	
	public void scaleImage(double scaleValue) {
		this.image = imageScalar.getScaledImage(this.image, scaleValue);
	}
	
	/**
	 * Used for creating a new image based on a predetermined size. 
	 * @param newWidth The new width of the image
	 * @param newHeight The new height of the image
	 */
	
	public void resizeImage(int newWidth, int newHeight) {
		this.image = imageScalar.resize(this.image, newWidth, newHeight);
	}
	
	/**
	 * Used for rotating the image (using degrees).
	 * All rotations are "Nearest Neighbor" rotations, allowing individual pixels to keep their relative form.
	 * The {@code currentRotated()} {@code cardinalRotate()} are better for general use. 
	 * @param degrees Degrees in which the image is rotate. 
	 */
	
	public void rotateImage(double degrees) {
		this.image = this.imageScalar.rotateImageByDegrees(this.image, degrees);
	}
	
	/**
	 * Reflects the image.
	 */
	
	public void reflectImage() {
		this.image = this.imageScalar.reflectImage(this.image);
	}
	
	/**
	 * Returns scaled instance of the default image. 
	 * @param scalar Multiplier in which the image will be scaled
	 * @return Scaled image
	 */
	
	public BufferedImage getScaled(double scalar) {
		BufferedImage subimg = this.image;
		subimg = imageScalar.getScaledImage(subimg, scalar);
		return subimg;
	}
	
	/**
	 * Returns a rotated instance of the default image.
	 * @param angle Angle (in degrees) the image will be rotated
	 * @return Rotated image
	 */
	
	public BufferedImage currentRotated(double angle) {
		BufferedImage subimg = this.image;
		subimg = imageScalar.rotateImageByDegrees(subimg, angle);
		return subimg;
	}
	
	/**
	 * Returns a rotated instance of the default image based on a multiple of 90.
	 * @param direction The multiple of 90 in which the image will be rotated. 
	 * @return Rotated image based on multiple of 90.
	 */
	
	public BufferedImage cardinalRotate(int direction) {
		BufferedImage subimg = this.image;
		if(direction > 1) direction -= 2;
		else direction += 2;
		subimg = imageScalar.rotateImageByDegrees(subimg,  direction * 90);
		return subimg;
	}
	
	public BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null).getSubimage(0, 0, bi.getWidth(), bi.getHeight());
	}
	
	protected void modifyImage(BufferedImage bi) {
		this.image = bi;
	}
	
	protected ImageScalar scalarProperty() {
		return this.imageScalar;
	}
	
	public abstract Sprite copy();

}
