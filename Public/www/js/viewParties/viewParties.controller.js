(function() {
  'use strict';

  angular
    .module('viewParties')
    .controller('ViewPartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ViewPartyService
    ){

      var vm = this;
      $scope.hostedParties = 'hostData';
      $scope.invitedParties='invData';
      $scope.favorData = 'favorData;';

      var rawUserID = +localStorage.getItem('userID');
///what is this, max?
      var userID = {
      userID: rawUserID
    };

    //INVITED PARTIES GET
    ViewPartyService.getInvitedParties(userID)
      .success(function(invData){
        $scope.invitedParties = invData;
      })
      .error(function(data){
        console.log('error');
      });
      $scope.getOneInvParty = function (party){
        localStorage.setItem('oneInvPartyID', party.partyID);
      };
      $scope.loadOneInvParty = function(){
        var partyIdItem = +localStorage.getItem('oneInvPartyID');
        ViewPartyService.getOneParty(partyIdItem).then(function(data){
          $scope.invPartyOne = data.data;
        });
      };

      //HOSTED PARTIES GET
      ViewPartyService.getHostedParties(userID)
        .success(function(hostData){
          $scope.hostedParties = hostData;
        })
        .error(function(data){
          console.log('error');
        });
      $scope.getOneHostParty = function (party) {
        localStorage.setItem('oneHostPartyID', party.partyID);
      };
      $scope.loadOneHostParty = function(){
        var partyIdItem = +localStorage.getItem('oneHostPartyID');
        ViewPartyService.getOneParty(partyIdItem).then(function(data){
          $scope.hostPartyOne = data.data;
        });
      };

        //FAVOR CLAIMING//
      $scope.loadOneFavor = function(){
          var rawPartyID = +localStorage.getItem('oneInvPartyID');
          ViewPartyService.getPartyFavor(rawPartyID).then(function(data){
            $scope.onePartyFavor = data.data;
        });
      };
      $scope.postOneFavorID = function(favor){
        var rawPartyID = +localStorage.getItem('oneInvPartyID');
        console.log(favor);
        var data = {
          favor: {
          favorID: favor.favorID
          },
          userID: rawUserID
        };
        ViewPartyService.favorClaim(rawPartyID, data).then(function(data){
        });
      };
    });
}());
