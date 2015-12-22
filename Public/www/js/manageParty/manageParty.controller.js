(function() {
  'use strict';

  angular
    .module('manageParty')
    .controller('ManagePartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ManagePartyService
    ){
      var vm = this;
      var rawUserID = +localStorage.getItem('userID')
        console.log('dingdong');
        var userID = {
          userID: rawUserID
        }

        ManagePartyService.getHostedParties(userID)
          .success(function(data){
            $scope.hostedParties = data;
            console.log('party one ',data);
          })
          .error(function(data){
            console.log('error');
          })

          $scope.shouldShowDelete = false;
          $scope.shouldShowReorder = false;
          $scope.listCanSwipe = true;


      ////MANAGE/EDIT HOSTED PARTIES////

      $scope.viewOne = function(party){
        localStorage.setItem('OnePartyID', party.partyID);
      };
      $scope.loadOne = function(){
        var rawPartyID = +localStorage.getItem('OnePartyID');
        ManagePartyService.getOneHostedParty(rawPartyID).then(function(data){
          console.log('data?', data);
          $scope.oneParty = data.data;
        });
      };
      $scope.loadOneFavor = function(){
        var rawPartyID = +localStorage.getItem('OnePartyID');
        ManagePartyService.getPartyFavor(rawPartyID).then(function(data){
          console.log(data);
          $scope.onePartyFavor = data.data;
        });
      };
      $scope.loadInvitedPeople = function(){
        var rawPartyID = +localStorage.getItem('OnePartyID');
        ManagePartyService.getInvitedPeeps(rawPartyID).then(function(data){
          console.log('controller invites', data.data);
          $scope.inviteList = data.data;
        });
      };
    });

}());
