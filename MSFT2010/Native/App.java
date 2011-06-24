public class App
{
    static
    {
        System.loadLibrary("App");
    }
    
    public static native void msgbox(String msg);
    
    public static void main(String[] args)
    {
        System.out.println(System.getProperty("java.library.path"));
        
        System.out.println("Entering main");
        msgbox("Hello from Java");
        System.out.println("Exiting main");
    }
}