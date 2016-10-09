public class App
{
  public static void main(String[] args)
	throws Exception
  {
	System.setSecurityManager(new SecurityManager());  // -Djava.security.manager
  
    //System.out.println(System.getProperty("java.version"));
	//System.setProperty("java.version", "1.8");
    //System.out.println(System.getProperty("java.version"));
	//System.getProperties().list(System.out);
	
	Util.doSomething();
	
	java.net.Socket s = new java.net.Socket("www.cnn.com", 80);
	s.getOutputStream();
  }
}