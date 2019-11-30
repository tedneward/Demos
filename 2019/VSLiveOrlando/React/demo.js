var println = console.log

var speaker = {
  'firstName' : 'Ted',
  'lastName' : 'Neward',
  sayHello : function() {
    println("Hello!")
  },
  sayHowdy : function() {
    println("Howdy!")
  }
}
println(speaker);

println(speaker.firstName)
println(speaker["lastName"])
speaker.sayHowdy()
speaker["sayHello"]()

for (var m in speaker) {
  println(m + "=" + speaker[m])
}
