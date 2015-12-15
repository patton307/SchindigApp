(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var registerUrl = 'http://localhost:8080/user/create'
      var getData = function(){
        return $http.get('http://localhost:8080/wizard');
      };
      var createUser = function(data) {
        return $http.post(registerUrl, data)
      };

      return {
        createUser: createUser,
        getData: getData
      }
    });

}());
