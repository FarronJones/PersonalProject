//package
package Main;
//imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
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
	
	//FPS
	int FPS = 60;
	
	//instantiate GamePanel class and KeyHandler
	Player player = new Player(this,keyH);
	//instantiate TileManager
	TileManager tileM = new TileManager(this);
	
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
		//calls player update method
		player.update();
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
		//good practice to save memory
		g2.dispose();
	}//end paintComponent method
}//end class
