(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state, $q, $cordovaDevice){
      var ip = 'http://localhost';
      var registerUrl = ip + ':8080/user/create';
      var loginUrl = ip + ':8080/user/login';


      // var device = $cordovaDevice.getDevice();
      // $scope.uuid = device.uuid;

      var uuidAuth = function(uuid) {
        console.log("testy", uuid);
        return $http.get(ip + ":8080/validate/" +uuid).success(function(data){
          console.log(data);
        });
      };

      var login = function(loginData) {
        return $http.post(loginUrl, loginData)
          .success(function(data){
            console.log('Login Success: ', data);
            localStorage.setItem('userID', data);
            $state.go('home');
          });
      };
      var createUser = function(data) {
        return $http.post(registerUrl, data);
      };




      return {
        createUser: createUser,
        login: login,
        uuidAuth: uuidAuth
      };
    });

}());
