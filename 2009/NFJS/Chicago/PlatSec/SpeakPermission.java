import java.security.*;

public class SpeakPermission extends BasicPermission
{
	private String act;
	public SpeakPermission(String action)
	{
		super(action);
		
		this.act = action;
	}
	
	public boolean implies(Permission other)
	{
		if (other instanceof SpeakPermission)
		{
			SpeakPermission requested = (SpeakPermission)other;
			if (this.act.equals("*") ||
				this.act.equals(requested.act) )
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void p(boolean result)
	{ System.out.println(result); }
	public static void main(String[] args)
	{
		SpeakPermission p1 = new SpeakPermission("insult");
		SpeakPermission p2 = new SpeakPermission("insult");
		SpeakPermission p3 = new SpeakPermission("*");
		SpeakPermission p4 = new SpeakPermission("compliment");
		
		p(p1.implies(p2));  // true
		p(p2.implies(p1));  // true
		p(p1.implies(p3));  // false
		p(p3.implies(p1));  // true
		p(p1.implies(p4));  // false
		p(p4.implies(p1));  // false
	}
}