(function() {
  'use strict';

  angular
    .module('viewParties', [
      'ionic',
      'ngCordova'
    ])
    .config(function($stateProvider){
      $stateProvider
        .state('viewParites', {
          url: '/viewParties',
          templateUrl: 'js/viewParties/views/allParties.html',
          controller: 'ViewPartyController'
        })
        .state('invitedParty', {
          url:'/invitedParty',
          templateUrl: 'js/manageParty/views/invitedParty.html',
          controller: 'ViewPartyController'
        });
    });
}());
