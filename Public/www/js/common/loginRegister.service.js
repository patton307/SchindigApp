(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var registerUrl = 'http://localhost:8080/user/create';
      var loginUrl = 'http://localhost:8080/user/login';
      
      var login = function(loginData) {
        return $http.post(loginUrl, loginData)
      };
      var createUser = function(data) {
        return $http.post(registerUrl, data)
      };

      return {
        createUser: createUser,
        login: login
      }
    });

}());
