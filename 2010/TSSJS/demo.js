var alertType = typeof(alert)
if (alertType === 'undefined')
    alert = println

var hello = function(name) {
    alert("Hello, " + name)
    alert("You brought " + arguments.length + " friends with you")
}
hello("Matt")
hello("Matt", "Scott", "Kito", "David")

var walkGlobals = function() {
    //var g = this
    //alert(g)
    //for (var it in g)
    //    alert(it)
        
    if (true)
    {
        var msg = "Howdy"
        function() {
            alert(msg)
        }()
    }
}
walkGlobals()