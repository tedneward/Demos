
object Hello
{
    def greeting(name : String) : String =
        if (name == "Mohammad") "Hello Mo" 
        else "I greet you formally, " + name
        
    def sumList(xs : List[Int]) : Int =
        xs match
        {
            case List() => 0
            case h :: t => h + sumList(t)
        }
/*
        if (xs.length == 0)
            0
        else
            xs.head + sumList(xs.tail)*/
        
    def even(n : Int) : Boolean = 
        if ((n % 2) == 0) true else false
        
    def main(args : Array[String]) : Unit =
    {
        val bill = ("Bill", "Pfeiffer", 44)  // Tuple3[String,String,Int]
        val billsFirstName = bill._1
        val bill2 = ("Ted", "Neward", 39)  // Tuple3[String,String,String]
        val tedsFirstName =
            bill2 match
            {
                case ("Ted", ln, _) =>
                    System.out.println("You're a Ted!")
                case (fn, _, _) =>
                    System.out.println(fn)
            }
        
        val names = Array("Fred", "Tom", "Barney")
        
        val cities = Map("Paris" -> "France", 
            "London" -> "England")
        val countryWhereTheyTalkFunny = cities("London")
        
        even(2)
        
        //System.out.println(greeting("Mohammad"))
        val xs = List(1, 2, 3, 7, 8, 9)
        val ys = Array(1, 2, 3, 4, 5, 6)
        val mults = 
            for (x <- xs; if (x % 2 == 0); y <- ys; if (y % 2 == 0))
                yield (x, y, x * y)
        for (m <- mults)
            System.out.println(m)

        val longNames = 
            for (p <- names if p.length > 3) yield p
        
        for (n <- longNames)
            System.out.println(n)
            
        val ns = List(1,2,3)
        System.out.println(sumList(ns))

        ()
    }
}
