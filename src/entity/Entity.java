package entity;

import java.awt.image.BufferedImage;

import interactable.Collider;
import main.CollisionManager;
import utilities.Vec2Int;

public class Entity 
{
	protected int myTileSize;
	protected int mySpeed;
	protected Vec2Int myPosition;
	protected Vec2Int myGridPosition;
	protected BufferedImage myIdle, myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2;
	protected EEntityDirection myDirection;
	
	protected int mySpriteCounter = 0;
	protected boolean mySpriteSwitch = false;
	
	protected Collider myCollider;
	
	protected CollisionManager myCollisionManager;
	
	public Entity(CollisionManager aCollisionManager)
	{
		myCollisionManager = aCollisionManager;
	}
	
	public Vec2Int getPosition()
	{
		return myPosition;
	}
	
	public Vec2Int getGridPosition()
	{
		myGridPosition.X = myPosition.X / myTileSize;
		myGridPosition.Y = myPosition.Y / myTileSize;
		
		return myGridPosition;
	}
}
