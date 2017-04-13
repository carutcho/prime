angular.module('prime').controller('ProdutoController',
    function($scope){
      $scope.usuario.id = {
        id : 12,
        login : "Reinaldo",
        senha : "123456"
      }

      $scope.logar = function(){
          alert('Buscando produto com id: ' + $scope.usuario.id);
      }
});
