import java.math.BigInteger
import BigInteger._

def + (lhs : BigInteger, rhs : BigInteger) = lhs add rhs
def - (lhs : BigInteger, rhs : BigInteger) = lhs subtract rhs
def * (lhs : BigInteger, rhs : BigInteger) = lhs multiply rhs
def / (lhs : BigInteger, rhs : BigInteger) = lhs divide rhs

val out = 
    if (args.length < 3)
        "Usage: scala operators.scala inputNum1 operator inputNum2"
    else
    {
        val lhs = new BigInteger(args(0))
        val rhs = new BigInteger(args(2))
        val result = args(1) match
        {
            case "+" => lhs + rhs
            case "-" => lhs - rhs
            case "*" => lhs * rhs
            case "/" => lhs / rhs
            case _ => "Unrecognized operator"
        }
        result.toString()
    }
System.out.println(out)

