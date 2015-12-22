(function() {
  'use strict';

  angular
    .module('manageParty', [
      'ionic',
      'ngCordova',
      'ion-google-place'
    ])
    .config(function($stateProvider){
      $stateProvider
        .state('manageLanding', {
          url: '/manageLanding',
          templateUrl: 'js/manageParty/views/manageLanding.html',
          controller: 'ManagePartyController'
        })
        .state('manageParty', {
          url: '/manageParty/:partyID',
          templateUrl: 'js/manageParty/views/manageHostedParty.html',
          controller: 'ManagePartyController'
        })
    });


}());
