import java.dyn.*;

class App
{
    public static void howdy()
    {
        System.out.println("Howdy!");
    }
}

public class MH
{
    public static void main(String[] args)
        throws Throwable
    {
        MethodHandle mh_h = 
            MethodHandles.lookup().
                findStatic(App.class, "howdy",
                    MethodType.methodType(void.class));
        mh_h.invokeExact();
    }
}