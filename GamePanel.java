//package
package Main;
//imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import entity.Player;
import object.OBJ_Heart;
import object.SuperObject;
import tile.TileManager;

//public class extends JPanel class which have functions
public class GamePanel extends JPanel implements Runnable {
	//screen settings is being made
	final int originalTileSize=16;//16x16 tile
	final int scale = 3;//scaling to fit resolution
	//public for classes
	public final int tileSize = originalTileSize*scale;//48x48 tile
	//int column is initialized as 15
	public final int maxScreenCol = 16;
	//int row is initialized as 12
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;  //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//initiate keyHandler
	KeyHandler keyH = new KeyHandler();
	//declare Thread gameThread
	Thread gameThread;
	
	//Game State
	public int gameState;
	public final int playState=1;
	
	//FPS
	int FPS = 60;
	
	//instantiate GamePanel class and KeyHandler
	Player player = new Player(this,keyH);
	//instantiate TileManager
	TileManager tileM = new TileManager(this);
	
	//For heart image
	Graphics2D g2;
	BufferedImage heart_full,heart_half, heart_blank;
	
	//For font
	Font maruMonica;
	//GamePanel
	GamePanel gp;

	
	//constructor
	public GamePanel(GamePanel gp) {
		//this.gp=gp;
		this.gp=gp;
		//try
		try {
			//set size of GamePanel
			this.setPreferredSize(new Dimension(screenWidth, screenHeight));
			//set background color
			this.setBackground(Color.black);
			//improve game rendering performance
			this.setDoubleBuffered(true);
			//add key input to panel
			this.addKeyListener(keyH);
			//GamePanel can be focused to receive key input
			this.setFocusable(true);
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
	}//end constructor
	//public void setupGame
	public void setupGame() {
		//gameState equal to playState
		gameState=playState;
	}//end setupGame
	//startGameThread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}//end startGameThread
	
	@Override
	public void run() {
		//sleep method to create a game loop, have the thread sleep for the remaining time
		//divide nanoseconds from FPS for precise calculation
		double drawInterval = 1000000000/FPS;// 0.166666 seconds
		//The next draw time to draw screen which starts the game loop
		double nextDrawTime = System.nanoTime() + drawInterval;
		//Create game loop which is important for this simple game
		//While the gameThread exists it complete code in while loop
		while(gameThread!=null) {
			//Update: update information such as character positions
			update();
			
			//Draw: draw the screen with updated information
			repaint();
			
			//Try
			try {
				//how much time remaining until the next draw time?
				double remainingTime = nextDrawTime - System.nanoTime();
				//convert remainingTime from nano to milliseconds
				remainingTime = remainingTime/1000000;
				//Overall if update and repaint takes more than the drawInterval, then no time is left
				//if remainingTime is less than zero
				if(remainingTime<0) {
					//remainingTime equal to zero
					remainingTime=0;
				}//end if
				//pauses the game loop till its over
				Thread.sleep((long)remainingTime);
				//set new Draw time which is being added 0.01666 seconds later
				nextDrawTime+=drawInterval;
		    //catch
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch
		}//end while loop
		
	}//end run method
	//update
	public void update() {
		//if gameState equal to playState
		if(gameState==playState) {
		//calls player update method
		player.update();
		}//end if
	}//end update method
	//paintComponent1 method
	public void paintComponent(Graphics g) {
		//to use the paintComponent
		super.paintComponent(g);
		//Changes graphics to graphics 2d class because we making a 2d game
		Graphics2D g2 = (Graphics2D)g;
		//call draw method for the tiles
		tileM.draw(g2);
		//call player draw method
		player.draw(g2);
		//call draw method for hearts
		draw(g2);
		//good practice to save memory
		g2.dispose();
	}//end paintComponent method
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
		while(i<player.maxLife/2) {
			g2.drawImage(heart_blank,x,y,null);
			//i is being incremented
			i++;
			//increases x by tileSize
			x+=gp.tileSize;
		}//end while loop
	}//end drawPlayerLife method
}//end class
