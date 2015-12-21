(function() {
  'use strict';

  angular
    .module('viewParties', [
      'ionic',
      'ngCordova'
    ])
    .config(function($stateProvider){
      $stateProvider
        // .state('allParites', {
        //   url: '/allParties',
        //   templateUrl: 'js/viewParties/views/allParties.html',
        //   controller: 'ViewPartyController'
        // })
        .state('invitedParty', {
          url:'/invitedParty',
          templateUrl: 'js/viewParties/views/invitedParty.html',
          controller: 'ViewPartyController'
        });
    });
}());
