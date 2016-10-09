var debug = require('debug');

console.log("Hello world");

function hello() {
  var x = this;
  return someReallyLongVariableThatGoesPastTheEdgeOfTheScreenSoThere;
}

var sayHello = function(arg) {
  console.log("Hello, ",arguments[0]);
}
sayHello("Techorama");

var execute = function(fn) {
  fn();
}
execute(sayHello);

var person = {
  'firstName' : 'Ted',
  'lastName' : 'Neward',
  'I can also do this' : 'Yes, you can'
};
console.log(person.firstName);
console.log(person["I can also do this"]);

var x = console.log;
x("Hello");

person.speak = function() {
  console.log("Hi, I'm a person!");
};
person.speak();

var appdebug = debug("app")
appdebug("You should not see this");





