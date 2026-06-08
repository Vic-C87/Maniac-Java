package utilities;

public class Debug
{
	public static void msg(int anIntDebug, String aVariableName)
	{
		System.out.println(aVariableName  + ": "+ anIntDebug);
	}
	
	public static void msg(Vec2Int aVec2IntDebug)
	{
		
		System.out.println(aVec2IntDebug.toString()  + ": "+ aVec2IntDebug.X + " ; " + aVec2IntDebug.Y);	
	}
	
	public static void msg(Vec2Int aVec2IntDebug, String aVariableName)
	{
		
		System.out.println(aVariableName  + ": "+ aVec2IntDebug.X + " ; " + aVec2IntDebug.Y);	
	}
	
	public static void msg(String aDebugMessage)
	{
		System.out.println(aDebugMessage);
	}
}
