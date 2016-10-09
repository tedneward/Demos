import java.lang.reflect.*;

class Point
{
    public Point(int x, int y) { init(x, y); }
    
    public String toString() { return "(" + x + "," + y + ")"; }
    
    private void init(int xx, int yy) { this.x = xx; this.y = yy; }
    
    private int x; private int y;
}


public class App
{
    public static void main(String[] args)
    {
        Personable p = PersonFactory.newPerson("Scott", "Davis");
        System.out.println(p.getFirstName());
    }
    
    public static void oldMain(String[] args)
        throws Exception
    {
        Point p = new Point(12, 12);
        System.out.println(p);
        
        Class pClass = p.getClass();
        System.out.println(pClass);
        
        for (Field f : pClass.getDeclaredFields())
            System.out.println(f);
            
        for (Method m : pClass.getDeclaredMethods())
            System.out.println(m);
            
        for (Constructor c : pClass.getDeclaredConstructors())
            System.out.println(c);
            
        Method ts = pClass.getMethod("toString", new Class[] { });
        Object result = ts.invoke(p);
        System.out.println(result);
        
        Method init = 
            pClass.getDeclaredMethod("init", 
                new Class[] { int.class, int.class });
        init.setAccessible(true);
        Object result2 = init.invoke(p, new Object[] { 24, 24 });
        System.out.println(result2);
        
        Field xField =
            pClass.getDeclaredField("x");
        xField.setAccessible(true);
        xField.set(p, 56);
        System.out.println(p);
    }
}













