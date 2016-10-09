import java.math.BigInteger, BigInteger._
import System.out._

class Person(val firstName : String, val lastName : String, val age : Int)
{
    def doYourJob() =
        println("I'm working here, boss, I'm working!")

    def apply() =
        println("I'm working here, boss, I'm working!")
}

val p = new Person("Ted", "Neward", 40)
p.doYourJob()
p()




class EnronInt
{
    var value : BigInteger = new BigInteger("0")
    
    def +(other : EnronInt) =
    {
        val result = new EnronInt()
        result.value = (value add other.value) multiply (new BigInteger("2"))
        result
    }
}

object EnronInt
{
    def +(lhs : BigInteger, rhs : EnronInt) : EnronInt =
        new EnronInt
    def +(lhs : EnronInt, rhs : BigInteger) : EnronInt =
}

val ei = new EnronInt()
ei.value = new BigInteger("10")
val res = ei + ei
println(res.value)




class Person
{
    var firstName : String = ""
    var lastName : String = ""
    var age : Int = 0
    var languages : List[String] = Nil
}

object Person
{
    def apply(firstName : String, lastName : String) : Person =
    {
        val r = new Person()
        r.firstName = firstName
        r.lastName = lastName
        r
    }
    def apply(firstName : String, lastName : String, age : Int) : Person =
    {
        val r = Person(firstName, lastName)
        r.age = age
        r
    }
}

