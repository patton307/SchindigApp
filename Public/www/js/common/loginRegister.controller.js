(function() {
  'use strict';

  angular
    .module('loginRegister')
    .controller('LoginRegisterController', function($http, $scope, $state, $stateParams, LoginRegisterService){
      $scope.login = function(){
        LoginRegisterService.getData().success(function(data){
          console.log(data);
          $state.go('splash')
        })
      }

      $scope.createNewUser = function(){
        LoginRegisterService.getData().success(function(data){
          console.log(data);
          $state.go('createNewUser')
        })
      }

    })

}());
