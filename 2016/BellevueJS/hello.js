console.log("We'll start in 10 minutes (18:15, or 6:15 for you Americans)");
console["log"]("Howdy");

var obj = {};
obj["First Name"] = "Ted";
obj["Last Name"] = "Neward";

console.log(obj);

var name = "First Name";
obj[name] = "Fred";
obj.doThis = function() {
  console.log("I did it");
};

console.log(obj.toString());


