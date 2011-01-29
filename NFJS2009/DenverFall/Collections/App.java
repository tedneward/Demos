import java.util.*;

public class App
{
    public static void main(String[] args)
        throws Exception
    {
        List<Person> people = new ArrayList<Person>(Arrays.asList(
            new Person("Carol", "Endarlin", 29),
            new Person("Tammy", "Brown", 29),
            new Person("Chet", "Rebman", 51),
            new Person("Gardner", "Gilman", 57),
            new Person("Bill", "Mark", 15),
            new Person("Michael", "Neward", 16),
            new Person("Matthew", "Neward", 10),
            new Person("Ted", "Neward", 38),
            new Person("Raymond", "Saturn", 108)  // "Get offa my lawn!"
        ));
        //System.out.println(people);
        
        //people.add(new Person("Tom", "Malaher", 46));
        System.out.println(people);
        
        for (String s : CollUtils.take(5, FileUtils.readlines("App.java")))
            System.out.println("==> " + s);
        
        System.out.println("========================");

        List<Person> drinkers = CollUtils.filter(people, new Predicate1<Person>() {
            public boolean apply(Person p) { return p.getAge() >= 21; }
        });
        List<Person> newarddrinkers = CollUtils.filter(drinkers, new Predicate1<Person>() {
            public boolean apply(Person p) { return p.getLastName().equals("Neward"); }
        });
        CollUtils.foreach(newarddrinkers, new Procedure1<Person>() {
            public void apply(Person p) { System.out.println("Have a beer, " + p.getFirstName() + "!"); }
        });
        
        Option<List<Person>> results = CollUtils.find(people, new Predicate1<Person>() {
            public boolean apply(Person p) { return p.getLastName().equals("Neward"); } 
        });
        if (results.isSome())
            CollUtils.foreach(results.get(), new Procedure1<Person>() {
                public void apply(Person p) { System.out.println("Found " + p); }
            });
            
        for (Integer ran : CollUtils.take(20, NumberUtils.random(20)))
            System.out.println(ran);
        
        /*
        List<Person> drinkers = 
            CollUtils.
                filter(people, #(p) { return p.getAge() >= 21 } ).
                foreach(#(p) System.out.println(...));
        */
        
        //for (Person p : people)
        //    if (p.getAge() >= 21)
        //        System.out.println("Have a beer, " + p.getFirstName() + "!");
        
        /*
        SortedSet<Person> pBA = new TreeSet<Person>(Person.BY_AGE);
        pBA.addAll(people);
        SortedSet<Person> drinkers = pBA.tailSet(new Person("", "", 21));
        System.out.println(drinkers);
        for (Person p : drinkers)
            System.out.println("Have a beer, " + p.getFirstName() + "!");
        */
    }
}





