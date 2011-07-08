class Person(@scala.reflect.BeanProperty var firstName : String, 
             @scala.reflect.BeanProperty var lastName : String)
{
}

object HelloWorld
{
  def oncePerSecond(callback : () => Unit) =
  {
    while (true) { callback(); Thread.sleep(1000); }
  }


  def main(args : Array[String]) = {
    System.out.println("Howdy, all!")
    var p = new Person("Joe", "Smith")
    p.setFirstName("Hernan");
    Console.println(p.firstName)
    oncePerSecond(() => Console.println("Time flies when you're being fun(ctional)"))
  }
}