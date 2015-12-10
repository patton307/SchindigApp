(function() {
  'use strict';

  angular
    .module('profile', [
      'ionic',
      'ngCordova'
    ])
    .config(function($stateProvider, $urlRouterProvider){
      $urlRouterProvider.otherwise('')
      $stateProvider
        .state('profile', {
          url: '/profile',
          templateUrl: 'js/profile/views/profileMain.html',
          controller: 'ProfileController'
        })
    });

}());
