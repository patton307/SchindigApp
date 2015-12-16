(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var ip = 'http://localhost';
      var viewHostedParties = ip + ':8080/party/2';


      var getHostedParties = function(){
        console.log('dog');
        return $http.get(viewHostedParties)
      };

      return {
        getHostedParties: getHostedParties
      }
    });

}());
