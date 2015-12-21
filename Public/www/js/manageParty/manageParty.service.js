(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var vm = this;
      var ip = 'http://10.0.10.72';
      var viewHostedPartiesURL = ip + ':8080/party/host';
      var viewInvitedPartiesURL = ip +':8080/parties/user';
      var updatedHostedPartiesURL = ip + ':8080/party/update';

      var getHostedParties = function(userID){
        console.log('dog');
        return $http.post(viewHostedPartiesURL, userID)
          .success(function(data){
            console.log('succes view', data);
          });
      };

      var getInvitedParties = function(){
        return $http.get(viewInvitedPartiesURL);
      };

      var updatedHostedParties = function (data){
        return $http.patch(updatedHostedPartiesURL, data)
          .success(function(data){
            console.log('success updateParty', data);
          });
      };



      return {
        getHostedParties: getHostedParties,
        getInvitedParties: getInvitedParties,
        updatedHostedParties: updatedHostedParties
      };


    });

}());
