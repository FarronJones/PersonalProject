//package object
package object;
//imports
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
//public class OBJ_Heart
public class OBJ_Heart extends SuperObject {
	//GamePanel gp
	GamePanel gp;
	//public OBJ_Heart
	public OBJ_Heart(GamePanel gp) {
		//this.gp equal to gp
		this.gp=gp;
		//name equal to Heart
		name = "Heart";
		//try
		try {
			//set health images
			image  = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image  = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
		}//end try
		//catch
		catch(IOException e) {
			e.printStackTrace();
		}//end catch
}//end OBJ_Heart method
}//end class

