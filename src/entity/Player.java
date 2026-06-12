package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.InputManager;
import main.CollisionManager;
import interactable.Collider;
import interactable.CollisionResult;
import interactable.Door;
import interactable.EInteractable;
import utilities.Vec2Int;

public class Player extends Entity 
{
	private Vec2Int myScreenCenter;
	private InputManager myInputManager;
		
	public Player(CollisionManager aCollisionManager, InputManager anInputManager, Vec2Int aScreenCenter, int aTileSize)
	{
		super(aCollisionManager);
		myInputManager = anInputManager;
		myScreenCenter = aScreenCenter;
		myTileSize = aTileSize;
		setDefaultValues();
		loadPlayerImage();
	}
	
	private void setDefaultValues()
	{
		myPosition = new Vec2Int(myScreenCenter);
		myPosition.X += myTileSize / 2;
		myPosition.Y += myTileSize / 2;
		myGridPosition = new Vec2Int();
		mySpeed = 4;
		myDirection = EEntityDirection.Idle;
		myCollider = new Collider(this, myTileSize / 2);
	}
	
	private void loadPlayerImage()
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
		CollisionResult results = myCollisionManager.checkCollision(myInputManager.getDirection(), mySpeed, myCollider);

		move(results.aDirection());			
		if (results.anInteractable() != null)
		{
			EInteractable type = results.anInteractable().getType();
			
			switch (type)
			{
			case Door:
				Door door = (Door)results.anInteractable();
				door.use(this);
				break;
			default:
				break;
			}
		}
		
		if (mySpriteCounter > 10 )
		{
			mySpriteSwitch = !mySpriteSwitch;
			mySpriteCounter = 0;
		}
	}
	
	private void move(EEntityDirection aDirection)
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
	
	public void teleport(Vec2Int aTargetPosition)
	{
		myPosition.X = aTargetPosition.X * myTileSize;
		myPosition.Y = aTargetPosition.Y * myTileSize;
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
		g2.drawRect(myScreenCenter.X, myScreenCenter.Y, myTileSize, myTileSize);
		g2.drawRect(myScreenCenter.X + myTileSize/4, myScreenCenter.Y + myTileSize/4, myCollider.getSize().X * 2, myCollider.getSize().Y * 2);

	}
}