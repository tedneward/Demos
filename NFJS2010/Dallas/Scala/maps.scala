import System.out._

val people =
    Map( 
        "Ted" -> 39
    )

val morePeople = people + ("Fred" -> 40)

val evenMorePeople = morePeople ++ Map("Jed" -> 68)

println(evenMorePeople)

val name = "Ted"
println(name + " is " + evenMorePeople("Ted"))

