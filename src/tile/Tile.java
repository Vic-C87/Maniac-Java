package tile;

import item.Item;
import utilities.Vec2Int;

public class Tile 
{
	public ETile myImage;
	public Vec2Int myPosition;
	public boolean myHaveCollider = false;	
	public boolean myHaveItem = false;
	public Item myItem;
	
	public Tile()
	{
		myImage = ETile.none;
		myPosition = new Vec2Int();
		myItem = new Item();
	}
	
	public Tile(Tile aCopy)
	{
		myImage = aCopy.myImage;
		myPosition = aCopy.myPosition;
		myHaveCollider = aCopy.myHaveCollider;
		myHaveItem = aCopy.myHaveItem;
		myItem = aCopy.myItem;
	}
	
	public void addItem(Item anItem)
	{
		myItem = anItem;
		myHaveItem = true;
	}
	
	public void removeItem()
	{
		myItem = null;
		myHaveItem = false;
	}
}
