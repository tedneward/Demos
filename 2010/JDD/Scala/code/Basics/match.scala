import System.out._


def sumList(xs : List[Int]) : Int =
  xs match
  {
    case List() => 0
    case hd :: tl => hd + sumList(tl)
  }


val numlist = 1 :: 2 :: 3 :: 4 :: Nil

println(sumList(numlist))


def printSomething(o : Any) : Unit=
  o match
  {
    case (firstName : String, lastName : String, age : Int) =>
      println("Tuple: " + firstName + " " + lastName + " is " + age + " years old")
    case (s : String) =>
      println("String: " + s)
    case (i : Int) =>
      println("Int: " + i)
    case (il : List[Int]) =>  // This line generates a warning
      il match
      {
        case List() => ()
        case i :: il => printSomething(i); printSomething(il);
      }
    case _ =>
      println("I have no clue what you just gave me")
  }

printSomething(("Ted", "Neward", 38))
printSomething("Howdy, world!")
printSomething(List(1, 2, 3, 4))



