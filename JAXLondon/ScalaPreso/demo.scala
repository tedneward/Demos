import System.out._
import java.math.{BigInteger => BInt}, BInt._
import scala.reflect._

class Person(@BeanProperty val firstName : String, @BeanProperty val lastName : String, 
             @BeanProperty var age : Int)
{
    def <=@=> (other : Person) =
        this.firstName.compareTo(other.firstName)

    override def toString =
        "[Person: " + firstName + " " + lastName + " " + age + "]"
}


object App
{
    def factorial(input : BInt) : BInt = 
        if (input == ZERO) ONE else input multiply factorial(input subtract ONE)
    
    def main(args : Array[String]) : Unit =
    {
        println("Hello, Scala!")
        println(factorial(new BInt("15")))
        val p = new Person("Neal", "Ford", 45)
        println(p.firstName)
        p.age=(46)
        println(p)
        val t = new Person("Ted", "Neward", 39)
        println(p <=@=> t)
    }
}
