import com.google.common.base.*;

public class PNApp
{
    public static void main(String[] args)
    {
        for (String arg : args)
        {
            if (validate(arg))
                System.out.println("Good");
            else
                System.out.println("Bad");
        }
    }
    
    public static final CharMatcher THREE_DIGITS = CharMatcher.JAVA_DIGIT
            .and(CharMatcher.JAVA_DIGIT)
            .and(CharMatcher.JAVA_DIGIT);
            
    public static final CharMatcher DASH = CharMatcher.is('-');
    
    public static final CharMatcher FOUR_DIGITS = THREE_DIGITS
            .and(CharMatcher.JAVA_DIGIT);
            
    public static final CharMatcher PHONE = THREE_DIGITS.and(DASH).and(FOUR_DIGITS);
    
    public static boolean validate(String in)
    {
        return (FOUR_DIGITS.matchesAllOf(in));
    }
}

