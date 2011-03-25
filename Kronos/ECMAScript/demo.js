if (typeof(Object.create) !== 'function') {
    Object.create = function(proto) {
        var F = function() {};
        F.prototype = proto;
        return new F();
    };
}
var protoToString = Object.create(String);
println(typeof(protoToString));


Function.prototype.method = function(name, func) {
    if (!this.prototype[name]) {
        this.prototype[name] = func;
    }
    return this;
};
Number.method('integer', function() {
    return Math[this < 0 ? 'ceiling' : 'floor'](this);
});
for (it in Number)
    println(it);
var six = 6;
println(typeof(six));
println(six.integer());


var name = "Ted";
name = 6;
name = false;
name = "I don't want to do this anymore!";
//println(typeof(name));
//println(typeof(6));

var out = println;
if (typeof(out) === typeof(undefined))
    out = print;
//println(typeof(out));
//out(typeof(println));

var printItsType = function (obj)
{
    out("The typeof " + obj + " is " + typeof(obj));
}
//printItsType();

var listGlobals = function ()
{
    for (it in this)
    {
        function(it) {
            out(it);
        }(it);
    }
}
//listGlobals();

var listItsMembers = function(obj)
{
    for (it in obj)
    {
        out(it);
    }
}
listItsMembers(name);
out(name.toString());






