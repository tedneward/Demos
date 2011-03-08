import java.dyn.*;

public class ID
{
    public static void main(String[] args)
    {
        test();
    }
    
    @BootstrapMethod(value=ID.class, name="bootstrapDynamic")
    static void test() throws Throwable 
    {
        InvokeDynamic.baz("baz arg", 2, 3.14);
    }
    private static void printArgs(Object... args) 
    {
        System.out.println(java.util.Arrays.deepToString(args));
    }
    private static final MethodHandle printArgs;
    static 
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class thisClass = lookup.lookupClass();  // (who am I?)
        printArgs = lookup.findStatic(thisClass,
            "printArgs", MethodType.methodType(void.class, Object[].class));
    }
    private static CallSite bootstrapDynamic(Class caller, String name, MethodType type) 
    {
        // ignore caller and name, but match the type:
        return new CallSite(MethodHandles.collectArguments(printArgs, type));
    }
}
