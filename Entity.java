//package
package entity;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	public int worldX,worldY;
	//public int speed declared
	public int speed;
	//boolean attacking=false;
	boolean attacking = false;
	//To store image file
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,
	attackRight1,attackRight2;
	//Declare String direction
	public String direction;
	//Declare int spriteCounter as 0
	public int spriteCounter = 0;
	//Declare int spriteNum as 1
	public int spriteNum = 1;
	//public Boolean collisionOn is false
	public boolean collisionOn=false;
	//public int type
	public int type;//1==monster
	//public int actionLockCounter equal to zero
	public int actionLockCounter=0;
	//Public boolean invincible equals false
	public boolean invincible=false;
	//Public int invinciblecounter equals zero
	public int invinciblecounter=0;
	//Character Status
	public int maxLife;
	public int life;
	//public Rectangle solidArea
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	//public Rectangle attackArea
	public Rectangle attackArea = new Rectangle(0,0,48,48);
	//public int solidAreaDefaultX and solidAreaDefaultY
	public int solidAreaDefaultX, solidAreaDefaultY;

	//public Entity 
	public Entity(GamePanel gp) {
		//this.gp=gp
		this.gp=gp;
	}//end public entity
	//BufferedImage setup method
			public BufferedImage setup(String imagePath,int width, int height) {
				//instantiate utilityTool
				UtilityTool uTool = new UtilityTool();
				//image is equal to null
				BufferedImage image = null;
						//try
						try {
							//scale the player images
							image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
							image = uTool.scaleImage(image, width,height);
						}//end try
						//catch
						catch(IOException e) {
							e.printStackTrace();
						}//end catch
						//return image
						return image;
			}//end BufferedImage setup method
			//public void draw
			public void draw(Graphics2D g2) {
				//BufferedImage is null
				BufferedImage image = null;
				int screenX = worldX-gp.player.worldX+gp.player.screenX;
				int screenY = worldY-gp.player.worldY+gp.player.screenY;
				if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
				   worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
				   worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
				   worldY-gp.tileSize<gp.player.worldY+gp.player.screenY) {
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
					}//end switch
					//if invincible equals to true
					if(invincible==true) {
						//g2.setComposite for transparency
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
					}//end if
					//draw
					g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
					
					//g2.setComposite for transparency reset
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}//end if
						
			}//end draw
			//public void setAction
			public void setAction() {}
			//public void update
			public void update() {
				//setAction method is called
				setAction();
				//collisionOn equal false
				collisionOn=false;
				gp.cChecker.checkTile(this);
				boolean contactPlayer=gp.cChecker.checkPlayer(this);
				//if the monster and contactPlayer is true
				if(this.type==1&&contactPlayer==true) {
					if(gp.player.invincible==false) {
						//we can give damage
						gp.player.life-=1;
						gp.player.invincible=true;
						//if gp.player.life is less than or equal to zero
						if(gp.player.life<=0) {
							//gp.player is equal to null
							gp.player=null;
						}//end if
					}//end if
					
				}//end if
				//if collision on is false
				if(collisionOn == false) {
					//switch base off of direction
					switch(direction) {
					case "up":worldY -=speed;
					break;
					case "down":worldY +=speed;
					break;
					case "left":worldX-=speed;
					break;
					case "right":worldX +=speed;
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
				//if invincible==true
				if(invincible==true) {
					//invinciblecounter is being incremented
					invinciblecounter++;
					//if invinciblecounter is greater than 40
					if(invinciblecounter>40) {
						//invincible equals false
						invincible=false;
						//invinciblecounter equals to zero
						invinciblecounter=0;
					}//end if
				}//end if
				}//end update
}//end class
