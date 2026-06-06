package entity;

import java.awt.image.BufferedImage;

import utilities.Vec2Int;

public class Entity 
{
	public int mySpeed;
	public Vec2Int myPosition;
	public BufferedImage myIdle, myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2;
	public EEntityState myDirection;
	
	public int mySpriteCounter = 0;
	public boolean mySpriteSwitch = false;
	
	public Vec2Int myColliderPosition;
	public Vec2Int myColliderSize;
}
