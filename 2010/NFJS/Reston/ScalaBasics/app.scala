object Hello
{
    def x = 5
    
    def main(args : Array[String]) : Unit =
    {
        var b = 
            if (args.length == 5) 
                1
            else
                ()
        System.out.println(b)
        
        def testMe =
        {
            System.out.println(x)
        }
        testMe
        val one : Int = 5
        System.out.println(5.toString())
        
        val five : List[Int] = List(1, 2, 3, 4, 5)
        val printIt : (Any => Unit) = (i : Any) => System.out.println("Found an " + i + "!")
        five.foreach( printIt )
        
        for (j <- five; k <- five; l <- Array(6, 7, 8, 9, 10); if ((k%2)==1) )
            System.out.println("j*k*l = " + j*k*l)
            
        val cities = Map( "London" -> "England" )

        val uberlist = five ++ List(6, 7, 8, 9)
        uberlist.foreach (printIt)
        
        System.out.println(five)
        System.out.println("Howdy!")
        5
    }
}







