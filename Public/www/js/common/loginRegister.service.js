(function() {
  'use strict';

  angular
    .module('loginRegister')
    .factory('LoginRegisterService', function($http, $state, $q, $cordovaDevice){

      var ip = 'http://10.0.10.39';
      var registerUrl = ip + ':8080/user/create';
      var loginUrl = ip + ':8080/user/login';
      // var uuidPostUrl = ip + ':8080/validate/' + uuid;

      // var uuid = $cordovaDevice.getUUID();

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
        login: login
      };
    });

}());
