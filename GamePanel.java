//imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//public class extends JPanel class which have functions
public class GamePanel extends JPanel implements Runnable {
	//screen settings is being made
	final int originalTileSize=16;//16x16 tile
	final int scale = 3;//scaling to fit resolution
	
	final int tileSize = originalTileSize*scale;//48x48 tile
	//int column is initialized as 15
	final int maxScreenCol = 16;
	//int row is initialized as 12
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;  //768 pixels
	final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//initiate keyHandler
	KeyHandler keyH = new KeyHandler();
	//declare Thread gameThread
	Thread gameThread;
	
	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	//constructor
	public GamePanel() {
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
	}//end constructor
	
	//startGameThread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}//end startGameThread
	
	@Override
	public void run() {
		//Create game loop which is important for this simple game
		//While the gameThread exists it complete code in while loop
		while(gameThread!=null) {
			//checks time using nanoTime
			long currentTime = System.nanoTime();
			//prints out the currentTime
			System.out.println("current Time: "+currentTime);
			//Update: update information such as character positions
			update();
			
			//Draw: draw the screen with updated information
			repaint();
		}//end while loop
		
	}//end run method
	//update
	public void update() {
		//change player position
		//updates player coordinates
		if(keyH.upPressed=true) {
			playerY -=playerSpeed;
		}//end if
		else if(keyH.downPressed=true) {
			playerY +=playerSpeed;
		}//end if
		else if(keyH.leftPressed=true) {
			playerX -=playerSpeed;
		}//end if
		else if(keyH.rightPressed=true) {
			playerX +=playerSpeed;
		}//end if
	}//end update method
	//paintComponent method
	public void paintComponent(Graphics g) {
		//to use the paintComponent
		super.paintComponent(g);
		//Changes graphics to graphics 2d class because we making a 2d game
		Graphics g2 = (Graphics2D)g;
		//set color
		g2.setColor(Color.white);
		//draws rectangle on screen
		g2.fillRect(playerX,playerY, tileSize, tileSize);
		//good practice to save memory
		g2.dispose();
	}//end paintComponent method
}//end class

