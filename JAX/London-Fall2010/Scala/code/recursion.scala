import System.out._
import java.math.{BigInteger => BInt}, BInt._

def factorial (n : BInt) : BInt =
    if (n == ZERO) ONE
    else n multiply factorial (n subtract ONE)

if (args.length > 0)
    println(factorial(new BInt(args(0))))
else
    println("Usage: factorial.scala inputNum")




