(function() {
  'use strict';

  angular
    .module('loginRegister')
    .controller('LoginRegisterController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      LoginRegisterService,
      $cordovaDevice
    )
      {

        //CORDOVA DEVICE//
        // console.log($cordovaDevice.getUUID());

        //LOGIN USER AND ROUTE
      $scope.login = function(username, password){
        var loginData = {
          username: username,
          password: password
        };
        LoginRegisterService.login(loginData).then(function(data){
          console.log('dog', data);
          localStorage.setItem('userID', data.data);
          $state.go('home');
        });
        LoginRegisterService.login(loginData);
      };

      $scope.signUp = function(){
        $state.go('createNewUser');
      };

      //FOR TO GET TO OUR VIEWS - DELETE FOR PRODUCTION

      $scope.dog = function (){
        $state.go('hostedParty');
      };

      //DELETE TO TOP COMMENT

      $scope.createNewUser = function(username, password, firstName, lastName, email, phone){
        var userData = {
          username: username,
          password: password,
          firstName: firstName,
          lastName: lastName,
          email: email,
          phone: phone
        };
        LoginRegisterService.createUser(userData).success(function(data){
          console.log('create new user',data);
          $state.go('login');
        });
    };
    });

}());
