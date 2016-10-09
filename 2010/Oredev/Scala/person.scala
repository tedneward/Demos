import System.out._

import scala.xml._

class Person(val firstName : String, val lastName : String, val age : Int) {
    override def toString() = 
        "Person: " + firstName + " " + lastName + " " + age
}

object App {
    def sumAge(lst : List[Person]) : Int =
        if (lst.isEmpty)
            0
        else
            lst.head.age + sumAge(lst.tail)
            
    def xmlExample = <shopping><bread price="3.50" qty="2">White</bread></shopping>
    
    def main(args : Array[String]) = {
        println("Howdy")
        val p = new Person("Ted", "neward", 39)
        println(p.firstName)
        val p2 = new Person(p.firstName, p.lastName, p.age + 1)
        println(p2.age)
        
        println((xmlExample \ "bread").text)
        
        val peopleList = 
            Nil
//            new Person("Ted", "Neward", 39) :: Nil
//            new Person("Charlotte", "Neward", 38) :: Nil
        println(peopleList)
        val sumAges = peopleList.foldLeft(0) { (accum : Int, p : Person) => accum + p.age }
            //sumAge(peopleList)
        println("Everybody's age added together is " + sumAges)
    }
}



