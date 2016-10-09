import java.io.*;
import java.security.*;

public class Util
{
  public static void doSomething()
  {
	AccessController.checkPermission(new SpeakPermission("insult"));  // succeeds or throws ACP
	//if (System.getSecurityManager() != null)
	//	System.getSecurityManager().checkPermission(new RuntimePermission("insult"));

	String insult = 
	  AccessController.doPrivileged(new PrivilegedAction<String>() {
		  public String run() 
		  {
			try
			{
				FileReader fr = new FileReader("../insults.txt");
				BufferedReader br = new BufferedReader(fr);
				String insult = br.readLine();
				br.close();
				fr.close();
				return insult;
			}
			catch (IOException ioEx)
			{
				ioEx.printStackTrace();
			}
			return "Default insult";
		  }
	    });	
	System.out.println(insult);
  }
}