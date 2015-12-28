(function() {
  'use strict';

  angular
    .module('schindig')
    .controller('LoginRegisterController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      LoginRegisterService,
      $cordovaDevice,
      $ionicPlatform
    )
      {


        // CORDOVA DEVICE//
        var uuid;
      //   $ionicPlatform.ready(function() {
      //     var device = $cordovaDevice.getDevice();
      //     uuid = device.uuid;
      //     console.log("device uuid", device.uuid);
      // });

      // console.log("variable uuid", uuid);


        //LOGIN USER AND ROUTE
      $scope.login = function(username, password){
        var loginData = {
          user : {
            username: username,
            password: password
          },
          device : uuid
        };
        LoginRegisterService.login(loginData).then(function(data){
            localStorage.setItem('userID', data.data);
            $state.go('home');
        });

        // LoginRegisterService.login(loginData);

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
