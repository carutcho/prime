angular.module('login').controller('LoginController',
    function($scope, $http){
      //'Authorization': 'Basic dGVzdDp0ZXN0',
      $scope.usuario = {
        usuario : "reinaldo",
        senha : "123"
      }

      $scope.logar = function(){

          let teste = $http({
                 url:'http://localhost:8080/produto/encriptar',
                 method:"POST",
                 headers: {'Content-Type': 'application/json'},
                 data: $scope.usuario
            }).success(function (data, status, headers, config){
              debugger;
            }).error(function(statusText, status, headers, config){
              debugger;
            })

      }
});
