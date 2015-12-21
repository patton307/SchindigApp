(function() {
  'use strict';

  angular
    .module('navigation', [
      'ionic',
      'ngCordova',
      'eventWizard'
    ])
    .config(function($stateProvider, $urlRouterProvider){
      $urlRouterProvider.otherwise('');
      $stateProvider
        .state('home', {
          url: '/home',
          templateUrl: 'js/navigation/views/home.html',
          controller: 'NavigationController'
        });
      });


}());
