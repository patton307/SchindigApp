(function() {
  'use strict';

  angular
    .module('manageParty')
    .controller('ManagePartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ManagePartyService,
      EventWizardService
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
          console.log('load favors',data);
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
      $scope.goToManageFavor = function(){
        $state.go('manageFavor')
      };
      $scope.goToManageInvites = function(){
        $state.go('manageInvites')
      };
      $scope.getNameValue = function(value){
        console.log('changed value',value);
        $scope.partyName = value;
        console.log('scoped value',$scope.partyName);
      };
      $scope.getDescriptionValue = function(descriptionValue){
        console.log('changed descriptionValue',descriptionValue);
        $scope.description = descriptionValue;
        console.log('scoped description',$scope.description);
      };
      $scope.getLocationValue = function(locationValue){
        console.log('changed descriptionValue',locationValue);
        $scope.location = locationValue;
        console.log('scoped location',$scope.location);
      };
      $scope.editData = function(partyName, description, location){
        var partyID = +localStorage.getItem('OnePartyID');
        console.log('what is this', location.formatted_address);
        var data = {
          party: {
            local: location.formatted_address,
            partyName: partyName,
            description: description,
            partyID: partyID
          }
        };
        EventWizardService.updateWizData(data).success(function(updatedWizData){
          console.log('success', updatedWizData);
          $state.go('home');
        });
      };
    });
}());
