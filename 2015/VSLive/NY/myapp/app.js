var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var mongoose = require('mongoose');

var routes = require('./routes/index');
var users = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


mongoose.connect('mongodb://localhost/vslivedemo-dev');
var Person = mongoose.model('Person', { 
  firstName: String,
  lastName: String,
  age: Number
});

var persons = undefined;
Person.find(function(err, ps) {
  if (err)
    console.log(err);
  else {
    console.log("Persons found:");
    console.log(ps);
    persons = ps;
  }
});

/* GET home page. */
app.get('/', function(req, res, next) {
  var message = {
    message: "Welcome to Express WebAPI"
  };
  res.jsonp(message);
});


app.get('/persons', function(req, res) {
  res.jsonp(persons);
});
app.post('/persons', function(req, res) {
  var newPerson = req.body;
  console.log(newPerson);
  var newP = new Person({
    firstName: newPerson.firstName,
    lastName: newPerson.lastName,
    age: newPerson.age
  });
  newP.save(function(err, per) {
    if (err) {
      console.log(err);
      res.status(400);
    }
    else {
      res.jsonp(per);
    }
  });
})

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});


module.exports = app;
