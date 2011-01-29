import java.util.*;
import example.service.*;

public class App
{
	private static ServiceLoader<Fooable> fooLoader =
		ServiceLoader.load(Fooable.class);
		
	public static void main(String[] args)
	{
		for (Fooable f : fooLoader)
			f.execute();
	}
}