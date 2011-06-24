package com.tedneward.demo
{
    class ScalaInteger(n : String)
    {
        import java.math.{BigInteger => BInt}
        import BInt._
        
        def this()
        {
            //this("You're kinda twisted, dude....")
            this("0")
        }
        
        def this(n : java.math.BigInteger)
        {
            this(n.toString())
        }
        
        override def toString = bi.toString()
    
        private val bi = new java.math.BigInteger(n)
        
        def multiply (other : ScalaInteger) = new ScalaInteger(bi.multiply(other.bi))
        def * (other : ScalaInteger) = multiply(other)
        def ** (other : ScalaInteger) = multiply(multiply(other))
            
        def subtract (other : ScalaInteger) = new ScalaInteger(bi.subtract(other.bi))
        def - (other : ScalaInteger) = subtract(other)
        
        def $less$eq$eq$greater (other : ScalaInteger) = "Rocky"
//        def <==> = "Rocky"
    }
    object ScalaInteger extends ScalaInteger
    {
        val ZERO = new ScalaInteger("0")
        val ONE = new ScalaInteger("1")

        implicit def intToScalaInteger(va : Int) : ScalaInteger=
            return new ScalaInteger(va.toString())
    }
}

object HelloWorld
{
    import com.tedneward.demo.{ScalaInteger => SInt}, SInt._

    def factorial(x : SInt) : SInt =
        if (x == ZERO)
            ONE
        else
            x multiply factorial(x subtract ONE)
            
    def sumList (xs : List[Int]) : Int =
        xs match
        {
            case h :: t => h + sumList(t)
            case _ => 0
        }

    val rockysAge = 39
    def main(args : Array[String]) : Unit =
    {
        System.out.println("Hello Rocky, you're " + rockysAge)
        
        val f : SInt = 12
        val g : SInt = 12
        System.out.println("f = " + (f * g) )
        System.out.println("f <==> " + (f <==> g))
        System.out.println("f <==> " + (f $less$eq$eq$greater g))
        
        val numList = List(12, 24, 36, 48)
        System.out.println("sum = " + sumList(numList))
    }
}




























