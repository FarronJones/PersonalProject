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
import Main.UtilityTool;

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
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		//call method from constructor
		getTileImage();
		//call loadMap method
		loadMap("/maps/Map.txt");
	}//end constructor
	//public void getTileImage
	public void getTileImage() {
			//setup the tile images
			setup(0,"Desert",false);
			setup(1,"Tundra",false);
	
	}//end method
	//public void setup method
	public void setup(int index, String imageName, boolean collision) {
		//instantiate utilityTool
		UtilityTool uTool = new UtilityTool();
		//try
		try {
			//Code to scale the tiles
			tile[index]= new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
			tile[index].collision=collision;
		}//end try
		//catch
		catch(IOException e) {
			e.printStackTrace();
		}//end catch
	}//end setup method
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
			//while col is less than maxWorldCol and row is less than maxWorldRow
			while(col<gp.maxWorldCol && row< gp.maxWorldRow) {
				//read line of Map.txt
				String line = br.readLine();
				//while col<gp.maxWorldCol
				while(col<gp.maxWorldCol) {
					//Splits the numbers and put in array
					String numbers[] = line.split(" ");
					//Changing string to int to able to use
					int num = Integer.parseInt(numbers[col]);
					//mapTileNum is num
					mapTileNum[col][row]= num;
					//increment col
					col++;
				}//end while
				//if col equal equal to gp.WorldCol
				if(col==gp.maxWorldCol) {
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
				//Declare int,worldcol,worldrow with 0
				int worldCol = 0;
				int worldRow = 0;
				//while worldcol is less than maxworldCol and worldrow is less than maxWorldRow
				while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {
					//extract a tile number
					int tileNum = mapTileNum[worldCol][worldRow];
					//int worldX equals
					int worldX=worldCol*gp.tileSize;
					//int worldY equals
					int worldY = worldRow*gp.tileSize;
					//int screenX equals
					int screenX = worldX - gp.player.worldX+gp.player.screenX;
					//int screenY equals
					int screenY = worldY - gp.player.worldY+gp.player.screenY;
					//this if statement creates boundary to draw tiles
					if(worldX+gp.tileSize> gp.player.worldX-gp.player.screenX&&
					   worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
					   worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
					   worldY-gp.tileSize<gp.player.worldY+gp.player.screenY) {
						//draw the images
					   g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
					}
					//draw the next worldcol by incrementing
					worldCol++;
					//if col == gp.maxWorldCol
					if(worldCol==gp.maxWorldCol) {
						//worldcol equal to zero and worldrow is incremented
						worldCol=0;
						worldRow++;
					
					}//end if
				}//end while loop
			}//end draw
}//end class
