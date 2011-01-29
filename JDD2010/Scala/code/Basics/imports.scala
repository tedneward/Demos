import System.out._

println("Hello")

import java.math.{BigInteger => BInt}
import BInt._

def countTo(n : BInt) =
{
  def count(i : BInt) : Unit =
    if (i.min(n) == i) {
      println(i)
      count(i add ONE)
    }
  count(ONE)
}
countTo(new BInt("5"))


/* Same as:

import java.math.BigInteger
import BigInteger._

def countTo(n : BigInteger) =
{
  def count(i : BigInteger) : Unit =
    if (i.min(n) == i) {
      println(i)
      count(i add ONE)
    }
  count(ONE)
}

countTo(new BigInteger("5"))

 */
 
 
 


