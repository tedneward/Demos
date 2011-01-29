import System.out._

class Person(val firstName : String, 
             val lastName : String, 
             var age : Int)
{
    override def toString() : String =
        "[Person: " + firstName + "," + lastName + "," + age + "]"
}

class DoSomething
{
    def apply(arg : String) =
        println(arg)
}

class Heartbeat(msg : String)
{
    def ping(millis : Int, act : (String) => Unit) =  //Function[]
    {
        while (true)
        {
            act(msg)
            Thread.sleep(millis * 1000)
        }
    }
}

object Utils
{
    def intersperse[A](v : A, l : List[A]) : List[A] =
    {
        def intersp[A](v : A, l : List[A]) : List[A] =
            l match
            {
                case List() => v :: Nil
                case hd :: tl => v :: hd :: intersp(v, tl)
            }
        intersp(v, l)
    }
}

class ListExt[A](list : List[A])
{
    def intersperse(v : A) : List[A] =
        Utils.intersperse[A](v, list)
}

object App
{
    implicit def list2listext[A](l : List[A]) : ListExt[A] =
        new ListExt(l)

    def main(args : Array[String]) =
    {
        val ds = new DoSomething
        ds("Howdy")
        //val h = new Heartbeat("PINGYRINGYDINGYDOO")
        //h.ping(1, (hbm) => println("Heartbeat says: " + hbm) ) 
        
        val persons = List(
            new Person("John", "Stevenson", 39),
            new Person("Dan", "Oprescu", 31),
            new Person("Henrik", "Roos", 34),
            new Person("Maria", "Bortes", 32),
            new Person("Alice", "Lottini", 34),
            new Person("Ted", "Neward", 39),
            new Person("Michael", "Neward", 17),
            new Person("Matthew", "Neward", 11)
        )
        persons.foreach( (p) => println(p) )
        val lastNames = persons.map( (p) => p.lastName )
        lastNames.foreach( (l) => println(l) )
        val totalAges = persons.foldLeft( 0 ) { (sum, p) => sum + p.age }
        println(totalAges)
        
        val personsXML = persons.foldLeft("<people>") { 
            (xml, p) => xml + "<person>" + p.firstName + "</person>"
        } + "</people>"
        println(personsXML)

        persons.filter( (p) => p.age >= 21 )
            .foreach( (p) => println("Have a beer, " + p + "!") )
            
        val intPersons = Utils.intersperse("PINGYRINGYDINGYDOO", lastNames)
        intPersons.foreach( println )
        
        val intPersons2 = lastNames.intersperse("BOOYAH")
        intPersons2.foreach(println)
    }
}











