package tile;

import java.awt.image.BufferedImage;

import interactable.IInteractable;
import utilities.Vec2Int;

public class Tile 
{
	BufferedImage myImage;
	boolean myHaveCollider = false;
	int myIndex;
	
	Vec2Int myGridPosition;
	Vec2Int myWorldPosition;
	
	boolean myHaveInteractable = false;
	IInteractable myInteractable;
	
	public Tile()
	{
		myImage = null;
		myIndex = 999;
		myGridPosition = new Vec2Int();
		myWorldPosition = new Vec2Int();
		myInteractable = null;
	}
	
	public Tile(BufferedImage anImage, Boolean aHaveCollider, int anIndex)
	{
		myImage = anImage;
		myHaveCollider = aHaveCollider;
		myIndex = anIndex;
		myGridPosition = new Vec2Int();
		myWorldPosition = new Vec2Int();
		myInteractable = null;
	}
	
	public Tile(Tile aCopy)
	{
		myImage = aCopy.myImage;
		myHaveCollider = aCopy.myHaveCollider;
		myIndex = aCopy.myIndex;
		myGridPosition = aCopy.myGridPosition;
		myWorldPosition = aCopy.myWorldPosition;
		myHaveInteractable = aCopy.myHaveInteractable;
		myInteractable = aCopy.myInteractable;
	}
	
	public BufferedImage getImage()
	{
		return myImage;
	}
	
	public void setGridPosition(int anX, int aY, int aTileSize)
	{
		myGridPosition.X = anX;
		myGridPosition.Y = aY;
		myWorldPosition.X = anX * aTileSize;
		myWorldPosition.Y = aY * aTileSize;
	}
	
	public boolean haveCollider()
	{
		return myHaveCollider;
	}
	
	public boolean haveInteractable()
	{
		return myHaveInteractable;
	}
	
	public void addInteractable(IInteractable anInteractable)
	{
		myInteractable = anInteractable;
		myHaveInteractable = true;
	}
	
	public IInteractable getInteractable()
	{
		return myInteractable;
	}
	
	public void removeInteractable()
	{
		myInteractable = null;
		myHaveInteractable = false;
	}
}
