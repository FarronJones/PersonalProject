//package
package Main;
//imports
import entity.Entity;

//public class CollisionChecker
public class CollisionChecker {
	//Gamepanel gp
	GamePanel gp;
	//public CollisionChecker
	public CollisionChecker(GamePanel gp) {
		//this.gp=gp
		this.gp=gp;
	}//end public CollisionChecker
	//public void checkTile
	public void checkTile(Entity entity) {
		//int entityLeftWorldX
		int entityLeftWorldX=entity.worldX+entity.solidArea.x;
		//int entityRightWorldX
		int entityRightWorldX = entity.worldX+entity.solidArea.x+entity.solidArea.width;
		//int entityTopWorldY
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		//int entityBottomWorldY
		int entityBottomWorldY = entity.worldY + entity.solidArea.y+entity.solidArea.height;
		
		//Int entity left col
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		//int entity Right col
		int entityRightCol = entityRightWorldX/gp.tileSize;
		//int entity top row
		int entityTopRow = entityTopWorldY/gp.tileSize;
		//Int entity bottomrow
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		//int tileNum1, tileNum2
		int tileNum1, tileNum2;
		//switch(entity.direction)
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY-entity.speed)/gp.tileSize;
			tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY+entity.speed)/gp.tileSize;
			tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX-entity.speed)/gp.tileSize;
			tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX+entity.speed)/gp.tileSize;
			tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn=true;
			}
			break;
		}//end switch
	}//checkTile
}//end public class CollisionChecker
