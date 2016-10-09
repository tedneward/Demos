public class Point
{
	public Point(int x, int y)
	{
		init(x, y);
	}
	
	private void init(int xx, int yy)
	{
		if (xx < 0) xx = 0;
		if (yy < 0) yy = 0;
		this.x = xx;
		this.y = yy;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	private int x;
	private int y;
}