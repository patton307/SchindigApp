(function() {
  'use strict';

  angular
    .module('navigation')
    .controller('NavigationController', function(
      $http,
      $scope,
      $state,
      $stateParams,
      NavigationService
    ){

        //TOP LEVEL NAVIGATION//
      $scope.wizardGo = function(){
        console.log('is this going to the wizard?');
        $state.go('wizard');
      };
      $scope.managePartyGo = function(){
        $state.go('manageParty');
      };
      $scope.allPartiesGo = function(){
        $state.go('allParties');
      };
      $scope.profileGO = function(){
        $state.go('profile');
      };
        //TABS//

    });
}());
