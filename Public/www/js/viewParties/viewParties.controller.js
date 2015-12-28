(function() {
  'use strict';

  angular
    .module('viewParties')
    .controller('ViewPartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ViewPartyService,
      ionicMaterialMotion,
      $ionicPopup,

    ){

      var vm = this;

      $scope.hostedParties = 'hostData';
      $scope.invitedParties='invData';
      $scope.favorData = 'favorData;';

      var rawUserID = +localStorage.getItem('userID');
      var userID = {
      userID: rawUserID
    };//THIS IS PROBABLY USED, BLAKE IS STUPID

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
          console.log(hostData);
        })
        .error(function(data){
          console.log('error', rawUserID);
        });
      $scope.getOneHostParty = function (party) {
        localStorage.setItem('oneHostPartyID', party.partyID);
      };
      $scope.loadOneHostParty = function(){
        var partyIdItem = +localStorage.getItem('oneHostPartyID');
        ViewPartyService.getOneParty(partyIdItem).then(function(data){
          console.log('hostpartyData', data.data);
          $scope.hostPartyOne = data.data;
        });
      };


        //FAVOR CLAIMING//
      $scope.loadOneFavor = function(){
          var rawPartyID = +localStorage.getItem('oneInvPartyID');
          ViewPartyService.getPartyFavor(rawPartyID).then(function(data){
            console.log('favor data', data.data);
            $scope.onePartyFavor = data.data;
        });
      };

      $scope.showFavorConfirm = function(favor){
        var favorClaimPopup = $ionicPopup.confirm ({
          title: 'Claim Party Favor?',
          template: 'Are you REALLY going to bring this?'
        });
        favorClaimPopup.then (function(res){
          if(res){
            var data = {
              favor: {
              favorID: favor.favor.favorID
              },
              userID: rawUserID,
              listID: favor.listID
            };
            console.log('postFavor', data);
            ViewPartyService.favorClaim(data).then(function(data){
              console.log(data);
              favor.claimed = true;
            });
          }
          else {
            alert("Alrighty Then...");
            }
          });
        };

      // $scope.postOneFavorID = function(favor){
      //   var rawPartyID = +localStorage.getItem('oneInvPartyID');
      //   console.log(favor);
      //   var data = {
      //     favor: {
      //     favorID: favor.favorID
      //     },
      //     userID: rawUserID
      //   };
      //   console.log('postFavor', data);
      //   ViewPartyService.favorClaim(rawPartyID, data).then(function(data){
      //     console.log('return from claim', data);
      //   });
      // };
    });
}());
