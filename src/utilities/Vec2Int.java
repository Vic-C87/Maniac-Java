package utilities;

public class Vec2Int 
{
	public int X;
	public int Y;
	
	public Vec2Int()
	{
		X = 0;
		Y = 0;
	}
	
	public Vec2Int(int anX, int aY)
	{
		X = anX;
		Y = aY;
	}
	
	public Vec2Int(Vec2Int aCopy)
	{
		X = aCopy.X;
		Y = aCopy.Y;
	}
	
	public void add(Vec2Int aVec2Int)
	{
		X += aVec2Int.X;
		Y += aVec2Int.Y;
	}
	
	public void add(int anX, int aY)
	{
		X += anX;
		Y += aY;
	}
	
	public void subtract(Vec2Int aVec2Int)
	{
		X -= aVec2Int.X;
		Y -= aVec2Int.Y;
	}
	
	public void subtract(int anX, int aY)
	{
		X -= anX;
		Y -= aY;
	}
	
	public void multiply(int aFactor)
	{
		X *= aFactor;
		Y *= aFactor;
	}
	
	public void divide(int aDenominator)
	{
		X /= aDenominator;
		Y /= aDenominator;
	}
	
	public void set(Vec2Int aVec2Int)
	{
		X = aVec2Int.X;
		Y = aVec2Int.Y;
	}
	
	public void set(int anX, int aY)
	{
		X = anX;
		Y = aY;
	}
}
