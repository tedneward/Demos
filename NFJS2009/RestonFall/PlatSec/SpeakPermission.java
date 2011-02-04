import java.security.*;

public class SpeakPermission extends BasicPermission
{
	private String type;

	public SpeakPermission(String action)
	{
		super(action);
		type = action;
	}
	
	public boolean implies(Permission perm)
	{
		if (perm instanceof SpeakPermission)
		{
			SpeakPermission req = (SpeakPermission)perm;
			if (req.type.equals(this.type))
				return true;
			if (this.type.equals("*"))
				return true;
		}
		
		return false;
	}
}