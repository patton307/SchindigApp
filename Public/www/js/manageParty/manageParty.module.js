(function() {
  'use strict';

  angular
    .module('manageParty', [
      'ionic',
      'ngCordova'
    ])
    .config(function($stateProvider){
      $stateProvider
        .state('hostedParty', {
          url: '/hostedParty',
          templateUrl: 'js/manageParty/views/viewHostedParties.html',
          controller: 'ManagePartyController'
        })
    })

}());
