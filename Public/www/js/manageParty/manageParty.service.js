(function() {
  'use strict';

  angular
    .module('manageParty')
    .factory('ManagePartyService', function($http, $state){
      var vm = this;
      var ip = 'http://localhost:8080';
      var viewHostedPartiesURL = ip + '/parties/host';
      var viewInvitedPartiesURL = ip +'/parties/user';
      var updatedHostedPartiesURL = ip + '/party/update';
      var deleteFavorUrl = ip + '/party/favor/delete';
      var deletePartyUrl = ip + '/party/delete';

      var deleteParty = function(data){
        return $http.post(deletePartyUrl, data);
      };
      var deleteFavorFromParty = function(listDelete){
        console.log(listDelete);
        return $http.post(deleteFavorUrl, listDelete)
          .then(function(data){
            console.log('data', data);
        });
      };
      var getInvitedPeeps = function(partyID){
        return $http.get(ip+'/party/'+partyID+'/invites').success(function(data){
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
        return $http.get(ip+'/party/'+partyID).success(function(data){
          console.log('one party', data);
        });
      };
      var getPartyFavor = function(partyID){
        partyID = partyID;
        return $http.get(ip + '/party/'+ partyID +'/favors').success(function(data){
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
        getInvitedPeeps: getInvitedPeeps,
        deleteFavorFromParty: deleteFavorFromParty,
        deleteParty: deleteParty
      };

    });

}());
