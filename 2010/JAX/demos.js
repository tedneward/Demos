// Interesting hack to provide a consistent console-print for both the
// SpiderMonkey and Rhino ES engines; SM has print() which implicitly prints
// a CRLF, and Rhino has both println() (with CRLF) and print() (without)
//
var output =
  (typeof println === 'function') ? 
    function(msg) {
      println(msg);
    }
  : function(msg) {
      print(msg);
    }; 

function cue() {
  output("----------------------------------")
}

if (typeof Object.create !== 'function') {
  Object.create = function(o) {
    var F = function () { };
    F.prototype = o;
    return new F();
  };
}

Function.prototype.method = function(name, func) {
  if (!this.prototype[name]) {
    this.prototype[name] = func;
  }
  return this;
};

var eventuality = function(that) {
  var registry = {};
  that.fire = function(event) {
    var array, func, handler, i;
    var type = typeof event === 'string' ?
                 event : event.type;
    if (registry.hasOwnProperty(type)) {
      array = registry[type];
      for (i = 0; i < array.length; i++) {
        handler = array[i];
        func = handler.method;
        if (typeof func === 'string') {
          func = this[func];
        }
        func.apply(this, handler.parameters || [event]);
      }
    }
    return this;
  };
  that.on = function(type, method, parameters) {
    var handler = {
      method : method,
      parameters : parameters
    };
    if (registry.hasOwnProperty(type)) {
      registry[type].push(handler);
    } else {
      registry[type] = [handler];
    }
    return this;
  };
  return that;
};

var reflect = function(o) {
  cue();
  output("Reflecting on " + o)
  for (var it in o)
    output(it + " = " + o[it]);
  cue();
};


var empty_object = { };

var stooge = {
  "first-name" : "Jerome",
  "last-name" : "Howard"
};

var another_stooge = Object.create(stooge);
another_stooge['first-name'] = "Harry";
another_stooge['last-name'] = "Moses";
another_stooge.nickname = 'Moe';

stooge.profession = 'actor';

reflect(stooge);
reflect(another_stooge);

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

cue();

var eventedStooge = eventuality(Object.create(stooge));
eventedStooge.on('poke', function() {
  output("Oh, a wiseguy, eh?");
});
eventedStooge.fire("poke");

cue();

/*
function globalReflect() {
  reflect(this);
}
globalReflect();

output(this);

cue();
*/

Number.method('integer', function() {
  return (Math[(this < 0 ? 'ceil' : 'floor')])(this);
});
output((10 / -3).integer());

cue();

function greetings() {
  if (arguments.length === 0) {
    output("Howdy, whomever you are!");
  }
  else {
    for (var i=0; i < arguments.length; i++) {
      output("Hello, " + arguments[i] + "!")
    }
  }
}

greetings();

greetings("Fred");

greetings("Fred", "Barney", "Wilma", "Betty");

cue()

output(stooge["middle-name"])
var middle = stooge["middle-name"] || "(none)"
output(middle)
output(stooge["middle-name"])

cue()

output(typeof flight)
output(typeof flight.number)
output(typeof flight.toString)
output(typeof flight.constructor)
output(typeof flight.arrival)
output(typeof flight.manifest)

cue()

for (var i in flight)
  output(i)

cue()
