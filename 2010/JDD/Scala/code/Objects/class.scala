import System.out._

class Empty
{
    
}

class Person
{
    val firstName = "Ted"
    def lastName = "Neward"
    var age = 40
    
    def sayHello() =
        println("Hey there, " + this.firstName + " says hi!")
}

val p = new Person()
println(p.firstName)
println(p.lastName)
println(p.age)

println("Happy birthday, Ted!")
p.age = 41
println(p.age)

p.sayHello()
p.sayHello
