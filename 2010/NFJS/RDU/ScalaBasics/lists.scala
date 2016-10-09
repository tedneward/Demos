import System.out._

def sumList(xs : List[Int]) : Int=
  if (xs.isEmpty)
    0
  else
    xs.head + sumList(xs.tail)


val numlist1 = 1 :: 2 :: 3 :: 4 :: Nil
val numlist2 = List()
val numlist3 = List(1, 2, 3, 4)
val numlist4 = numlist2 ++ numlist3

println(sumList(numlist1))
println(sumList(numlist2))
println(sumList(numlist3))
println(sumList(numlist4))

