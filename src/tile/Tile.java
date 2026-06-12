package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import interactable.IInteractable;
import utilities.Vec2Int;

public class Tile 
{
	private BufferedImage myImage;
	private boolean myHaveCollider = false;
	private int myIndex;
	private int myTileSize;
	private int myHalfTileSize;
	
	private Vec2Int myGridPosition;
	private Vec2Int myWorldPosition;
	private Vec2Int myScreenCenter;
	
	private boolean myHaveInteractable = false;
	private IInteractable myInteractable;
	
	public Tile()
	{
		myImage = null;
		myIndex = 999;
		myGridPosition = new Vec2Int();
		myWorldPosition = new Vec2Int();
		myScreenCenter = new Vec2Int();
		myInteractable = null;
	}
	
	public Tile(Vec2Int aScreenCenter, BufferedImage anImage, Boolean aHaveCollider, int anIndex, int aTileSize)
	{
		myImage = anImage;
		myHaveCollider = aHaveCollider;
		myIndex = anIndex;
		myTileSize = aTileSize;
		myHalfTileSize = myTileSize / 2;
		myGridPosition = new Vec2Int();
		myWorldPosition = new Vec2Int();
		myScreenCenter = aScreenCenter;
		myInteractable = null;
	}
	
	public Tile(Tile aCopy)
	{
		myImage = aCopy.myImage;
		myHaveCollider = aCopy.myHaveCollider;
		myIndex = aCopy.myIndex;
		myTileSize = aCopy.myTileSize;
		myHalfTileSize = aCopy.myHalfTileSize;
		myGridPosition = new Vec2Int(aCopy.myGridPosition);
		myWorldPosition = new Vec2Int(aCopy.myWorldPosition);
		myScreenCenter = new Vec2Int(aCopy.myScreenCenter);
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
	
	public void draw(Graphics2D g2, Vec2Int aPlayerPosition)
	{
		g2.drawImage(myImage, myWorldPosition.X + myScreenCenter.X - aPlayerPosition.X + myHalfTileSize, myWorldPosition.Y + myScreenCenter.Y - aPlayerPosition.Y + myHalfTileSize, myTileSize, myTileSize, null);
		//DRAW Interactable
	}
}
