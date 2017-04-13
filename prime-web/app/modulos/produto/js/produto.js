var produto = angular.module('produto', ['ui.router']);

produto.config(function($stateProvider, $urlRouterProvider){
  $stateProvider.state('buscar', {url: '/buscar', templateUrl : '../partials/buscar.html', controller : 'ProdutoController'});
});
