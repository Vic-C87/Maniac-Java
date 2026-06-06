package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener
{
	public boolean myUpPressed, myDownPressed, myLeftPressed, myRightPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			myUpPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			myDownPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			myLeftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			myRightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			myUpPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			myDownPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			myLeftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			myRightPressed = false;
		}
	}
}
