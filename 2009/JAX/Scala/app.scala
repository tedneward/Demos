import scala.reflect.{BeanProperty => BP}

class Person(@BP var firstName : String, @BP var lastName : String)
{
    override def toString() = firstName + " " + lastName
}


object App
{
    import java.math.{BigInteger => BInt}
    import BInt._

    def x = 10
    
    def foo = {
        val x = 10;
        System.out.println(x)
    }
    
    def factorial(x : BInt) : BInt =
        if (x == ZERO) ONE
        else x multiply factorial (x subtract ONE)
        
    def periodicTimer(theAction : () => Unit) =
    {
        while (true)
        {
            Thread.sleep(1 * 1000)
            theAction()
        }
    }
    
    //def ping() : Unit = 

    def main(args : Array[String]) : Unit =
    {
        System.out.println("Hello, Scala")
        System.out.println("factorial(100) = " + factorial(new BInt("100")))
        
        val p = new Person("Walter", "Smith")
        System.out.println(p.firstName)
        
        periodicTimer(() => { System.out.println("PING") } )
    }
}
















