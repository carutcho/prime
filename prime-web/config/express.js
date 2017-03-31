var express = require('express');

module.exports = function () {
  var  app = express ();

  app.set('port',3000);
  app.use(express.static('./public'));

  return app;
}
