import java.math._, BigInteger._

trait Persistent
{
    def persist : () => Unit ;
}

trait Meditatable
{
    def meditate =
    {
        Thread sleep 24 * 7 * 60 * 60 * 1000
        System.out.println("You need consulting, and here's my business card")
    }
    def pronounce
}

import scala.reflect.BeanProperty

class Person(@BeanProperty var firstName : String,
             @BeanProperty var lastName : String,
             @BeanProperty var age : Int)
    extends Runnable with Meditatable with java.io.Serializable
{
    System.out.println("Person!")

    def run =
        System.out.println("Run me!")

    def pronounce =
        System.out.println("Your code is the sux")

    override def toString =
        "[Person--firstName="+firstName+" lastName=" + lastName + " age=" + age + "]"
}

object Utility
{
    def doSomething =
        System.out.println("Howdy, all!")
        
    def !&^#@*@(x : BigInteger) : BigInteger =
        if (x == ZERO) ONE
        else x multiply !&^#@*@(x subtract ONE)
}

object App
{
    def main(args : Array[String]) =
    {
        System.out.println("Hello, Hamlet, from scala!")
        Utility.doSomething
        System.out.println(Utility.!&^#@*@(new BigInteger("100")))
        val mike = new Person("Michael", "Nygard", 25)
        System.out.println(mike)
        
        
        for (val i <- 1 to 10; (i%2)==0; val j <- 1 to 10; (j % 2) == 0)
        {
            System.out.println(i * j)
        }
    }
}





























