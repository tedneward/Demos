var express = require('express');
var router = express.Router();
var debug = require('debug')('persons');
var _ = require('lodash');
var mongodb = require('mongodb');
var host = "localhost";
var port = 27017;
var db = new mongodb.Db('vsliveorlando', 
	new mongodb.Server(host, port, {auto_reconnect: true}, {}));

/* This is deprecated */
var persons = [
  { id: 1,
    firstName: "Ted",
    lastName: "Neward",
    speakerRating: 5
  },
  { id: 2,
    firstName: "Billy",
    lastName: "Hollis",
    speakerRating: 4.9
  },
  { id: 3,
    firstName: "Brian",
    lastName: "Randell",
    speakerRating: 4.5
  }
];

router.param('pid', function (req, res, next, personId) {
  debug("personId:",personId);
  db.open(function(error, result) {
  	if (error) console.error(error);
  	else {
      debug("db open");
  		db.collection('persons', function(error, collection) {
  			if( error ) console.error(error);
  			else {
          debug("collection persons found");
  				collection.findOne({"_id":new mongodb.ObjectID(personId)}, 
            {}, function(err, data) {
  					if (err) console.error(err);
  					else {
              debug("data:",data);
  						req.person = data;
              next();
  					}
  				});
  			}
  		});
  	}
  });
});

/* GET persons listing. */
router.get('/', function(req, res, next) {
  db.open(function(error, result) {
  	if (error) console.error(error);
  	else {
  		db.collection('persons', function(error, collection) {
  			if( error ) console.error(error);
  			else  {
  				collection.find().toArray(function(err, data) {
  					if (err) console.error(err);
  					else {
              debug("pid found",data);
              res.send(data);
  						db.close();
  					}
  				});
  			}
  		});
  	}
  });
});
/* GET an individual person */
router.get('/:pid', function(req, res, next) {
  debug("get /:pid received",req.params.id);
  res.send(req.person);
});
/* DELETE an individual person */
router.delete('/:pid', function(req, res, next) {
  debug("delete /:id received",req.params.id);
  res.send(req.person);
});
/* PUT update to an individual person */
router.put('/:pid', function(req, res, next) {
  debug("put /:id received",req.params.id);
  persons.push(req.body);
  res.send({ message: "Success" });
});
/* POST insert a new person into the database */
router.post('/', function(req, res, next) {
  debug("post received",req.body);
  persons.push(req.body);
  res.send({ message: "Success" });
});

module.exports = router;

