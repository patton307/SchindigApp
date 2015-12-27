(function() {
  'use strict';

  angular
    .module('navigation')
    .factory('NavigationService', function($http, $state){
        var ip = "http://10.0.10.72:8080";
        var logOutUrl = ip + "/user/logout";

        var test = function(){
          console.log('doggydog');
        };

        var logOutUser = function(data) {
          return $http.post(logOutUrl, data)
          .success(function(data){
            console.log("logoutService", data);
          });
        };

        return {
          logOutUser: logOutUser
        };
    });
}());
