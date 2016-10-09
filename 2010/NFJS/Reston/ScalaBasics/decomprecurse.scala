object App
{
    val los = List(1, 2, 3, 4, 5)
    
    def sumList(xs : List[Int]) : Int =
    {
        xs match
        {
            case List() => 0
            case h :: t =>
                h + sumList(t)
        }
    }
    
    def main(args : Array[String]) =
        System.out.println(sumList(los))
}