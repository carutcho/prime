angular.module('prime').controller('LoginController',
    function($scope){
      $scope.usuario = {
        login : "Reinaldo",
        senha : "123456"
      }

      $scope.logar = function(){
          alert('Logando com: ' + $scope.usuario.login);
      }
});
