//package
package entity;
//imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
//Import Gamepanel and KeyHandler from main class
import Main.GamePanel;
import Main.KeyHandler;

//public class Player extends Entity
public class Player extends Entity {
	//GamePanel gp
	GamePanel gp;
	//KeyHandler KeyH
	KeyHandler keyH;
	//Constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		//this==this
		this.gp = gp;
		this.keyH=keyH;
		//call setDefaultValues method
		setDefaultValues();
		//call getPlayerImage method
		getPlayerImage();
	}//end Player
	//public void setDefaulValues
	public void setDefaultValues() {
		//repeating code from GamePanel class
		x=100;
		y=100;
		speed=4;
		//default direction
		direction = "down";
	}//end void
	//public void getPlayerImage
	public void getPlayerImage() {
		//try
		try {
			//all this code load the images
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}//end catch
	}//end getPlayerImage
	//public void update
	public void update() {
		//change player position
				//updates player coordinates
				if(keyH.upPressed==true) {
					direction = "up";
					y-=speed;
				}//end if
				else if(keyH.downPressed==true) {
					direction = "down";
					y +=speed;
				}//end if
				else if(keyH.leftPressed==true) {
					direction = "left";
					x -=speed;
				}//end if
				else if(keyH.rightPressed==true) {
					direction = "right";
					x +=speed;
				}//end if
	}//end update method
	//public void draw
	public void draw(Graphics2D g2) {
		//BufferedImage is null
		BufferedImage image = null;
		//switch direction
		switch(direction) {
		//each case will update the image accordingly
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;
		}
		//g2.drawImage to draw the image
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
	}//end draw method
}//end public class
