var prime = angular.module('prime', ['ui.router', 'login']);

prime.config(function($stateProvider, $urlRouterProvider){
  $urlRouterProvider.otherwise('/');
  $stateProvider.state('404', {url: '/404', templateUrl : '../404.html'} );
  $stateProvider.state('boas vindas', {url: '/', templateUrl : '../boasvindas.html'} );
});
