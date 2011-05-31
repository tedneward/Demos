class Person
{
    def firstName
    def age
    def lastName

    def doSomething(map)
    {
        println("map = $map")
        println("map.a = $map.a")
    }

    Person(fn, ln, a)
    {
        firstName = fn
        lastName = ln
        age = a
    }
}


Person p = new Person("Keynan", "Pratt", 15)
println "Everybody say hi to $p.firstName $p.lastName $p.age"

Person p2 = new Person("Max", "Sun", 44)
p2.lastName = "Pratt"
p2.setLastName("Williams")
println "p2 = $p2"

p2.doSomething(a:10, x:50, y:"Foobar", b:20, z:22.7)
