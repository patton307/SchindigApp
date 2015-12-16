(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var ip = 'http://10.0.10.67';
      var registerUrl = ip + ':8080/user/create';
      var loginUrl = ip + ':8080/user/login';

      var login = function(loginData) {
        return $http.post(loginUrl, loginData)
          .then(function(data){
          console.log('success', data);
          $state.go('splash')
        }, function(data){
          console.log('failure', data);
        })
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
