(function() {
  'use strict';

  angular
    .module('profile', [
      'ionic'
    ])
    .config(function($stateProvider, $urlRouterProvider){
      $urlRouterProvider.otherwise('')
      $stateProvider
        .state('profile', {
          url: '/profile',
          templateUrl: 'js/profile/views/profileMain.html'
        })
    });

}());
