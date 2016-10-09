import com.google.common.base.*;
import com.google.common.collect.*;
import static com.google.common.base.Strings.*;
import static com.google.common.base.Preconditions.*;
import java.util.*;

class Person
{
    public String firstName;
    public String lastName;
    public int age;
    public String socialSecurity;
    
    public Person(String f, String l, int a) { 
        checkArgument(a >= 0, "must be positive: %s", a);
        checkNotNull(f);
        checkNotNull(l);
        
        firstName = f; lastName = l; age = a; 
    }
    
    public int hashCode()
    {
        return Objects.hashCode(firstName, lastName, age, socialSecurity);
    }
    
    public String toString()
    {
        return Objects.toStringHelper(this)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("age", age)
            .toString();
    }
}

public class App
{
    public static final Splitter SPACE_SPLIT = 
        Splitter.on(" ").omitEmptyStrings();
        
    public static final Function<String, Integer> strToInt =
        new Function<String, Integer>() {
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
    public static final Function<Integer, Integer> squareIt =
        new Function<Integer, Integer>() {
            public Integer apply(Integer s) {
                return s * s;
            }
        };

    static class NewardList extends ForwardingList<String>
    {
        public NewardList(List<String> d) { de = d; }
        
        protected List<String> delegate() { return de; }
        
        public boolean add(String elem)
        {
            if (elem.equals("Neward"))
                return super.add(elem);
            else
                return super.add("Neward");
        }
        
        private List<String> de;
    }
        
    public static void main(String[] args)
    {
        Person p = new Person("Jay", "Zimmerman", 104);
        System.out.println(p);
        String message = repeat("Ted rocks!", 100);
        System.out.println(message);
        
        String str = "This   is   a   sample string. I want to count the number of words in it.";
        for (String s : SPACE_SPLIT.split(str))
            System.out.println(s);
            
        String str2 = Joiner.on(",").useForNull("<<NO DATA>>")
            .join("This", "is", "a", null, "sample", "string");
        System.out.println(str2);
        
        CharMatcher phone = CharMatcher.JAVA_DIGIT
            .or(CharMatcher.is('('))
            .or(CharMatcher.is(')'))
            .or(CharMatcher.is('-'));
        String phoneNumber = phone.retainFrom("(555)555-1212$#@!");
        System.out.println(phoneNumber);
        
        System.out.println(strToInt.apply("321"));
        Function squareString = Functions.compose(squareIt, strToInt);
        System.out.println(squareString.apply("3"));

        /*
        List<String> nothingButNewards = Constraints.constrainedList(
            new ArrayList(Arrays.asList("Neward", "Neward")),
            new Constraint<String>() {
                public String checkElement(String cand) {
                    if (cand.equals("Neward"))
                        return cand;
                    throw new IllegalArgumentException("Must be a Neward");
                }
            });
        nothingButNewards.add("Jones");
        */
        NewardList nBN = new NewardList(new ArrayList(Arrays.asList("Neward", "Neward")));  
        nBN.add("Jones");
        System.out.println(nBN);
    }
}




