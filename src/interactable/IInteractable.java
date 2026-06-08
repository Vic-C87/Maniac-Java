package interactable;

import entity.Player;

public interface IInteractable
{
	public void use(Player aPlayer);
	public EInteractable getType();
	public int getImageIndex();
	
}
