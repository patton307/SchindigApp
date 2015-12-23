(function() {
  'use strict';
  angular
  .module('viewParties')
  .factory('ViewPartyService', function($http, $state){
    var vm = this;
    var ip = 'http://localhost';
    var viewHostedPartiesURL = ip + ':8080/parties/host';
    var viewInvitedPartiesURL = ip +':8080/parties/user';
    var getOneInvitedPartyURL = ip +':8080/party/';


    var getHostedParties = function(userID){
      return $http.post(viewHostedPartiesURL, userID)
        .success(function(data){

        });
    };

    var updatedHostedParties = function (data){
      return $http.patch(updatedHostedPartiesURL, data)
        .success(function(data){

        });
    };

    var getInvitedParties = function (userID){
      return $http.post (viewInvitedPartiesURL, userID)
      .success(function(data){
      });
    };

    var getOneParty = function (partyID){
      partyID= partyID;
      return $http.get(ip + ':8080/party/'+partyID).success(function(data){

      });
    };

    var getPartyFavor = function(partyID){
        partyID = partyID;
        return $http.get(ip + ':8080/party/'+ partyID +'/favors').success(function(data){

        });
      };

      var favorClaim = function(partyID, favorData){
        partyID = partyID;
        favorData = favorData;
        return $http.post(ip + ':8080/party/'+ partyID + '/claim', favorData).success(function(data){

        });
      };




    return {
      getHostedParties: getHostedParties,
      updatedHostedParties: updatedHostedParties,
      getInvitedParties: getInvitedParties,
      getOneParty: getOneParty,
      getPartyFavor : getPartyFavor,
      favorClaim: favorClaim
    };
  });


}());
