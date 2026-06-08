package entity;

import java.awt.image.BufferedImage;

import utilities.Vec2Int;

public class Entity 
{
	protected int mySpeed;
	protected Vec2Int myPosition;
	protected BufferedImage myIdle, myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2;
	protected EEntityDirection myDirection;
	
	protected int mySpriteCounter = 0;
	protected boolean mySpriteSwitch = false;
	
	protected Vec2Int myColliderPosition;
	protected Vec2Int myColliderSize;
	
	public Vec2Int getPosition()
	{
		return myPosition;
	}
}
