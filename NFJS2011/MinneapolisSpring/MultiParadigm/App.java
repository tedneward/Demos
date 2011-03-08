class Rectangle
{
    public int getW() { return width; }
    public int getH() { return height; }
    public void setW(int val) { width = val; }
    public void setH(int val) { height = val; }
    
    public String toString() { return "{" + width + " x " + height + "}"; }
    
    private int width;
    private int height;
}

class Square extends Rectangle
{
    public Square(int side) { set(side); }
    public void set(int val) { super.setW(val); super.setH(val); }
}

public class App
{
    public static void main(String[] args)
    {
        Rectangle r = new Square();
        r.setW(12);
        r.setH(24);
        doSomethingWithARect(r);
    }
    
    public static void doSomethingWithARect(Rectangle r)
    {
        System.out.println(r);
    }
}
