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
        .state('manageParty', {
          url: '/manageParty',
          templateUrl: 'js/manageParty/views/manageHostedParty.html',
          controller: 'ManagePartyController'
        });
    });


}());
