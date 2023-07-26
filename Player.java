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
		//Player Status
		maxLife = 100;
		life = maxLife;
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
		//if any of the keypressed is equal to true, the the spriteCounter increase
		if(keyH.upPressed==true || keyH.downPressed == true || 
				keyH.leftPressed==true || keyH.rightPressed==true) {
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
			//In every frame the update method gets called, increases the counter by 1
			//increment the spriteCounter 
			spriteCounter++;
			//if it reaches 12 it changes the sprite, since the update method is 60 times per
			//second then the image changes every 12 frames.
			//if spriteCounter greater than 12
			if(spriteCounter>12) {
				//if spriteNum equal to 1
				if(spriteNum==1) {
					//then spriteNum is 2
					spriteNum=2;
				}//end if
				//else if spriteNum equal to 2
				else if(spriteNum==2) {
					//then spriteNum is equal to 1
					spriteNum=1;
				}//end else if
				//the spriteCounter is reset
				spriteCounter=0;
			}//end if
			}//end if
		}//end update method
	//public void draw
	public void draw(Graphics2D g2) {
		//BufferedImage is null
		BufferedImage image = null;
		//switch direction
		switch(direction) {
		//each case will update the image accordingly to create a working animation
		case "up":
			//if spriteNum equal 1
			if(spriteNum==1) {
				image = up1;
			}//end if
			//if spriteNum equal 2
			if(spriteNum == 2) {
				image = up2;
			}//end if
			break;
		case "down":
			//if spriteNum equal 1
			if(spriteNum==1) {
				image = down1;
			}//end if
			//if spriteNum equal 2
			if(spriteNum == 2) {
				image = down2;
			}//end if
			break;
		case "left":
			//if spriteNum equal to 1
			if(spriteNum==1) {
				image = left1;
			}//end if
			//if spriteNum equal to 2
	        if(spriteNum==2) {
	        	image = left2;
	        }//end if
			break;
		case "right":
			//if spriteNum equal to 1
			if(spriteNum==1) {
				image = right1;
			}//end if
			//if spriteNum equal to 2
			if(spriteNum==2) {
				image = right2;
			}//end if
			break;
		}
		//g2.drawImage to draw the image
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
	}//end draw method
}//end public class
