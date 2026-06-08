package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.EEntityDirection;
import interactable.CollisionResults;
import interactable.IInteractable;
import tile.Map;
import tile.Tile;
import utilities.Vec2Int;


public class AssetManager 
{
	GameManager myGameManager;
	Map myMap;
	ArrayList<Tile> myLoadedTiles;
	int myTileSize;
	Vec2Int myScreenCenter;
	Vec2Int myScreenHalfSize;
	
	public AssetManager(GameManager aGameManager, int aTileSize)
	{
		myGameManager = aGameManager;
		
		myLoadedTiles = new ArrayList<Tile>();
		myMap = new Map();
		loadTileImages("/data/tileData.txt");
		loadMap("/maps/maptest2.txt");
		myTileSize = aTileSize;
		myScreenCenter = new Vec2Int(myGameManager.myScreenCenter);
		myScreenHalfSize = new Vec2Int(myGameManager.myScreenWidth / 2, myGameManager.myScreenHeight / 2);
	}
	
	void loadTileImages(String aFilePath)
	{
		try
		{
			InputStream inputStream = getClass().getResourceAsStream(aFilePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			BufferedImage image;
			
			String line;
			boolean colliderTile;
			int index;
			
			while ((line = bufferedReader.readLine()) != null)
			{
				image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + line));
				line = bufferedReader.readLine();
				colliderTile = Boolean.parseBoolean(line);
				index = myLoadedTiles.size();
				Tile tile = new Tile(image, colliderTile, index);
				myLoadedTiles.add(tile);			
			}
		} catch (Exception e)
		{
			e.getStackTrace();
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
			
			myMap.setMap(xSize, ySize, title);
			
			line = bufferedReader.readLine();
			int y = 0;
			while (y < ySize)
			{
				String numbers[] = line.split(" ");
				
				for (int x = 0; x < xSize; x++)
				{
					int value = Integer.parseInt(numbers[x]);
					myMap.setTile(x, y, myLoadedTiles.get(value));
					myMap.getTile(x, y).setGridPosition(x, y, myTileSize);
				}
				y++;
				line = bufferedReader.readLine();
			}			
		} catch (Exception e)
		{
			e.getStackTrace();
		}
	}
		
	public CollisionResults checkCollision(EEntityDirection aDirection, Vec2Int aPosition, Vec2Int aColliderSize, int aSpeed)
	{
		EEntityDirection direction = EEntityDirection.Idle;
		IInteractable interactable = null;
		int gridX = aPosition.X / myTileSize;
		int gridY = aPosition.Y / myTileSize;
		switch (aDirection)
		{
		case Up:
			int yUp = aPosition.Y - aSpeed;
			gridY = yUp / myTileSize;
			if (yUp >= 0 && !myMap.getTile(gridX, gridY).haveCollider()) 
			{
				direction = EEntityDirection.Up;
			}
			break;
		case Down:
			int yDown = aPosition.Y + aSpeed;
			int yMax = myMap.getSize().Y * myTileSize;
			gridY = yDown / myTileSize;
			if (yDown < yMax && !myMap.getTile(gridX, gridY).haveCollider()) 
			{
				direction = EEntityDirection.Down;		
			}
			break;
		case Left:
			int xLeft = aPosition.X - aSpeed;
			gridX = xLeft / myTileSize;
			if (xLeft >= 0 && !myMap.getTile(gridX, gridY).haveCollider()) 
			{
				direction = EEntityDirection.Left;
			}
			break;
		case Right:
			int xRight = aPosition.X + aSpeed;
			int xMax = myMap.getSize().X * myTileSize;
			gridX = xRight / myTileSize;
			if (xRight < xMax && !myMap.getTile(gridX, gridY).haveCollider()) 
			{
				direction = EEntityDirection.Right;
			}
			break;
		default:
			break;		
		}
		
		if (myMap.getTile(gridX, gridY).haveInteractable())
		{
			direction = EEntityDirection.Idle;
			interactable = myMap.getTile(gridX, gridY).getInteractable();
		}
		
		return new CollisionResults(direction, interactable);		
	}
	
	public void draw(Graphics2D g2)
	{
		Vec2Int playerPosition = myGameManager.getPlayerPosition();
		int northBound = playerPosition.Y - myScreenCenter.Y - myTileSize;
		int southBound = playerPosition.Y + myScreenCenter.Y + myTileSize;
		int westBound = playerPosition.X - myScreenCenter.X - myTileSize;
		int eastBound = playerPosition.X + myScreenCenter.X + myTileSize;
		int yMax = myMap.getSize().Y * myTileSize;
		int xMax = myMap.getSize().X * myTileSize;
		int y = 0;
		int x = 0;
		
		for (int yPos = 0; yPos < yMax; yPos += myTileSize)
		{
			x = 0;
			for (int xPos = 0; xPos < xMax; xPos += myTileSize)
			{
				if (xPos > westBound && xPos < eastBound && yPos > northBound && yPos < southBound )
				{
					g2.drawImage(myMap.getTile(x, y).getImage(), xPos + myScreenCenter.X - playerPosition.X, yPos + myScreenCenter.Y - playerPosition.Y, myTileSize, myTileSize, null);
//					
//					if (myMap.getTile(x, y).haveInteractable())
//					{
//						g2.drawImage(myMap.getTile(x, y).getInteractable().getImageIndex(), xPos + myScreenCenter.X - playerPosition.X, yPos + myScreenCenter.Y - playerPosition.Y, myTileSize, myTileSize, null);
//					}
				}
				x++;
			}
			y++;
		}
	}
}