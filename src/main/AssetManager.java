

package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Player;
import interactable.Door;
import interactable.EInteractable;
import tile.Map;
import tile.Tile;
import utilities.Vec2Int;


public class AssetManager 
{
	private Player myPlayer;
	private Map myMap;
	private ArrayList<Tile> myLoadedTiles;
	private int myTileSize;
	private int myHalfTileSize;
	private Vec2Int myScreenCenter;
	private Vec2Int myMapWorldSize;
	
	private Door aTestDoor;
	
	public AssetManager(Player aPlayer, Map aMap, Vec2Int aScreenCenter, int aTileSize)
	{
		myPlayer = aPlayer;
		myMap = aMap;
		myScreenCenter = new Vec2Int(aScreenCenter);
		myTileSize = aTileSize;
		myHalfTileSize = myTileSize / 2;
		myLoadedTiles = new ArrayList<Tile>();
		loadTileImages("/data/tileData.txt");
		loadMap("/maps/maptest2.txt");
		myMapWorldSize = myMap.getWorldSize();
		aTestDoor = new Door(EInteractable.Door, 0, new Vec2Int(3,3), new Vec2Int(7,5), "", myTileSize);
		myMap.placeItem(3, 1, aTestDoor);//DRAW DOOR!!!
	}
	
	private void loadTileImages(String aFilePath)
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
				Tile tile = new Tile(myScreenCenter, image, colliderTile, index, myTileSize);
				myLoadedTiles.add(tile);			
			}
		} catch (Exception e)
		{
			e.getStackTrace();
		}
	}
	
	private void loadMap(String aFilePath)
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
	
	public void draw(Graphics2D g2)
	{
		Vec2Int playerPosition = myPlayer.getPosition();
		int northBound = playerPosition.Y - myScreenCenter.Y - myTileSize *2;
		int southBound = playerPosition.Y + myScreenCenter.Y + myTileSize *2;
		int westBound = playerPosition.X - myScreenCenter.X - myTileSize *2;
		int eastBound = playerPosition.X + myScreenCenter.X + myTileSize *2;
		int y = 0;
		int x = 0;
		
		for (int yPos = 0; yPos < myMapWorldSize.Y; yPos += myTileSize)
		{
			x = 0;
			for (int xPos = 0; xPos < myMapWorldSize.X; xPos += myTileSize)
			{
				if (xPos > westBound && xPos < eastBound && yPos > northBound && yPos < southBound )
				{
					myMap.getTile(x, y).draw(g2, playerPosition);
				}
				x++;
			}
			y++;
		}
	}
}