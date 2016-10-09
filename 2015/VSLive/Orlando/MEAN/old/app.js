var debug = require('debug');

var debugApp = debug('app');
var debugData = debug('data');

console.log("Welcome to my custom application");

debugApp("You should only see this if DEBUG=app or *");

