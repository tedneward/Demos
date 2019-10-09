var debug = require('debug')('app');

debug("about to say hello");
console.log("Hello SDN");
console["log"]("Hello again, SDN!");
debug("just said hello");

var l = console.log;
l("Hello for a third time");

