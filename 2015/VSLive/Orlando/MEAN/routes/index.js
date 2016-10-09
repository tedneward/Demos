var express = require('express');
var router = express.Router();
var debug = require('debug')('index');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
router.get('/hello', function(req, res, next) {
  debug("/hello requested at ", new Date());
  res.send("<html><body>Hello from Express</body></html>");
});

module.exports = router;
