//package
package tile;

//imports
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;

//Public class TileManager
public class TileManager {
	//Make use of the GamePanel
	GamePanel gp;
	//Make tiles as array
	Tile[] tile;
	//TileManager constructor
	public TileManager(GamePanel gp) {
		//this.gp=gp
		this.gp=gp;
		//create ten type of Tiles
		tile = new Tile[10];
		//call method from constructor
		getTileImage();
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
	//public void draw method
			public void draw(Graphics2D g2) {
				//Declare int,col,row,x and y with 0
				int col = 0;
				int row = 0;
				int x = 0;
				int y = 0;
				//while col is less than maxScreenCol and row is less than maxScreenRow
				while(col<gp.maxScreenCol && row<gp.maxScreenRow) {
					//draw the images
					g2.drawImage(tile[0].image,x,y, gp.tileSize,gp.tileSize,null);
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

