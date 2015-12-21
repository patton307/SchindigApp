(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var ip = 'http://10.0.10.72';
      var registerUrl = ip + ':8080/user/create';
      var loginUrl = ip + ':8080/user/login';

      var login = function(loginData) {
        return $http.post(loginUrl, loginData)
          .success(function(data){
          console.log('success', data);
          $state.go('splash');
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
