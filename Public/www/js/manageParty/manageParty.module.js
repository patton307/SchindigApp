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
        .state('manageHostedParty', {
          url: '/manageHostedParty',
          templateUrl: 'js/manageParty/views/manageHostedParty.html',
          controller: 'ManagePartyController'
        });
      });


}());
