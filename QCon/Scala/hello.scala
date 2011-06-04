class Person(fn:String, ln:String, a:Int)
{
  @scala.reflect.BeanProperty
  var firstName = fn

  @scala.reflect.BeanProperty
  var lastName = ln

  @scala.reflect.BeanProperty
  var age = a
}


class Ola(message : String)
{
  @scala.reflect.BeanProperty
  var msg = message

  def annoyingGuy() =
  {
    println("Let's see if " + message + " is bound in.")
  }

  println("HelloWorld has been constructed")
  println("HelloWorld has a message for you " + message)
}

object HelloWorld
{
  def test(item : Int) : Boolean =
  {
    (item % 2 == 0)
  }
  def main(args : Array[String]) : Unit =
  {
    println("Hello, scala!")
    val h = new Ola("Ola Bini says hi too!")

    var cont = true
    for (val i <- 1 to 10; test(i); cont)
    {
      println("Counting :" + i)
      cont = if i < 9
    }


    println("This is just to make Scala (and Ola) happy")
  }
}




