// Load modules
var express = require('express'),
    bodyParser = require('body-parser'),
    debug = require('debug')('app'),
    _ = require("lodash");

// Create express instance
var app = express();
app.use(bodyParser.json())

var personData = [
  {
    "id": 1,
    "firstName": "Ted",
    "lastName": "Neward",
    "status": "MEANing"
  },
  {
    "id": 2,
    "firstName": "Brian",
    "lastName": "Randell",
    "status": "TFSing"
  }
];

var getAllPersons = function(req, res) {
  debug("getAllPersons");
  res.status(200).jsonp(personData);
};
var getPerson = function(req, res) {
  if (req.person) {
    res.status(200).jsonp(req.person);
  }
  else {
    res.status(404).jsonp({ message: "Unrecognized identifier: " + identifier });
  }
};
var updatePerson = function(req, res) {
  if (req.person) {
    res.status(200).jsonp(_.merge(req.person, req.body));
  }
  else {
    res.status(404).jsonp({ message: "Unrecognized person identifier" });
  }
};
var insertPerson = function(req, res) {
  var person = req.body;
  debug("Received", person);
  person.id = personData.length + 1;
  personData.push(person);
  res.status(200).jsonp(person);
};
var deletePerson = function(req, res) {
  if (req.person) {
    debug("Removing", req.person.firstName, req.person.lastName);
    _.remove(personData, function(it) {
      it.id === req.person.id;
    });
    debug("personData=", personData);
    var response = { message: "Deleted successfully" };
    res.status(200).jsonp(response);
  }
  else {
    var response = { message: "Unrecognized person identifier"};
    res.status(404).jsonp(response);
  }
};

app.get('/', function (req, res) {
  debug("/ requested");
  res.send('Hello World!');
});
app.get('/persons', getAllPersons);
app.get('/persons/:personId', getPerson);
app.delete('/persons/:personId', deletePerson);
app.post('/persons', insertPerson);
app.put('/persons/:personId', updatePerson);

app.param('personId', function (req, res, next, personId) {
  debug("personId found:",personId);
  var person = _.find(personData, function(it) { 
    return personId == it.id;
  });
  debug("person:", person);
  req.person = person;
  next();
});

// Start the server
var port = process.env.PORT || 3000;
debug("We picked up",port,"for the port");
var server = app.listen(port, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);

});
