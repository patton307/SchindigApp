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
        $state.go('wizard');
      };
      $scope.managePartyGo = function(){
        $state.go('manageParty');
      };
      $scope.allPartiesGo = function(){
        console.log('go go go ');
        $state.go('allParties');
      };
      $scope.profileGO = function(){
        $state.go('profile');
      };
        //TABS//

    });
}());
