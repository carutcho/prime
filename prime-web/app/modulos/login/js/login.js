var login = angular.module('login', ['ui.router']);

login.config(function($stateProvider, $urlRouterProvider){
  $stateProvider.state('login', {url: '/login', templateUrl : 'modulos/login/partials/login.html', controller : 'LoginController'});    
});
