let [a, b, c, d] = ['hello', ', ', 'world'];
console.log(a + b + c);
console.log(d);
d = "fred";
console.log(d);


/*
var bob = `Hello, ${function() {
  // Some really ridiculously long block of code goes here
  return Date.now();
}()}`;
console.log(bob);
console.log(bob);
console.log(bob);
console.log(bob);


var doIt = function() {
  let fred = 5;
  return foo;
}

var foo = (x) => {
  console.log(this);
};
foo();
var otherFoo = function() {
  console.log(this);
}
otherFoo();
var MyClass = (function() {
  var key = Symbol("key");
  
  function MyClass(privateData) {
    this[key] = privateData;
  }
  
  MyClass.prototype = {
    doSomething: function() {
      return this[key];
    }
  };
  
  return MyClass;
})();

var c = new MyClass("hello");
console.log(c.doSomething());
console.log(c["key"]);
console.log(c[Symbol("key")]);
console.log("------");
for (var m in c) {
  console.log(m);
}
*/
