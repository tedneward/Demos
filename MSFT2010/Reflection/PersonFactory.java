import java.lang.reflect.*;

public class PersonFactory
{
    public static Personable newPerson(String f, String l)
    {
        final Person target = new Person(f, l);
        
        InvocationHandler tracer = new InvocationHandler() {
            public Object invoke(Object proxy, Method m, Object[] args)
                throws Throwable
            {
                System.out.println("Method invoked: " + m);
                
                Object results = m.invoke(target, args);
                
                System.out.println("Method returning");
                return results;
            }
        };
        
        Personable result = (Personable)
            Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[] { Personable.class },
                tracer
            );
        return result;
    }
}




