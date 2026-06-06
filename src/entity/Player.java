package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GameManager;
import main.InputManager;
import main.AssetManager;
import utilities.Vec2Int;

public class Player extends Entity 
{
	GameManager myGameManager;
	InputManager myInputManager;
	AssetManager myAssetManager;
	
	int myHalfTileSize;
	
	public Player(GameManager aGameManager, InputManager anInputManager, AssetManager anAssetManager)
	{
		myGameManager = aGameManager;
		myInputManager = anInputManager;
		myAssetManager = anAssetManager;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues()
	{
		myPosition = new Vec2Int(myGameManager.myScreenCenter);
		mySpeed = 4;
		myDirection = EEntityState.Idle;
		myHalfTileSize = myGameManager.myTileSize/2;
		myColliderPosition = new Vec2Int(myHalfTileSize, myHalfTileSize);
		myColliderSize = new Vec2Int(myHalfTileSize/2, myHalfTileSize/2);
	}
	
	public void getPlayerImage()
	{
		try
		{
			myIdle = ImageIO.read(getClass().getResourceAsStream("/player/character_robot_idle.png"));
			myUp1 = ImageIO.read(getClass().getResourceAsStream("/player/robot_up_1.png"));
			myUp2 = ImageIO.read(getClass().getResourceAsStream("/player/robot_up_2.png"));
			myDown1 = ImageIO.read(getClass().getResourceAsStream("/player/robot_down_1.png"));
			myDown2 = ImageIO.read(getClass().getResourceAsStream("/player/robot_down_2.png"));
			myLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/robot_left_1.png"));
			myLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/robot_left_2.png"));
			myRight1 = ImageIO.read(getClass().getResourceAsStream("/player/robot_right_1.png"));
			myRight2 = ImageIO.read(getClass().getResourceAsStream("/player/robot_right_2.png"));
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		myColliderPosition.add(myPosition);
		if (myInputManager.myUpPressed)
		{
			if (!myAssetManager.checkCollision(myColliderPosition.X, myColliderPosition.Y - mySpeed, myColliderSize))
			{
				myDirection = EEntityState.Up;
				myPosition.Y -= mySpeed;
				mySpriteCounter++;
			}
		}
		else if (myInputManager.myDownPressed)
		{
			if (!myAssetManager.checkCollision(myColliderPosition.X, myColliderPosition.Y + mySpeed, myColliderSize))
			{
				myDirection = EEntityState.Down;
				myPosition.Y += mySpeed;
				mySpriteCounter++;
			}
		}
		else if (myInputManager.myLeftPressed)
		{
			if (!myAssetManager.checkCollision(myColliderPosition.X - mySpeed, myColliderPosition.Y, myColliderSize))
			{
				myDirection = EEntityState.Left;
				myPosition.X -= mySpeed;
				mySpriteCounter++;
			}
		}
		else if (myInputManager.myRightPressed)
		{
			if (!myAssetManager.checkCollision(myColliderPosition.X + mySpeed, myColliderPosition.Y, myColliderSize))
			{
				myDirection = EEntityState.Right;
				myPosition.X += mySpeed;
				mySpriteCounter++;
			}
		}
		else
		{
			myDirection = EEntityState.Idle;
			mySpriteCounter = 0;
		}
		
		if (mySpriteCounter > 10 )
		{
			mySpriteSwitch = !mySpriteSwitch;
			mySpriteCounter = 0;
		}
		
		myColliderPosition.X = myHalfTileSize;
		myColliderPosition.Y = myHalfTileSize;
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		
		switch(myDirection)
		{
		case EEntityState.Idle:
			image = myIdle;
			break;
		case EEntityState.Up:
			image = mySpriteSwitch ? myUp1 : myUp2;
			break;
		case EEntityState.Down:
			image = mySpriteSwitch ? myDown1 : myDown2;
			break;
		case EEntityState.Left:
			image = mySpriteSwitch ? myLeft1 : myLeft2;
			break;
		case EEntityState.Right:
			image = mySpriteSwitch ? myRight1 : myRight2;
			break;
		}
			
		g2.drawImage(image, myGameManager.myScreenCenter.X, myGameManager.myScreenCenter.Y, myGameManager.myTileSize, myGameManager.myTileSize, null);		
	}
}