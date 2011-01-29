public class App
{
	public static void main(String[] args)
	{
		System.setSecurityManager(new SecurityManager());  // -Djava.security.manager
		
		System.out.println(System.getProperty("java.version"));
		
		System.setProperty("java.version", "1.8");
		System.out.println(System.getProperty("java.version"));
		
		Util.doSomething();
	}
}
