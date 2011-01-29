import java.io.*;
import java.security.*;

public class Util
{
	public static void doSomething()
	{
		// Check to see if callers have permission
		//if (System.getSecurityManager() != null)
		//	System.getSecurityManager().checkPermission(new RuntimePermission("insult"));
		AccessController.checkPermission(new SpeakPermission("insult"));
		
		String insult = null;
		
		insult = AccessController.doPrivileged(new PrivilegedAction<String>() {
			public String run() {
				BufferedReader br = null;
				try
				{
					FileReader fr = new FileReader("../insults.txt");
					br = new BufferedReader(fr);
					
					String r = br.readLine();
					br.close();
					return r;
				}
				catch (IOException ioEx)
				{
					ioEx.printStackTrace();
				}
				return "Default insult: You ugly";
			}
		});
		
		System.out.println(insult);
	}
}
