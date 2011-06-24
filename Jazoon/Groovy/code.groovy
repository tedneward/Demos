class Person
{
    String firstName
    String lastName
    int age
    
    def plus(Person rhs)
    {
        this.lastName = rhs.lastName
        this
    }

    void set(java.util.Map params)
    {
        for (Map.Entry e in params)
        {
            if (e.key == "firstName")
                firstName = e.value
            else if (e.key == "lastName")
                lastName = e.value
            else if (e.key == "age")
                age = e.value
        }
    }

    String toString()
    {
        return "[Person: " + firstName + " " + lastName + " (" + age + " years old)]"
    }
}

def marry(Person p1, Person p2, Closure pronunciation)
{
    println("$p1.firstName, do you take $p2.firstName?")
    println("$p2.firstName, do you take $p1.firstName?")
    
    pronunciation(p1, p2)
    
    p2.lastName = p1.lastName
    println("Insert interesting sermon here")
}
def pronounce(minister, husband, wife)
{
    message = """
I, $minister.lastName, pronounce you, $husband.firstName and you, $wife.firstName,
to be husband and wife. You may now kiss the bride, throw the rice, and go away.
"""
    println(message)
}

Person minister = new Person(firstName:"Reverend", lastName:"Killjoy", age:65)

//def pronunciation = { m, p1, p2 -> pronounce(m, p1, p2) }
//pronunciation.curry(minister)

Person aaron = new Person(firstName:"Aaron", lastName:"Bigulia", age:25)
Person brunhilda = new Person(firstName:"Brunhilda", lastName:"Stadtlemeyer", age:21)
marry(aaron, brunhilda, { println("You are now married. Please give me money.") })

File.eachLine { line -> println("Found a line: $line") }






println("==================================================")


people = [ [ firstName : "Ted", lastName: "Neward", age : 37 ],
           [ firstName : "Amanda", lastName: "Laucher", age : 27],
           [ firstName : "Damien", lastName: "Facciola", age : 12] ]
println(people.getClass())
println(people.class)

for (Map e in people)
{
    Person p = new Person(age: e.age, firstName: e.firstName, lastName: e.lastName)
    
    if (p.firstName == "Amanda")
        p.set([lastName : "Neward"])
    
    String message = """
Hey, it's cool to be a Person when your name is $p.firstName $p.lastName
and you're old enough to drink beer because when futbol games
are on you really want beer to drown your sorrows when some
$p.age-yearold kicks the ball into his own goal.
"""
    println(message)
}

Person p1 = new Person(firstName: "Ted", lastName: "Neward", age:37)
Person p2 = new Person(firstName: "Amanda", lastName: "Lauchner", age:27)
p2 = p2 + p1
System.out.println(p2)