var mongodb = require('mongodb')

var Db = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var ObjectID = require('mongodb').ObjectID;

var host = "localhost";
var port = 27017;
var db = new mongodb.Db('vsliveorlando', 
	new mongodb.Server(host, port, {auto_reconnect: true}, {}));
db.open(function(error, result) {
	if (error) console.error(error);
	else {
		db.collection('persons', function(error, collection) {
			if( error ) console.error(error);
			else  {
				collection.find().toArray(function(err, data) {
					if (err) console.error(err);
					else {
						console.log(data);
						db.close();
					}
				});
			}
		});
	}
});

