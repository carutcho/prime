angular.module('prime', ['nrRoute']).config(function($routeProvider){

    $routeProvider.when('/login', {
      templateUrl: 'partials/login/login.html',
      controller: 'LoginController'
    });

});
