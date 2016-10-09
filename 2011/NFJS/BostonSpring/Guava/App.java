import java.util.*;
import com.google.common.base.*;
import com.google.common.collect.*;

public class App
{
    public static final Joiner NAME_JOINER = Joiner.on(", ");
    public static final Function<Person, String> TO_LAST =
        new Function<Person, String>() {
            public String apply(Person p) { return p.getLastName(); }
        };
    public static final Predicate<Person> OF_AGE = 
        new Predicate<Person>() {
            public boolean apply(Person p) { return p.getAge() >= 21; }
        };
    public static final Predicate<Person> NEWARDS = 
        new Predicate<Person>() {
            public boolean apply(Person p) { return p.getLastName().equals("Neward"); }
        };
        
    public static void main(String[] args)
    {
        List<Person> people = Arrays.asList(
            new Person("Len", "Packer", 38),
            new Person("Hari", "Ram", 30),
            new Person("Joanna", "Lu", 25),
            new Person("Ted", "Neward", 40),
            new Person("Neal", "Ford", 43),
            new Person("Mark", "Richards", 102),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        );
        System.out.println("Welcome to " + NAME_JOINER.join(people));
        
        Iterable<String> names = Splitter.on(",").on(";").split("Ted, Neal; Mark, Ken; Jay");
        for (String s : names)
            System.out.println(s);

        List<String> lastNames = new ArrayList<String>();
        for (Person p : people)
            lastNames.add(TO_LAST.apply(p));
            
        for (Person p : Iterables.filter(Iterables.filter(people, OF_AGE), NEWARDS))
            System.out.println("Have a beer, " + p.getFirstName());
    }
}















