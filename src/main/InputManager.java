package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.EEntityDirection;
import utilities.Vec2Int;

public class InputManager implements KeyListener, MouseListener
{
	EEntityDirection myDirection;
	Vec2Int myMousePosition = new Vec2Int();
	GameManager myGameManager;
	
	public InputManager(GameManager aGameManager)
	{
		myDirection = EEntityDirection.Idle;
		myGameManager = aGameManager;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) 
		{
			myDirection = EEntityDirection.Up;
		}
		if (code == KeyEvent.VK_S) 
		{
			myDirection = EEntityDirection.Down;
		}
		if (code == KeyEvent.VK_A) 
		{
			myDirection = EEntityDirection.Left;
		}
		if (code == KeyEvent.VK_D) 
		{
			myDirection = EEntityDirection.Right;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) 
		{
			myDirection = EEntityDirection.Idle;
		}
		if (code == KeyEvent.VK_S) 
		{
			myDirection = EEntityDirection.Idle;
		}
		if (code == KeyEvent.VK_A) 
		{
			myDirection = EEntityDirection.Idle;
		}
		if (code == KeyEvent.VK_D) 
		{
			myDirection = EEntityDirection.Idle;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		myMousePosition.set(e.getX(), e.getY());
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			//myGameManager.OnLeftMouseClick(myMousePosition);
		}
		
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			//myGameManager.OnRightMouseClick(myMousePosition);
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public EEntityDirection getDirection()
	{
		return myDirection;
	}
}
