class Person(var firstName : String, var lastName : String, var age : Int)
{
    def fullName = firstName + " " + lastName
    
    override def toString = 
        toStringImpl()
        
    var toStringImpl : () => String =
        () => firstName + " " + lastName + " (" + age + " yrs old)"
}

object App
{
    def periodicTimer(time : Int, action : () => Unit ) =
    {
        while (true)
        {
            Thread.sleep(time * 1000)
            action()
        }
    }
    
    def main(args : Array[String]) : Unit =
    {
        System.out.println("Howdy")
        //def timerWithoutTime = periodicTimer _
        //System.out.println(timerWithoutTime)
        //timerWithoutTime(3)
        //periodicTimer(3, () => System.out.println("PING"))
        val p = new Person("Scott", "Davis", 40)
        System.out.println(p)
        
        p.toStringImpl = () => "Whee"
        System.out.println(p)
        ()
    }
}



