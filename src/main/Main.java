package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		JFrame window = new JFrame("Maniac");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setUndecorated(true);

		GameManager gameManager = new GameManager(screenSize.width, screenSize.height);
		
		window.add(gameManager);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gameManager.startGameThread();
	}
}
