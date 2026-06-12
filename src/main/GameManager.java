package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.Map;
import utilities.Vec2Int;

public class GameManager extends JPanel implements Runnable 
{
	private static final long serialVersionUID = -3171413117350253492L;
	
	private final int myTileSize;
	private final int myMaxScreenCol = 16;
	private final int myMaxScreenRow = 9;
	private final int myScreenWidth;
	private final int myScreenHeight;
	private final int myFPS = 60;
	private final Vec2Int myScreenCenter;
	
	private InputManager myInputManager;
	private Thread myGameThread;

	private Map myMap;
	private AssetManager myAssetManager;
	private CollisionManager myCollisionManager;
	private Player myPlayer;
	
	public GameManager(int aWidth, int aHeight) 
	{
		myScreenWidth = aWidth;
		myScreenHeight = aHeight;
		myTileSize = aWidth / myMaxScreenCol;
		myInputManager = new InputManager(this);
		this.setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(myInputManager);
		this.addMouseListener(myInputManager);
		this.setFocusable(true);
		myScreenCenter = new Vec2Int(myScreenWidth/2 - myTileSize/2, myScreenHeight/2 - myTileSize/2);
		myMap = new Map(myTileSize);
		myCollisionManager = new CollisionManager(myMap, myTileSize);
		myPlayer = new Player(myCollisionManager, myInputManager, myScreenCenter, myTileSize);
		myAssetManager = new AssetManager(myPlayer, myMap, myScreenCenter,myTileSize);
		myCollisionManager.setup();
	}
	
	public void startGameThread() 
	{
		myGameThread = new Thread(this);
		myGameThread.start();
	}
	
	@Override
	public void run() 
	{
		double drawInterval = 1000000000 / myFPS;
		
		long currentTime = System.nanoTime();
		long lastTime = currentTime;
		long deltaTime = currentTime - lastTime;
		
		while (myGameThread != null) 
		{
			currentTime = System.nanoTime();
			deltaTime = currentTime - lastTime;
			if (deltaTime >= drawInterval)
			{
				update();
				
				repaint();
				lastTime = currentTime;
			}	
		}	
	}
	
	public void update()
	{
		myPlayer.update();
	}
	
	public void paintComponent(Graphics g) 
	{		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Draw Tiles
		myAssetManager.draw(g2);
		
		//Draw Objects
		
		//Draw Player
		myPlayer.draw(g2);
		
		//Draw UI
		
		g2.dispose();	
	}
}
