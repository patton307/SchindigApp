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
        // .state('home', {
        //   url: '/home',
        //   templateUrl: 'js/navigation/views/home.html',
        //   controller: 'NavigationController'
        // })
        // setup an abstract state for the tabs directive
        .state('tab', {
          url: "",
          // abstract: true,
          templateUrl: "js/navigation/views/tabs.html"
        })

   // Each tab has its own nav history stack:
      // /tab/home
       .state('tab.home', {
         url: '/home',
         views: {
           'tab-home': {
             templateUrl: 'js/navigation/views/home.html',
             controller: 'NavigationController'
           }
         }
       })
       // tab/test
       .state('tab.wizard', {
         url: '/wizard',
         views: {
           'tab-wizard': {
             templateUrl: 'js/eventWizard/views/evtNameType.html',
             controller: 'EventWizardController'
           }
         }
       })

       .state('tab.parties', {
         url: '/allParties',
         views: {
           'tab-parties': {
             templateUrl: 'js/viewParties/views/allParties.html',
             controller: 'ViewPartiesController'
           }
         }
       })

       .state('tab.settings', {
         url: '/profile',
         views: {
           'tab-profile': {
             templateUrl: 'js/navigation/views/home.html',
             controller: 'ProfileController'
           }
         }
       });
      //  .state('tab.settings', {
      //    url: '/settings',
      //    views: {
      //      'tab-settings': {
      //        templateUrl: 'js/navigation/views/home.html',
      //        controller: 'SettingsController'
      //      }
      //    }
      //  });

    });

}());
