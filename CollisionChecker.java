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
	//NPC or Monster
	public int checkEntity(Entity entity,Entity[]target) {
		//int index=999
		int index=999;
		
		//for loop to go through target.length
		for(int i=0; i<target.length;i++) {
			if(target[i]!=null) {
				//Get entity's solid area position
				entity.solidArea.x=entity.worldX+entity.solidArea.x;
				entity.solidArea.y=entity.worldY+entity.solidArea.y;
				
				//Get the targets's solid area position
				target[i].solidArea.x=target[i].worldX+target[i].solidArea.x;
				target[i].solidArea.y=target[i].worldY+target[i].solidArea.y;
				
				//switch 
				switch(entity.direction) { 
				case "up":
					entity.solidArea.y-=entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn=true;
							index=i;
					}///end if
					break;
				case "down":
					entity.solidArea.y+=entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn=true;
						index=i;
					}///end if
					break;
				case "left":
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn=true;
							index=i;
					}///end if
					break;
				case "right":
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn=true;
							index=i;
					}///end if
					break;
				}//end switch
			}//end if
			entity.solidArea.x=entity.solidAreaDefaultX;
			entity.solidArea.y=entity.solidAreaDefaultY;
			target[i].solidArea.x=target[i].solidAreaDefaultX;
			target[i].solidArea.y=target[i].solidAreaDefaultY;
		}//end for loop
		return index;
	}//end checkEntity method
	//public void checkPlayer
	public void checkPlayer(Entity entity) {
				//Get entity's solid area position
				entity.solidArea.x=entity.worldX+entity.solidArea.x;
				entity.solidArea.y=entity.worldY+entity.solidArea.y;
				
				//Get the player solid area position
				gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
				gp.player.solidArea.y=gp.player.worldY+gp.player.solidArea.y;
				
				//switch 
				switch(entity.direction) { 
				case "up":
					entity.solidArea.y-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
							entity.collisionOn=true;
					}///end if
					break;
				case "down":
					entity.solidArea.y+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn=true;
					}///end if
					break;
				case "left":
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
							entity.collisionOn=true;
					}///end if
					break;
				case "right":
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.player.solidArea)) {
							entity.collisionOn=true;
							
					}///end if
					break;
					
				}//end switch
			}//end if
	entity.solidArea.x=entity.solidAreaDefaultX;
	entity.solidArea.y=entity.solidAreaDefaultY;
	gp.player.solidArea.x=gp.player.solidAreaDefaultX;
	gp.player.solidArea.y=gp.player.solidAreaDefaultY;
}//end checkPlayer method
}//end CollisionChecker
