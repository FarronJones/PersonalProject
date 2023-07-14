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
				//draw the images
				g2.drawImage(tile[0].image,0,0, gp.tileSize,gp.tileSize,null);
				g2.drawImage(tile[1].image,48,0, gp.tileSize,gp.tileSize,null);
			}//end draw
}//end class
