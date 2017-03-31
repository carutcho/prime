var login = angular.module('prime', ['ui.router']);

login.config(function($stateProvider, $urlRouterProvider){
  $urlRouterProvider.otherwise('/404');
  $stateProvider.state('login', {url: '/login', templateUrl : '../partials/login/login.html', controller : 'LoginController'});
  $stateProvider.state('404', {url: '/404', templateUrl : '../404.html'} );
});
