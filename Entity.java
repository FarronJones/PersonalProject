//package
package entity;
//imports
import java.awt.image.BufferedImage;

//public class Entity
//Stores variables that will be used in player,monster and NPC classes.
public class Entity {
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
}//end class
