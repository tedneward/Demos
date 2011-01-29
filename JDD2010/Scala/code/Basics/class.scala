import System.out._

trait Comparableator[T] extends Comparable[T]
{
    def > (other : T) = compareTo(other) > 0
    def < (other : T) = compareTo(other) < 0
    def >= (other : T) = compareTo(other) < 0
    def <= (other : T) = compareTo(other) > 0
    
    def <==> (other : T) = compareTo(other)
}

class Person(var firstName : String, val lastName : String, val age : Int)
    extends Comparableator[Person]
{
    override def compareTo(other : Person) = firstName.compareTo(other.firstName)
    
    override def toString =
        "Person: " + firstName + " " + lastName + " " + age
}

object App
{
    def ping(period : Int, operation : () => Unit) =
    {
        while (true)
        {
            Thread.sleep(period * 1000)
            operation()  // apply()
        }
    }
    def printPing() = println("PING")

    def main(args : Array[String]) =
    {
        val p = new Person("Ted", "Neward", 39)
        println(p)
        
        println("Howdy, " + p.firstName + ", would you like to get a vodka?")
        p.firstName = "The speaker formerly known as Ted"
        println(p)
        
        val p2 = new Person("Bill", "Burke", 39)
        println(if (p > p2) "Ted is better than Bill" else "Nah, never happened")        
        
        println(p <==> p2)
        
        val numlist = List(1,2,3,4)
        numlist.foreach( (i) => println(i * 10) )
        
        ping(1, printPing)
    }
}



