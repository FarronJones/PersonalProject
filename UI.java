//packages
package Main;
//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import object.OBJ_Heart;
import object.SuperObject;
//public class UI
public class UI {
		//Graphics 2D g2
		Graphics2D g2;
		//Buffered Images
		BufferedImage heart_full,heart_half, heart_blank;
		//For font
		Font maruMonica;
		//GamePanel
		GamePanel gp;
		//UI constructor
			public UI(GamePanel gp) {
				//this.gp=gp;
				this.gp=gp;
				//try
				try {
					//inputstream is equal get the font
					InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
					//maruMonica equal Fonnt is created
					maruMonica = Font.createFont(Font.TRUETYPE_FONT,is);
				}//end try
				//catch
				catch(FontFormatException e) {
					e.printStackTrace();
				}//end catch
				//catch
				catch(IOException e) {
					e.printStackTrace();
				}//end catch
				//create HUD OBJECT
				SuperObject heart = new OBJ_Heart(gp);
				//set heart to images
				heart_full=heart.image;
				heart_half=heart.image2;
				heart_blank=heart.image3;
		}//end UI constructor
			//public void draw
			public void draw(Graphics2D g2) {
				//this.g2 equal to g2
				this.g2=g2;
				//set font to maruMonica
				g2.setFont(maruMonica);
				//set rendering
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				//set color to white
				g2.setColor(Color.white);
				//if gp.gameState equal equal to gp.playState
				if(gp.gameState==gp.playState) {
				//drawPlayerLife method
				drawPlayerLife();
				}//end if
			}//end draw method
			//public void drawPlayerLife method
			public void drawPlayerLife() {
				//int x and y is tileSize divided by 2
				int x = gp.tileSize/2;
				int y = gp.tileSize/2;
				//int i equal to zero
				int i = 0;
				//while i is less than player.maxLife/2
				//Draw the max life
				while(i<gp.player.maxLife/2) {
					//draw the blank hearts
					g2.drawImage(heart_blank,x,y,null);
					//i is being incremented
					i++;
					//increases x by tileSize
					x+=gp.tileSize;
				}//end while loop
				//Resets the values
				x=gp.tileSize/2;
				y=gp.tileSize/2;
				i=0;
				//Draw the current life
				//while i is less than gp.player.life
				while(i<gp.player.life) {
					//draw the half hearts
					g2.drawImage(heart_half,x,y,null);
					//increment i
					i++;
					//if i is less than gp.player.life
					if(i<gp.player.life) {
						//draw the full hearts
						g2.drawImage(heart_full,x,y,null);
					}//end if
					//increment i
					i++;
					//increment i
					x+=gp.tileSize;
				}//end while loope
			}//end drawPlayerLife method
}//end public class UI
