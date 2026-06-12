package item;

import utilities.Vec2Int;

public class Item 
{
	private String myName;
	private EItem myImage;
	private Vec2Int myPosition;
	private boolean myHaveCollider = false;
	
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
