(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var ip = 'http://192.168.1.66';
      var viewHostedParties = ip + ':8080/party/1';

      var getHostedParties = function(){
        console.log('dog');
        return $http.get(viewHostedParties)
      };

      return {
        getHostedParties: getHostedParties
      }
    });

}());
