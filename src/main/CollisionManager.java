package main;

import entity.EEntityDirection;
import interactable.Collider;
import interactable.CollisionResult;
import interactable.IInteractable;
import tile.Map;
import utilities.Vec2Int;

public class CollisionManager
{	
	private int myTileSize;
	private Map myMap;
	private Vec2Int myGridSize;
	private Vec2Int myWorldSize;
	
	public CollisionManager(Map aMap, int aTileSize)
	{
		myTileSize = aTileSize;
		myMap = aMap;
	}
	
	public void setup()
	{
		myGridSize = new Vec2Int(myMap.getGridSize());
		myWorldSize = new Vec2Int(myGridSize.X * myTileSize, myGridSize.Y * myTileSize);
	}
	
	public CollisionResult checkCollision(EEntityDirection aDirection, int aSpeed, Collider aCollider)
	{
		EEntityDirection direction = EEntityDirection.Idle;
		IInteractable interactable = null;
		int gridX = aCollider.getGridPosition().X;
		int gridY = aCollider.getGridPosition().Y;
		int newPosition = aCollider.getCollisionPosition(aDirection, aSpeed);
		switch (aDirection)
		{
		case Up:
			gridY = (newPosition / myTileSize);
			if (newPosition >= 0 && !myMap.getHaveCollider(gridX, gridY)) 
			{
				direction = EEntityDirection.Up;
			}
			break;
		case Down:
			gridY = (newPosition / myTileSize);
			if (newPosition < myWorldSize.Y && !myMap.getHaveCollider(gridX, gridY)) 
			{
				direction = EEntityDirection.Down;
			}
			break;
		case Left:
			gridX = (newPosition / myTileSize);
			if (newPosition >= 0 && !myMap.getHaveCollider(gridX, gridY)) 
			{
				direction = EEntityDirection.Left;
			}
			break;
		case Right:
			gridX = (newPosition / myTileSize);
			if (newPosition < myWorldSize.X && !myMap.getHaveCollider(gridX, gridY)) 
			{
				direction = EEntityDirection.Right;
			}
			break;
		default:		
			
		}	
		
		if (myMap.getTile(gridX, gridY).haveInteractable())
		{
			interactable = myMap.getInteractable(gridX, gridY);
			
		}
		
		return new CollisionResult(direction, interactable);		
	}
}
