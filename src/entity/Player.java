package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.InputManager;
import main.AssetManager;
import interactable.CollisionResults;
import utilities.Vec2Int;

public class Player extends Entity 
{
	int myTileSize;
	Vec2Int myScreenCenter;
	InputManager myInputManager;
	AssetManager myAssetManager;
	
	int myHalfTileSize;
	
	public Player(InputManager anInputManager, AssetManager anAssetManager, Vec2Int aScreenCenter, int aTileSize)
	{
		myInputManager = anInputManager;
		myAssetManager = anAssetManager;
		myScreenCenter = aScreenCenter;
		myTileSize = aTileSize;
		setDefaultValues();
		loadPlayerImage();
	}
	
	public void setDefaultValues()
	{
		myPosition = new Vec2Int(myScreenCenter);
		mySpeed = 4;
		myDirection = EEntityDirection.Idle;
		myHalfTileSize = myTileSize/2;
		myColliderPosition = new Vec2Int(myHalfTileSize, myHalfTileSize);
		myColliderSize = new Vec2Int(myHalfTileSize/2, myHalfTileSize/2);
	}
	
	public void loadPlayerImage()
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
		
		CollisionResults results = myAssetManager.checkCollision(myInputManager.getDirection(), myColliderPosition, myColliderSize, mySpeed);
		
		move(results.aDirection());
		
		if (mySpriteCounter > 10 )
		{
			mySpriteSwitch = !mySpriteSwitch;
			mySpriteCounter = 0;
		}
		
		myColliderPosition.X = myHalfTileSize;
		myColliderPosition.Y = myHalfTileSize;
	}
	
	void move(EEntityDirection aDirection)
	{
		myDirection = aDirection;
		
		switch (myDirection)
		{
		case Up:
			myPosition.Y -= mySpeed;
			mySpriteCounter++;
			break;
		case Down:
			myPosition.Y += mySpeed;
			mySpriteCounter++;
			break;
		case Left:
			myPosition.X -= mySpeed;
			mySpriteCounter++;
			break;
		case Right:
			myPosition.X += mySpeed;
			mySpriteCounter++;
			break;
		case Idle:
			mySpriteCounter = 0;
			break;
			
		default:
			myDirection = EEntityDirection.Idle;
			mySpriteCounter = 0;
			break;
		}
	}
	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		
		switch(myDirection)
		{
		case EEntityDirection.Idle:
			image = myIdle;
			break;
		case EEntityDirection.Up:
			image = mySpriteSwitch ? myUp1 : myUp2;
			break;
		case EEntityDirection.Down:
			image = mySpriteSwitch ? myDown1 : myDown2;
			break;
		case EEntityDirection.Left:
			image = mySpriteSwitch ? myLeft1 : myLeft2;
			break;
		case EEntityDirection.Right:
			image = mySpriteSwitch ? myRight1 : myRight2;
			break;
		}
			
		g2.drawImage(image, myScreenCenter.X, myScreenCenter.Y, myTileSize, myTileSize, null);		
	}
}