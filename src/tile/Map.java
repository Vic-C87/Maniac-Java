package tile;

import java.awt.image.BufferedImage;

import interactable.IInteractable;
import utilities.Vec2Int;

public class Map 
{
	private int myTileSize;
	private String myTitle;
	private Vec2Int myGridSize;
	private Vec2Int myWorldSize;
	private Tile[][] myMap;
	
	public Map(int aTileSize)
	{
		myTileSize = aTileSize;
		myTitle = "None";
		myGridSize = new Vec2Int();
		myWorldSize = new Vec2Int();
	}
	
	public void setMap(int anXSize, int aYSize, String aTitle)
	{
		myTitle = aTitle;
		myMap = new Tile[anXSize][aYSize];
		myGridSize = new Vec2Int(anXSize, aYSize);
		myWorldSize = new Vec2Int(myGridSize.X * myTileSize, myGridSize.Y * myTileSize);
	}
	
	public void setTile(int anX, int aY, Tile aTile)
	{
		myMap[anX][aY] = new Tile(aTile);
	}
	
	public Tile getTile(int anX, int aY)
	{
		return myMap[anX][aY];
	}
	
	public BufferedImage getImage(int anX, int aY)
	{
		return myMap[anX][aY].getImage();
	}
	
	public IInteractable getInteractable(int anX, int aY)
	{
		return myMap[anX][aY].getInteractable();
	}
	
	public boolean getHaveCollider(int anX, int aY)
	{
		return myMap[anX][aY].haveCollider();
	}
	
	public Vec2Int getGridSize()
	{
		return myGridSize;
	}
	
	public Vec2Int getWorldSize()
	{		
		return myWorldSize;
	}
	
	public void placeItem(int anXTile, int aYTile, IInteractable anInteractable)
	{
		myMap[anXTile][aYTile].addInteractable(anInteractable);
	}
}
