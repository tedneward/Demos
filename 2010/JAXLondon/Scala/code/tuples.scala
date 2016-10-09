import System.out._

val tuple1 = ("Ted", "Neward", 38)

println(tuple1)
println("First name = " + tuple1._1)

val (firstName, lastName, age) = tuple1
println(firstName)
println(lastName)
println(age)

println(tuple1.productArity)



