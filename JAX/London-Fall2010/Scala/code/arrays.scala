import System.out._

val numlist1 = Array(1, 2, 3, 4)

println(numlist1(0))
println(numlist1(1))
println(numlist1(2))
println(numlist1(3))

println("=============")

numlist1(1) = 3

println(numlist1(1))

println(numlist1.length)

val numlist2 = Array.range(1, 4)
