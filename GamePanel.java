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
	
	//FPS
	int FPS = 60;
	
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
		//change player position
		//updates player coordinates
		if(keyH.upPressed==true) {
			playerY -=playerSpeed;
		}//end if
		else if(keyH.downPressed==true) {
			playerY +=playerSpeed;
		}//end if
		else if(keyH.leftPressed==true) {
			playerX -=playerSpeed;
		}//end if
		else if(keyH.rightPressed==true) {
			playerX +=playerSpeed;
		}//end if
	}//end update method
	//paintComponent method
	public void paintComponent(Graphics g) {
		//to use the paintComponent
		super.paintComponent(g);
		//Changes graphics to graphics 2d class because we making a 2d game
		Graphics2D g2 = (Graphics2D)g;
		//set color
		g2.setColor(Color.white);
		//draws rectangle on screen
		g2.fillRect(playerX,playerY, tileSize, tileSize);
		//good practice to save memory
		g2.dispose();
	}//end paintComponent method
}//end class
