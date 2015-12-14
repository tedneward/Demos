var name = "Bob", 
	time = new Date();
console.log(`Hello, ${name}, how are you, today at ${time}?`);

class Person {
  constructor(fn, ln, a) {
    this.firstName = fn;
    this.lastName = ln;
    this.age = a;
  }
  
  toString() {
    return `[Person: firstName:${this.firstName}, lastName: ${this.lastName} ]`
  }
};

var ted = new Person("Ted", "Neward", 43);
console.log(ted);

var handler = function() {
  console.log("Handled!");
};
var theProtoObj = {
  name: "Proto"
};
var obj = {
    // __proto__
    __proto__: theProtoObj,
    // Shorthand for 'handler: handler'
    handler,
    // Methods
    toString() {
      // Super calls
      return "d " + super.toString();
    },
    // Computed (dynamic) property names
    [ 'prop_' + (() => 42)() ]: 42
};
obj.handler();
console.log(obj.toString());
console.log(obj.prop_42);


var pt = { "x": 1, "y": 2 };
var { x, z } = pt;
console.log(x, z);
var { x: xx, y: yy } = pt;
console.log(xx, yy);

