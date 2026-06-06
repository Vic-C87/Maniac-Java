package main;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Maniac");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		GameManager gameManager = new GameManager();
		
		window.add(gameManager);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gameManager.startGameThread();
	}
}
