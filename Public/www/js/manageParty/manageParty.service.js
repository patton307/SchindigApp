(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var vm = this;
      var ip = 'http://localhost';
      var viewHostedPartiesURL = ip + ':8080/parties/host';
      var viewInvitedPartiesURL = ip +':8080/parties/user';
      var updatedHostedPartiesURL = ip + ':8080/party/update';

      var getInvitedPeeps = function(partyID){
        return $http.get(ip+':8080/party/'+partyID+'/invites').success(function(data){
          console.log('invite list',data);
        });
      };
      var getHostedParties = function(userID){
        console.log('dog');
        return $http.post(viewHostedPartiesURL, userID)
          .success(function(data){
            console.log('succes view', data);
        });
      };
      var getOneHostedParty = function(partyID){
        console.log(partyID);
        partyID = partyID;
        return $http.get(ip+':8080/party/'+partyID).success(function(data){
          console.log('one party', data);
        });
      };
      var getPartyFavor = function(partyID){
        partyID = partyID;
        return $http.get(ip + ':8080/party/'+ partyID +'/favors').success(function(data){
          console.log('favor data', data);
        });
      };
      var getInvitedParties = function(userID){
        return $http.get(viewInvitedPartiesURL, userID)
          .success(function(data){
            console.log('succes your', data);
        });
      };
      var updatedHostedParties = function (data){
        return $http.patch(updatedHostedPartiesURL, data)
          .success(function(data){
            console.log('success updateParty', data);
          });
      };
      return {
        getHostedParties: getHostedParties,
        updatedHostedParties: updatedHostedParties,
        getOneHostedParty: getOneHostedParty,
        getPartyFavor: getPartyFavor,
        getInvitedPeeps: getInvitedPeeps
      };

    });

}());
