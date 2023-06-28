//imports
import java.awt.Color;
import java.awt.Dimension;
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
	
	//declare Thread gameThread
	Thread gameThread;
	
	//constructor
	public GamePanel() {
		//set size of GamePanel
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		//set background color
		this.setBackground(Color.black);
		//improve game rendering performance
		this.setDoubleBuffered(true);
	}//end constructor
	
	//startGameThread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}//end startGameThread
	
	@Override
	public void run() {
		//Create game loop which is important for this simple game
		
	}//end run method
}//end class
