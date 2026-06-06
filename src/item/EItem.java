package item;



public enum EItem 
{
	door(0),
	key(1),
	axe(2),
	none(3);
	
	public int myID;
	
	private EItem(int anID)
	{
		myID = anID;
	}
	
	public static EItem fromID(int anID)
	{
		for (EItem tile: values())
		{
			if (tile.myID == anID)
			{
				return tile;
			}
		}
		return null;
	}
}
