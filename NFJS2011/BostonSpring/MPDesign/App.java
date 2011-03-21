import fj.*;

class Rectangle
{
    public int height;
    public int width;
    
    public static final Equal<Rectangle> rectEq =
        Equal.p2Equal(Equal.intEqual,Equal.intEqual)
            .comap(toP2);    
            
    public static final F<Rectangle, P2<Integer,Integer>> toP2 =
        new F<Rectangle, P2<Integer, Integer>>() {
                public P2<Integer, Integer> f(final Rectangle r) {
                    return P.p(r.height, r.width);
                }
            };

    public boolean equals(Object other) {
        return other instanceof Rectangle && rectEq.eq((Rectangle)other);
    }
            
    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public void setHeight(int val) { height = val; }
    public void setWidth(int val) { width = val; }
    
    public String toString() { return "h:" + height + " w:" + width; }

}

class Square extends Rectangle
{
    public void setHeight(int val) { height = width = val; }
    public void setWidth(int val) { height = width = val; }
}
    
public class App
{
    public static void main(String[] args)
    {
        Rectangle r = new Square(); r.setHeight(2);
        transform(r, 2);
        System.out.println(r);

        F<String, Integer> stoi = new F<String, Integer>() {
            public Integer f(final String s) {
                return Integer.parseInt(s);
            }
        };
        F<Integer, String> f = new F<Integer,String>() {
            public String f(final Integer i) {
                return i.toString();
            }
        };
        F<String, String> stos = stoi.andThen(f);
        
        // F<Integer,String> f = #{ i -> i.toString(); };
        System.out.println(f.f(12));
    }
    
    public static void transform(Rectangle r, int multiplier)
    {
        r.setHeight(r.getHeight() * multiplier);
        r.setWidth(r.getWidth() * multiplier);
    }
}