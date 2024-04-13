//package
package entity;
//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
//Import Gamepanel and KeyHandler from main class
import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import tile.Tile;
//public class Player extends Entity
public class Player extends Entity {
	//Graphics 2D g2
	Graphics2D g2;
	//KeyHandler KeyH
	KeyHandler keyH;
	//Public final int screen X and Y
	public final int screenX;
	public final int screenY;
	//Constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		//super(gp)
		super(gp);
		//Screen X and Y
		screenX=gp.screenWidth/2 -(gp.tileSize/2);
		screenY=gp.screenHeight/2-(gp.tileSize/2);
		//this==this
		this.keyH=keyH;
		//instantiate Rectangle class
		solidArea = new Rectangle();
		//Set up solidArea 
		solidArea.x=8;
		solidArea.y=66;
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY= solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		//call setDefaultValues method
		setDefaultValues();
		//call getPlayerImage method
		getPlayerImage();
		//call getPlayerAttackImage
		getPlayerAttackImage();
	}//end Player
	//public void setDefaulValues
	public void setDefaultValues() {
		//repeating code from GamePanel class
		worldX=gp.tileSize*7;
		worldY=gp.tileSize*6;
		speed=4;
		//default direction
		direction = "down";
		//Player Status
		maxLife = 6;
		life = maxLife;
	}//end void
	//public void getPlayerImage
	public void getPlayerImage() {
		//setup the player images
		up1=setup("/player/boy_up_1",gp.tileSize,gp.tileSize);
		up2=setup("/player/boy_up_2",gp.tileSize,gp.tileSize);
		down1=setup("/player/boy_down_1",gp.tileSize,gp.tileSize);
		down2=setup("/player/boy_down_2",gp.tileSize,gp.tileSize);
		left1=setup("/player/boy_left_1",gp.tileSize,gp.tileSize);
		left2=setup("/player/boy_left_2",gp.tileSize,gp.tileSize);
		right1=setup("/player/boy_right_1",gp.tileSize,gp.tileSize);
		right2=setup("/player/boy_right_2",gp.tileSize,gp.tileSize);
	}//end getPlayerImage
	//public void getPlayerAttackImage
	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1-removebg-preview",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2-removebg-preview",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1-removebg-preview",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2-removebg-preview",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup("/player/boy_attack_left_1-removebg-preview",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2-removebg-preview",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1-removebg-preview",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2-removebg-preview",gp.tileSize*2,gp.tileSize);
	}//end getPlayerAttackImage
	//public void update
	public void update() {
		//if attacking equals true
		if(attacking==true) {
			//call attacking method
			attacking();
		}//end if
		// else if any of the keypressed is equal to true, the the spriteCounter increase
		else if(keyH.upPressed==true || keyH.downPressed == true || 
				keyH.leftPressed==true || keyH.rightPressed==true) {
			//change player position
			//updates player coordinates
			if(keyH.upPressed==true) {
				direction = "up";
			}//end if
			else if(keyH.downPressed==true) {
				direction = "down";
			}//end if
			else if(keyH.leftPressed==true) {
				direction = "left";
			}//end if
			else if(keyH.rightPressed==true) {
				direction = "right";
			}//end if
			//CollisionOn equals false
			collisionOn=false;
			//gp.cChecker.checkTile this
			gp.cChecker.checkTile(this);
			
			//check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			interactMonster(monsterIndex);
			
			//contact monster called
			contactMonster(monsterIndex);
			//If collision is false, player can move
			if(collisionOn==false) {
				switch(direction) {
				case "up":
					worldY-=speed;
					break;
				case "down":
					worldY+=speed;
					break;
				case "left":
					worldX-=speed;
					break;
				case "right":
					worldX+=speed;
					break;
				}//end switch
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
			//This needs to be outside of key if statement!
			//if invincible==true
			if(invincible==true) {
				//invinciblecounter is being incremented
				invinciblecounter++;
				//if invinciblecounter is greater than 60
				if(invinciblecounter>60) {
					//invincible equals false
					invincible=false;
					//invinciblecounter equals to zero
					invinciblecounter=0;
				}//end if
			}//end if
		}//end update method
	//public void attacking method
	public void attacking() {
		//spriteCounter is being incremented
		spriteCounter++;
		//if spriteCounter is less than or equal to 5
		if(spriteCounter <=5) {
			//SpriteNum is equal to 1
			spriteNum=1;
		}//end if
		//if spriteCounter is greater than 5 and spriteCounter is less than or equal to 25
		if(spriteCounter>5 && spriteCounter<=25) {
			//spriteNum equal 2
			spriteNum = 2;
		}//end if
		//if spriteCounter greater than 25
		if(spriteCounter>25) {
			//spriteNum equals 1
			spriteNum=1;
			//spriteCounter equals to zero
			spriteCounter=0;
			//attacking equal false
			attacking = false;
		}//end if spriteCounter
	}//end public void attacking()
	//public void contactMonster int i
	public void contactMonster(int i) {
		//if i is not equal 999
		if(i!=999) {
			if(invincible==false) {
			//decrease life
			life-=1;
			invincible=true;
			}//end if
		}//end if
	}//end method
	//public void draw
	public void draw(Graphics2D g2) {
		//BufferedImage is null
		BufferedImage image = null;
		//switch direction
		switch(direction) {
		//each case will update the image accordingly to create a working animation
		case "up":
			//if attacking ==false
			if(attacking == false) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = up1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = up2;
				}//end if
			}//end if
			//if attacking==true
			if(attacking==true) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = attackUp1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = attackUp2;
				}//end if
			}//end if
			break;
		case "down":
			//if attacking ==false
			if(attacking == false) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = down1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = down2;
				}//end if
			}//end if
			//if attacking==true
			if(attacking==true) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = attackDown1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = attackDown2;
				}//end if
			}//end if
			
			break;
		case "left":
			//if attacking ==false
			if(attacking == false) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = left1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = left2;
				}//end if
			}//end if
			//if attacking==true
			if(attacking==true) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = attackLeft1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = attackLeft2;
				}//end if
			}//end if
			break;
		case "right":
			//if attacking ==false
			if(attacking == false) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = right1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = right2;
				}//end if
			}//end if
			//if attacking==true
			if(attacking==true) {
				//if spriteNum equal 1
				if(spriteNum==1) {
					image = attackRight1;
				}//end if
				//if spriteNum equal 2
				if(spriteNum == 2) {
					image = attackRight2;
				}//end if
			}//end if
			break;
		}
		//g2.drawImage to draw the image
		g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
	}//end draw method
	//public void interactMonster
	public void interactMonster(int i) {
		//if gp.keyH.enterPressed==true
		if(gp.keyH.enterPressed==true) {
		//if i is not equal to 999
		if(i!=999) {
			System.out.println("You are hitting an monster!");
		}//end if
		else {
			//if gp.keyH.enterPressed==true
			if(gp.keyH.enterPressed==true) {
				//attacking equals true
				attacking=true;
			}//end if KeyH.enterPressed
		}//end else
		}//end if KeyH.enter pressed
	}//end interactMonster
}//end public class
