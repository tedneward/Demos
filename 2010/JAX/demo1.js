if (typeof Object.create !== 'function') {
  Object.create = function(o) {
    var F = function () { };
    F.prototype = o;
    return new F();
  };
}

var airplanePrototype = {
    "flight-number" : 0,
    "airline" : 'United',
    "destination" : {
        "city" : "",
        "IATA" : ""
    }
};

var myFlightYesterdayFromSEAtoFRA = Object.create(airplanePrototype);
print(myFlightYesterdayFromSEAtoFRA['airline']);
myFlightYesterdayFromSEAtoFRA.airline = 'Lufthansa';
print(myFlightYesterdayFromSEAtoFRA['airline']);


var j = 12;
print(typeof j);
j = "Howdy";
print(typeof j);

var o2 = {
    "flight-number" : 322,
    "airline" : 'Oceanic'
};

var o1 = {
    "first-name" : "Marco",
    "last-name" : "Glur",
    "outish" : function () {
        for (var i = 0; i < arguments.length; i += 1) {
            print(arguments[i]);
        }
        print("this = " + this);
    }
};

print(o1['first-name']);

o1.outish("Hello from Seattle");

o1.outish("Hello", "from", "Seattle", "I'm", "really", "tired");

var foobar = function () {
    var i = 0;

    return function loopDoIt(x) {
        println(i);
    };
}();

var name = "Patricia";
var methodNameToInvoke = 'toUpperCase';
var upperName = name[methodNameToInvoke]();
print(upperName);
var upperName = name[methodNameToInvoke];
print(upperName);
