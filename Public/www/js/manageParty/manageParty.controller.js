(function() {
  'use strict';

  angular
    .module('manageParty')
    .controller('ManagePartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ManagePartyServices

    ){
      var vm = this;
      ManagePartyService.getHostedParties()
        .success(function(data){
          $scope.hostedParties = data;
          console.log('party one ',data);
        })
        .error(function(data){
          console.log('error');
        });
    });


}());
