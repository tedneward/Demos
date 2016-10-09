if (typeof "println" !== "function")
    println = function(str) {
        print(str + "\n")
    }

var outish = println

outish('Hello, world!')

var showGlobals = function () {
    outish(this)
    for (var i in this)
        outish(i)
}
showGlobals()

var greetings = function () {
    var action = function(str) {
        str = str + "!"
        println("Greetings, " + str)
    }

    for (var i=0; i<arguments.length; i++)
        action(arguments[i])
    println(i)
}
println("With no args: ===>")
greetings()
println("With 2 args: ===>")
greetings("Gary", "Jeff")
