package interactable;


import entity.Player;
import utilities.Debug;
import utilities.Vec2Int;

public class Door implements IInteractable
{
	private int myTileSize;
	private EInteractable myType;
	private int myImageIndex;
	private Vec2Int myGridPosition;
	private Vec2Int myTargetPosition;
	private String myTargetMap;
	
	private boolean myIsLocked = false;
	
	public Door(EInteractable aType, int anImageIndex,Vec2Int aGridPosition, Vec2Int aTargetPosition, String aTargetMap, int aTileSize)
	{
		myTileSize = aTileSize;
		myType = aType;
		myImageIndex = anImageIndex;
		myGridPosition = aGridPosition;
		myTargetPosition = aTargetPosition;
		myTargetMap = aTargetMap;
	}
	
	@Override
	public void use(Player aPlayer)
	{
		// check if this map is target map
		// otherwise get target map
		// set player position to myTargetPosition
		aPlayer.teleport(myTargetPosition);
		Debug.msg(myTargetPosition, "Door used, leading to: ");
		
	}

	@Override
	public EInteractable getType()
	{
		return myType;
	}

	@Override
	public int getImageIndex()
	{
		return myImageIndex;
	}

}
