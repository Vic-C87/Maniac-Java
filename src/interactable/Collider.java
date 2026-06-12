package interactable;

import entity.EEntityDirection;
import entity.Entity;
import utilities.Vec2Int;

//	Make into a record??? !!!
public class Collider
{
	private Vec2Int mySize;
	
	private Entity myOwner = null;
	
	public Collider(Entity anOwner, int aSize)
	{
		myOwner = anOwner;
		mySize = new Vec2Int(aSize/2, aSize/2);
	}
	
	public int getCollisionPosition(EEntityDirection aDirection, int aStep)
	{
		int position = 999999;
		switch (aDirection)
		{
		case Up:
			position = myOwner.getPosition().Y - mySize.Y - aStep;
			 break;
		case Down:
			position = myOwner.getPosition().Y + mySize.Y + aStep;
			break;
		case Left:
			position = myOwner.getPosition().X - mySize.X - aStep;
			break;
		case Right:
			position = myOwner.getPosition().X + mySize.X + aStep;
			break;		
			default:
				break;
		}
		return position;
	}
	
	public Vec2Int getGridPosition()
	{
		return myOwner.getGridPosition();
	}
	
	public Vec2Int getSize()
	{
		return mySize;
	}
}
