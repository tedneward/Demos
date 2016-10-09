val p = new Person("Ted", "Neward", 39)
println(p)

println("Howdy, " + p.firstName + ", would you like to get a vodka?")
p.firstName = "The speaker formerly known as Ted"
println(p)

val p2 = new Person("Bill", "Burke", 39)
println(if (p > p2) "Ted is better than Bill" else "Nah, never happened")


