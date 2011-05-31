print("hello!");

//if (alert !== undefined)
//  print("alert not defined!");
  
if (typeof Object.create !== 'function') {
  Object.create = function(proto) {
    var F = function () {};
    F.prototype = proto;
    return new F();
  }
}

var o = Object.create(Object);
print(o.prototype);
