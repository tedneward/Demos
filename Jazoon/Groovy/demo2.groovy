
public class Person {
    String firstName
    String lastName
    int age
    Person spouse
    
    def void plus(Person other)
    {
        if (other)
        {
            other?.spouse = this
            this.spouse = other
    
            other?.lastName = this.lastName
            
            System.out.println("I now present $firstName and " +
                "$spouse.firstName $spouse.lastName!")    
        }
    }
}

caucus = [ [first: "Justin", last:"Timberlake", age:27],
           [first: "Brittney", last:"Spears", age:27],
           [first: "George", last:"Clooney", age:50]
         ]
people = [ ]
for (Map e in caucus)
{
    Person p = new Person(firstName: e.first, lastName: e.last, age:e.age)
    people.add(p)
}
people[0] + people[1]
people[2] + null

