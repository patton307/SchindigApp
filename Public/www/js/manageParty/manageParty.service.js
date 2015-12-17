(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var vm = this;
      var ip = 'http://192.168.0.135';
      var viewHostedParties = ip + ':8080/party/host';

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
