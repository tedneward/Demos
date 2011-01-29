import System.out._

class Person(firstName : String, lastName : String, age : Int)
{
    def sayHello() =
        println(firstName + " says hello!")
}

val p = new Person("Ted", "Neward", 40)
p.sayHello()
//println(p.lastName)  // ERROR



class Person2(val firstName : String, val lastName : String, val age : Int)
{
    def sayHello() =
        println(firstName + " says hello!")
}

val p2 = new Person2("Ted", "Neward", 40)
p2.sayHello()
println(p2.lastName)
//p2.age = p2.age + 1  // ERROR



class Person3(var firstName : String, var lastName : String, var age : Int)
{
    def sayHello() =
        println(firstName + " says hello!")
}

val p3 = new Person3("Ted", "Neward", 40)
p3.sayHello()
println(p3.lastName)
p3.age = p3.age + 1  // ERROR
println(p3.age)



class Person4(val firstName : String, val lastName : String, val age : Int)
{
    println("Constructing a Person4 with " + firstName)
}
val p4 = new Person4("Ted", "Neward", 40)



class Person5(val firstName : String, val lastName : String, val age : Int)
{
    def this(fn : String, ln : String) =
        this(fn, ln, 0)
}
val p5 = new Person5("Ted", "Neward")
println(p5.age)


