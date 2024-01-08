//package
package Main;
//imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import entity.Entity;
import entity.Player;
import monster.MON_IceClops;
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
	
	//World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxWorldRow;
	
	//initiate keyHandler
	KeyHandler keyH = new KeyHandler(this);
	//declare Thread gameThread
	Thread gameThread;
	//Entity monster array
	public Entity monster[] = new Entity[10];
	//ArrayList created
	ArrayList<Entity> entityList = new ArrayList<>();
	//FPS
	int FPS = 60;
	
	//Instantiate UI class
	public UI ui = new UI(this);
	//instantiate GamePanel class and KeyHandler
	public Player player = new Player(this,keyH);
	//instantiate TileManager
	TileManager tileM = new TileManager(this);
	//public AssetSetter
	public AssetSetter aSetter = new AssetSetter(this);
	
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
	}
	//startGameThread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		//aSetter to set Monster
		aSetter.setMonster();
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
		//for loop to go through monster.length
		for(int i=0; i<monster.length;i++) {
			if(monster[i]!=null) {
				monster[i].update();
			}//end if
		}//end for loop
	
		
	}//end update method
	//paintComponent1 method
	public void paintComponent(Graphics g) {
		//to use the paintComponent
		super.paintComponent(g);
		//Changes graphics to graphics 2d class because we making a 2d game
		Graphics2D g2 = (Graphics2D)g;
		//call draw method for the tiles
		tileM.draw(g2);
		//Monster
		for(int i=0; i<monster.length;i++) {
			  if(monster[i]!=null) {
				monster[i].draw(g2);
			}//end if
		}//end for loop
		//call player draw method
		player.draw(g2);
		//UI call drawPlayerLife
		ui.drawPlayerLife(g2);
		//good practice to save memory
		g2.dispose();
	}//end paintComponent method
}//end class
