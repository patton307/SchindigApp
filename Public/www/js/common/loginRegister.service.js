(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var ip = 'http://localhost';
      var registerUrl = ip + ':8080/user/create';
      var loginUrl = ip + ':8080/user/login';

      var login = function(loginData) {
        return $http.post(loginUrl, loginData)
          .then(function(data){
            console.log('Login Success: ', data);
            localStorage.setItem('userID', data.data);
            console.log(data.data);
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
