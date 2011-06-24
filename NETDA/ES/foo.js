var output = print;

function Scott(msg)
{
  if (arguments.length !== 0)
  {
    (function(v) { output("We got a message of " + msg); })(arguments.length);
  }

  output("Msg = " + msg)
  for (var x = 1; x < arguments.length; x++)
    output(arguments[x]);

  for (var y in this)
    output(y);
}

function newPerson(firstName, lastName)
{
  var p = new Object();
  p.firstName = firstName;
  p.lastName = lastName;
  p.toString = function() { return this["firstName"] + " " + this["lastName"]; }
  return p
}

/*
var i = "12";
var j = 12;

var k = i + j;
output(k);

if (i == j)
  output ("i == j");
else
  output ("Nothing");

if (i===j)
  output ("i===j");
else
  output ("Nothing");


var outish = print;
outish("Howdy");
*/

//Scott("howdy", "This", "is", "a", "function call");
Scott()

var scott = newPerson("Scott", "Slack");
scott.lastName = "Lazy";
scott.age = 46;
scott.toString = function() 
{
  var result = "[";
  for (var prop in this)
  {
    if (typeof(this[prop] !== typeof(Function) ))
      result += prop + ":" + this[prop] + " ";
  }
  result += "]"
  return result;
}
output(scott.toString())






























