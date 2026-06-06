package tile;

public enum ETile 
{
	grass(0),
	wall(1),
	water(2),
	none(3);
	
	public int myID;
	
	private ETile(int anID)
	{
		myID = anID;
	}
	
	public static ETile fromID(int anID)
	{
		for (ETile tile: values())
		{
			if (tile.myID == anID)
			{
				return tile;
			}
		}
		return null;
	}
}