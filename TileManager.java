//package
package tile;

//imports
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import Main.GamePanel;

//Public class TileManager
public class TileManager {
	//Make use of the GamePanel
	GamePanel gp;
	//Make tiles as array
	Tile[] tile;
	//int mapTileNum as an array
	int mapTileNum[][];
	//TileManager constructor
	public TileManager(GamePanel gp) {
		//this.gp=gp
		this.gp=gp;
		//create ten type of Tiles
		tile = new Tile[10];
		//instantiate mapTileNum
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		//call method from constructor
		getTileImage();
		//call loadMap method
		loadMap("/maps/Map.txt");
	}//end constructor
	//public void getTileImage
	public void getTileImage() {
		//try
		try {
			//Tile at array 0 equal new tile
			tile[0] = new Tile();
			//get tileImage from Desert.png
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Desert.png"));
			
			//Tile at array 1 equal new tile
			tile[1] = new Tile();
			//get tileImage from Tundra.png
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tundra.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}//end catch
	}//end method
	//public void loadMap method
	public void loadMap(String filePath) {
		//try
		try {
			//get map txt file
			InputStream is = getClass().getResourceAsStream(filePath);
			//Instantiate BufferedReader to read context of txt file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			//Declare col,row with 0
			int col = 0;
			int row = 0;
			//while col is less than maxScreenCol and row is less than maxScreenRow
			while(col<gp.maxScreenCol && row< gp.maxScreenRow) {
				//read line of Map.txt
				String line = br.readLine();
				//while col<gp.maxScreenCol
				while(col<gp.maxScreenCol) {
					//Splits the numbers and put in array
					String numbers[] = line.split(" ");
					//Changing string to int to able to use
					int num = Integer.parseInt(numbers[col]);
					//mapTileNum is num
					mapTileNum[col][row]= num;
					//increment col
					col++;
				}//end while
				//if col equal equal to gp.maxScreenCol
				if(col==gp.maxScreenCol) {
					//col is equal to zero
					col = 0;
					//row is being incremented
					row++;
				}//end if
			}//end while loop
			//close reader since we are done
			br.close();
		}catch(Exception e) {
			
		}//end catch
	}//end method
	//public void draw method
			public void draw(Graphics2D g2) {
				//Declare int,col,row,x and y with 0
				int col = 0;
				int row = 0;
				int x = 0;
				int y = 0;
				//while col is less than maxScreenCol and row is less than maxScreenRow
				while(col<gp.maxScreenCol && row<gp.maxScreenRow) {
					//extract a tile number
					int tileNum = mapTileNum[col][row];
					//draw the images
					g2.drawImage(tile[tileNum].image,x,y, gp.tileSize,gp.tileSize,null);
					//draw the next col by incrementing
					col++;
					x+=gp.tileSize;
					//if col == gp.maxScreenCol
					if(col==gp.maxScreenCol) {
						//resets col and x while increasing row and y position
						col=0;
						x=0;
						row++;
						y+=gp.tileSize;
					}//end if
				}//end while loop
			}//end draw
}//end class
