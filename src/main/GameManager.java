package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import utilities.Vec2Int;

public class GameManager extends JPanel implements Runnable 
{
	private static final long serialVersionUID = -3171413117350253492L;
	final int myOriginalTileSize = 16;
	final int myScale = 3;
	
	public final int myTileSize = myOriginalTileSize * myScale;
	public final int myMaxScreenCol = 16;
	public final int myMaxScreenRow = 12;
	public final int myScreenWidth = myTileSize * myMaxScreenCol;
	public final int myScreenHeight = myTileSize * myMaxScreenRow;
	public final int myFPS = 60;
	public final Vec2Int myScreenCenter;
	
	InputManager myInputManager = new InputManager();
	Thread myGameThread;

	AssetManager myAssetManager;
	public Player myPlayer;
	
	public GameManager() 
	{
		this.setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(myInputManager);
		this.setFocusable(true);
		myScreenCenter = new Vec2Int(myScreenWidth/2 - myTileSize/2, myScreenHeight/2 - myTileSize/2);
		myAssetManager = new AssetManager(this);
		myPlayer = new Player(this, myInputManager, myAssetManager);
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
