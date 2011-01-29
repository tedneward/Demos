import System.out._

package com
{
    package tedneward
    {
        class Nifty
        {
            private[tedneward] def intellectualProperty = 42
            def saySomething = println("This is a nifty class")
        }
    }
}

trait MyComparable[T]
{
    def compareTo(other : T) : Int
    def < (other : T) : Boolean =
        this.compareTo(other) < 0
    def > (other : T) : Boolean =
        this.compareTo(other) > 0
    def <= (other : T) : Boolean =
        this.compareTo(other) > 0
    def >= (other : T) : Boolean =
        this.compareTo(other) < 0
    def <==> (other : T) : Int =
        this.compareTo(other)
}

import scala.reflect._

class PersonPOJO(@BeanProperty val firstName : String, 
                 @BeanProperty val lastName : String, 
                 @BeanProperty val age : Int)



class Person(@BeanProperty val firstName : String, 
             @BeanProperty val lastName : String, 
             @BeanProperty var age : Int)
    extends MyComparable[Person]
{
    private val hidden = "You can't see me"
    
    def this(name : String, age : Int) =
        this(name, "", age)

    def compareTo(other : Person) =
        firstName compareTo (other firstName)
    
    val fullName = firstName + " " + lastName
    
    def fullName2 =
        firstName + " " + lastName

    override def toString() : String =
        "[Person: " + firstName + "," + lastName + "," + age + "]"
    
    def toXML() =
        <person xmlns="com.tedneward.mynamespace">
            <firstName>{firstName}</firstName>
            <lastName>{lastName}</lastName>
            <age>{age}</age>
        </person>
}

class Student(firstName : String, lastName : String, 
              age : Int, val subject : String)
    extends Person(firstName, lastName, age)
{
    override def compareTo(other : Person) =
        other match
        {
            case (s : Student) =>
                firstName compareTo (other firstName)
            case _ =>
                super.compareTo(other)
        }

    override def toString() : String =
        "[Student: " + super.toString() + " " + subject + "]"
}

object App
{
    def main(args : Array[String]) =
    {
        val p = new Person("Ted", "Neward", 39)
        val p2 = new Person("Charlotte", "Neward", 39)
        val s = new Student("Michael", "Neward", 17, "Video games")
        println(p)
        println(s)

        println(p compareTo p2)
        
        if (p > p2)
            println("p > p2")
            
        println(p <==> p2)
        
        p.age =(40)
    }
}











