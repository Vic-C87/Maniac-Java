package item;

import utilities.Vec2Int;

public class Item 
{
	public String myName;
	public EItem myImage;
	public Vec2Int myPosition;
	public boolean myHaveCollider = false;
	
	public Item()
	{
		myName = "";
		myImage = EItem.none;
		myPosition = new Vec2Int();
	}
	
	public Item(Item aCopy)
	{
		myName = aCopy.myName;
		myImage = aCopy.myImage;
		myPosition = aCopy.myPosition;
		myHaveCollider = aCopy.myHaveCollider;
	}
	
	public Item(String aName, EItem anItemImage)
	{
		myName = aName;
		myImage = anItemImage;
		myPosition = new Vec2Int();
	}
}
