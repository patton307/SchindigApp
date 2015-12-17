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
      $scope.getHostedParties = function(){

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
      };
    });



}());
