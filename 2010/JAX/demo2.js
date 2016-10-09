var x = "Sebastian"

function cue() { print("================") }

var out;
if (typeof(println) === 'undefined')
    out = print
else
    out = println

out(x)

var flight = {
  airline : "Oceanic",
  number : 815,
  departure : {
    IATA : "SYD",
    time : "2004-09-22 14:55",
    city : "Sydney"
  },
  arrival : {
    IATA : "LAX",
    time : "2004-09-23 10:42",
    city : "Los Angeles"
  }
};

out(flight.number)
out(flight["number"])

flight["Flight details"] = "Oceanic 815"

out(flight["Flight details"])

function printSomething()
{
    out("printing....")
    var i = 12
    for (var a = 0; a < arguments.length; a++)
    {
        doSomethingElse(a)
        out(arguments[a] + ", ")
    }
    
    function doSomethingElse(arg)
    {
        var x = arg
        out("x = " + x)
        out("i = " + i)
    }
    out("done")
}
printSomething()
printSomething("Sebastian")
printSomething("one", 'two', 3, 4.0)


for (d in flight)
    out(d)

cue()

var pg = function ()
{
    for (t in this)
        out(t)
}()







