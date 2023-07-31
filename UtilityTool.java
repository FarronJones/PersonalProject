//package
package Main;
//imports
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//public class 
public class UtilityTool {
	//public scaleImage
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		//Code to scale image
		BufferedImage scaledImage = new BufferedImage(width,height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original,0,0,width,height,null);
		g2.dispose();
		
		//return scaledImage
		return scaledImage;
	}//end scaleImage method
}//end class
