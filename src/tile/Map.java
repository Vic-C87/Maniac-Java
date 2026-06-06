package tile;

import item.Item;
import utilities.Vec2Int;

public class Map 
{
	public String myTitle;
	public Vec2Int mySize;
	public Tile[][] myMap;
	
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
	
	public void placeItem(int anXTile, int aYTile, Item anItem)
	{
		myMap[anXTile][aYTile].addItem(anItem);
	}
}
