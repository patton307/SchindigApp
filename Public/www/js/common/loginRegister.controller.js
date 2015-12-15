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

      $scope.createNewUser = function(username, password, firstName, lastName, email, phone){
        var userData = {
          username: username,
          password: password,
          firstName: firstName,
          lastName: lastName,
          email: email,
          phone: phone
        }
        LoginRegisterService.createUser(userData).success(function(data){
          console.log(data);
          $state.go('createNewUser')
        })
      }

    })

}());
