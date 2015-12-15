(function() {
  'use strict';

  angular
    .module('loginRegister')

    .factory('LoginRegisterService', function($http, $state){
      var getData = function(){
        return $http.get('http://localhost:8080/wizard');
      };

      return {
        getData: getData
      }
    });

}());
