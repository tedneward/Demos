if (typeof(println) === 'undefined')
{
  println = function() {
     var msg = ''
     for (var i = 0; i < arguments.length; i++)
        msg = msg + ' ' + arguments[i] + ' ';
     WScript.Echo(typeof(msg) + ":" + msg)
  } 	
}
//println('Howdy');

var tedP = {
  'firstname' : "Ted",
  'lastname' : "Pattison"
}

var makeDrinker = function(person) {
  person['Favorite Drink of All Time'] = 'RedBull';
  person['drink'] = function() {
    println(this["Favorite Drink of All Time"]);
  }
}
tedP = makeDrinker(tedP)

 
tedP["Favorite Drink of All Time"] = "Red Bull and Vodka";
//println(tedP["Favorite Drink of All Time"], " is a great drink!")
//println()
tedP.drink()

