(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var vm = this;
      var ip = 'http://10.0.10.32';
      var viewHostedParties = ip + ':8080/parties';


      var getHostedParties = function(userID){
        console.log('dog');
        return $http.post(viewHostedParties, userID)
          .success(function(data){
            console.log('succes view', data);
          })
      };

      return {
        getHostedParties: getHostedParties
      }
    });

}());
