package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import item.EItem;
import item.Item;
import tile.ETile;
import tile.Map;
import tile.Tile;
import utilities.Vec2Int;

public class AssetManager 
{
	GameManager myGameManager;
	Map myMap;
	BufferedImage[] myLoadedTiles;
	BufferedImage[] myLoadedItems;
	int myTileSize;
	Vec2Int myScreenCenter;
	Vec2Int myScreenHalfSize;
	Item[] myItems;
	
	public AssetManager(GameManager aGameManager)
	{
		myGameManager = aGameManager;
		
		myLoadedTiles = new BufferedImage[10];
		myLoadedItems = new BufferedImage[10];
		myMap = new Map();
		myItems = new Item[10];
		loadTileImages();
		loadMap("/maps/world.txt");
		loadItemAssets();
		placeItems();
		myTileSize = myGameManager.myTileSize;
		myScreenCenter = new Vec2Int(myGameManager.myScreenCenter);
		myScreenHalfSize = new Vec2Int(myGameManager.myScreenWidth / 2, myGameManager.myScreenHeight / 2);
	}
	
	void loadTileImages()
	{
		try 
		{
			myLoadedTiles[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			myLoadedTiles[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			myLoadedTiles[2] = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	void loadItemAssets()
	{
		try 
		{
			myLoadedItems[0] = ImageIO.read(getClass().getResourceAsStream("/items/Door.png"));
			myItems[0] = new Item("Door", EItem.door);
			myItems[0].myHaveCollider = true;

			myLoadedItems[1] = ImageIO.read(getClass().getResourceAsStream("/items/Key.png"));
			myItems[1] = new Item("Key", EItem.key);
			myLoadedItems[2] = ImageIO.read(getClass().getResourceAsStream("/items/Axe.png"));
			myItems[2] = new Item("Axe", EItem.axe);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	void loadMap(String aFilePath)
	{
		try
		{
			InputStream inputStream = getClass().getResourceAsStream(aFilePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line = bufferedReader.readLine();
			String title = line;
			line = bufferedReader.readLine();
			String size[] = line.split("x");
			int xSize = Integer.parseInt(size[0]);
			int ySize = Integer.parseInt(size[1]);
			
			myMap.myTitle = title;
			myMap.mySize = new Vec2Int(xSize, ySize);
			myMap.myMap = new Tile[xSize][ySize];
			
			line = bufferedReader.readLine();
			int y = 0;
			while (y < ySize)
			{
				String numbers[] = line.split("\t");
				
				for (int x = 0; x < xSize; x++)
				{
					int value = Integer.parseInt(numbers[x]);
					myMap.myMap[x][y] = new Tile();
					myMap.myMap[x][y].myImage = ETile.fromID(value);
					myMap.myMap[x][y].myPosition = new Vec2Int(x, y);
					myMap.myMap[x][y].myHaveCollider = value % 2 == 0 ? false : true;
				}
				y++;
				line = bufferedReader.readLine();
			}			
		} catch (Exception e)
		{
			e.getStackTrace();
		}
	}
	
	public void placeItems()
	{
		myMap.placeItem(10, 10, myItems[1]);
	}
	
	public boolean checkCollision(Vec2Int aPosition)
	{
		int x = aPosition.X / myTileSize;
		int y = aPosition.Y / myTileSize;
		
		if (myMap.myMap[x][y].myHaveCollider)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkCollision(Vec2Int aPosition, Vec2Int aSize)
	{
		int xWest = (aPosition.X - aSize.X);
		int yNorth = (aPosition.Y - aSize.Y);
		int xEast = (aPosition.X + aSize.X);
		int ySouth = (aPosition.Y + aSize.Y);
		
		if (checkCollision(xWest, yNorth) || checkCollision(xEast, yNorth) || checkCollision(xWest, ySouth) || checkCollision(xEast, ySouth))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean checkCollision(int anX, int aY, Vec2Int aSize)
	{
		int xWest = (anX - aSize.X);
		int yNorth = (aY - aSize.Y);
		int xEast = (anX + aSize.X);
		int ySouth = (aY + aSize.Y);
		
		if (checkCollision(xWest, yNorth) || checkCollision(xEast, yNorth) || checkCollision(xWest, ySouth) || checkCollision(xEast, ySouth))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean checkCollision(int anX, int aY)
	{
		int x = anX / myTileSize;
		int y = aY / myTileSize;
		
		if (x >= myMap.mySize.X || x < 0 || y >= myMap.mySize.Y || y < 0)
		{
			return true;
		}
		else if (myMap.myMap[x][y].myHaveCollider || myMap.myMap[x][y].myHaveItem)
		{
			
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g2)
	{
		int screenBuffer = 2;
		int playerPositionX = myGameManager.myPlayer.myPosition.X;
		int playerPositionY = myGameManager.myPlayer.myPosition.Y;
		int worldPosX = 0;
		int worldPosY = 0;
		int northBound = ((playerPositionY - myScreenCenter.Y) / myTileSize) - screenBuffer;
		int southBound = ((playerPositionY + myScreenCenter.Y) / myTileSize) + screenBuffer;
		int westBound = ((playerPositionX - myScreenCenter.X) / myTileSize) - screenBuffer;
		int eastBound = ((playerPositionX + myScreenCenter.X) / myTileSize) + screenBuffer;
		
		for (int y = 0; y < myMap.mySize.Y; y++)
		{
			for (int x = 0; x < myMap.mySize.X; x++)
			{
				worldPosY = y * myTileSize + myScreenCenter.Y;
				if (x > westBound && x < eastBound && y > northBound && y < southBound )
				{
					worldPosX = x * myTileSize + myScreenCenter.X;
					g2.drawImage(myLoadedTiles[myMap.myMap[x][y].myImage.myID], worldPosX - playerPositionX, worldPosY - playerPositionY, myTileSize, myTileSize, null);
					if (myMap.myMap[x][y].myHaveItem)
					{
						g2.drawImage(myLoadedItems[myMap.myMap[x][y].myItem.myImage.myID], worldPosX - playerPositionX, worldPosY - playerPositionY, myTileSize, myTileSize, null);
						//System.out
					}
				}
			}
		}
	}
}