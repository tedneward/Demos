abstract case class Node(name : String)
{
    def draw()
}
case class Leaf(n : String, op : () => Unit) extends DE(n) {
    override def draw() = op()
}
case class Branch(n : String, des : List[DE]) extends DE(n) {
    override def draw() = des.foreach(d => d.draw())
}

object App
{
    def main(args : Array[String]) =
    {
        val e = 
            Node("Parent",
                List(
                    Leaf("circle", () => System.out.println("Drawing circle")),
                    Leaf("square", () => System.out.println("Drawing square"))))
        e.draw
    }
}