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
        .state('hostedParty', {
          url: '/hostedParty',
          templateUrl: 'js/manageParty/views/manageHostedParty.html',
          controller: 'ManagePartyController'
        })

        .state('partyList', {
          url: '/partyList',
          templateUrl: 'js/manageParty/views/viewAllParties.html',
          controller: "ManagePartyController"
        })

        .state('invitedParty', {
          url:'/invitedParty',
          templateUrl: 'js/manageParty/views/viewInvitedParty.html',
          controller: 'ManagePartyController'
        });
    });


}());
