//package
package entity;
//imports
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.UtilityTool;

//public class Entity
//Stores variables that will be used in player,monster and NPC classes.
public class Entity {
	//GamePanel gp
	GamePanel gp;
	//public int x,y declared
	public int x,y;
	//public int speed declared
	public int speed;
	//To store image file
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	//Declare String direction
	public String direction;
	//Declare int spriteCounter as 0
	public int spriteCounter = 0;
	//Declare int spriteNum as 1
	public int spriteNum = 1;
	//public int actionLockCounter equal to zero
	public int actionLockCounter=0;
	//Character Status
	public int maxLife;
	public int life;
	//public Entity 
	public Entity(GamePanel gp) {
		//this.gp=gp
		this.gp=gp;
	}//end public entity
	//BufferedImage setup method
			public BufferedImage setup(String imagePath) {
				//instantiate utilityTool
				UtilityTool uTool = new UtilityTool();
				//image is equal to null
				BufferedImage image = null;
						//try
						try {
							//scale the player images
							image = ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
							image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
						}//end try
						//catch
						catch(IOException e) {
							e.printStackTrace();
						}//end catch
						//return image
						return image;
			}//end BufferedImage setup method
}//end class
