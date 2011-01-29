class Rectangle
{
    public Rectangle(int height, int width)
    {
        this.ht = height; this.wd = width;
    }
    
    public int getHeight() { return ht; }
    public int getWidth() { return wd; }
    public void setHeight(int value) { ht = value; }
    public void setWidth(int value) { wd = value; }
    
    public String toString() { return "[" + ht + " x " + wd + "]"; }
    
    private int ht; private int wd;
}
class Square extends Rectangle
{
    public Square(int side)
    {
        super(side, side);
    }
    
    public void setHeight(int value) { super.setHeight(value); super.setWidth(value); }
    public void setWidth(int value) { super.setHeight(value); super.setWidth(value); }
}


public class App
{
    public static void transform(Rectangle r, int n)
    {
        r.setHeight(r.getHeight() * n);
        r.setWidth(r.getWidth() * n);
    }
    public static void main(String[] args)
    {
        Rectangle r = new Square(2);
        System.out.println(r);
        transform(r,2);
        System.out.println(r);
    }
}