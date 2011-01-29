import System.out._

val people =
    Map( 
        "Ted" -> 38
    )

val morePeople = people + ("Fred" -> 40)

val evenMorePeople = morePeople ++ Map("Jed" -> 68)

println(evenMorePeople)

val name = "Ted"
println(name + " is " + evenMorePeople("Ted"))

val example = Map[String,String]()
println(example.getClass())
val anotherExample = example + ("Ted" -> "Seattle")
println(anotherExample.getClass())