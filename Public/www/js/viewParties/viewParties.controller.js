(function() {
  'use strict';

  angular
    .module('viewParties')
    .controller('ViewPartyController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      ManagePartyService

    ){
      var vm = this;
      ManagePartyService.getHostedParties()
        .success(function(data){
          $scope.hostedParties = data;
          console.log('party one ',data);
        })
        .error(function(data){
          console.log('error');
        })
    });


}());
