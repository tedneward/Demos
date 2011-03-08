import java.util.*;
import com.google.common.collect.*;
import static com.google.common.base.*;

public class App
{
    public static void main(String[] args)
    {
        Person p = new Person("Doug", "Hawley", 29);
        System.out.println(p);
        System.out.println(p.hashCode());
        
        Person p2 = new Person(null, null, 12);
        
        Function add5 = new Function<Integer, Integer>() {
            public Integer apply(Integer op) {
                return op + 5;
            }
        };
        
        int x = 12;
        int y = add5.apply(x);  // int y = x + 12;
        
        List<Person> persons = Arrays.asList(
            new Person("Doug", "Hawley", 29),
            new Person("Ted", "Neward", 40),
            new Person("Michael", "Neward", 17),
            new Person("Neal", "Ford", 45),
            new Person("Mark", "Richards", 102)
        );
        for (Person p : persons)
            if (p.getAge() >= 21)
                System.out.println("Have a beer!");
                
        Predicate<Person> olderThan21 = new Predicate<Person>() {
            public boolean apply(Person p) { return p.getAge() >= 21; }
        };
        Predicate<Person> newards = new Predicate<Person>() {
            public boolean apply(Person p) { return p.getLastName().equals("Neward"); }
        };
        
        for (Person p : Iterables.filter(persons, and(not(isNull), and(olderThan21, newards))))
            System.out.println("Have a beer!");
            
        Set<String> USStates = ImmutableSet.of("AK", "AL", "HI", "CA", "MI", "MO", "MA", "ME", "MY");
    }
}


