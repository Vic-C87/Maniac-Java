package tile;

import interactable.IInteractable;
import utilities.Vec2Int;

public class Map 
{
	String myTitle;
	Vec2Int mySize;
	Tile[][] myMap;
	
	public Map()
	{
		myTitle = "None";
		mySize = new Vec2Int();
	}
	
	public Map(String aTitle, int anXSize, int aYSize)
	{
		myTitle = aTitle;
		mySize = new Vec2Int(anXSize, aYSize);
		myMap = new Tile[mySize.X][mySize.Y];
	}
	
	public Map(String aTitle, Vec2Int aSize)
	{
		myTitle = aTitle;
		mySize = aSize;
		myMap = new Tile[mySize.X][mySize.Y];
	}
	
	public Map(int anXSize, int aYSize)
	{
		myTitle = "None";
		mySize = new Vec2Int(anXSize, aYSize);
		myMap = new Tile[mySize.X][mySize.Y];
	}
	
	public Map(Vec2Int aSize)
	{
		myTitle = "None";
		mySize = aSize;
		myMap = new Tile[mySize.X][mySize.Y];
	}
	
	public Map(Tile[][] aMap)
	{
		myTitle = "None";
		mySize = new Vec2Int(aMap[0].length, aMap.length);
		myMap = aMap;
	}
	
	public Map(Map aCopy)
	{
		myTitle = aCopy.myTitle;
		mySize = aCopy.mySize;
		myMap = aCopy.myMap;
	}
	
	public void setMap(int anXSize, int aYSize, String aTitle)
	{
		myTitle = aTitle;
		myMap = new Tile[anXSize][aYSize];
		mySize = new Vec2Int(anXSize, aYSize);
	}
	
	public void setTile(int anX, int aY, Tile aTile)
	{
		myMap[anX][aY] = aTile;
	}
	
	public Tile getTile(int anX, int aY)
	{
		return myMap[anX][aY];
	}
	
	public Vec2Int getSize()
	{
		return mySize;
	}
	
	public void placeItem(int anXTile, int aYTile, IInteractable anInteractable)
	{
		myMap[anXTile][aYTile].addInteractable(anInteractable);
	}
}
