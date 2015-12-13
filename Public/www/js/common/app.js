(function() {
 'use strict';



// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular
  .module('schindig',[
    'ionic',
    'eventWizard',
    'profile'
    ])
    .run(function($ionicPlatform) {
      $ionicPlatform.ready(function() {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
          cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
          cordova.plugins.Keyboard.disableScroll(true);

        }
        if (window.StatusBar) {
          // org.apache.cordova.statusbar required
          StatusBar.styleDefault();
        }
      });
    })
    .config(function($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/tab/dash');
      $stateProvider
      .state('tab', {
        url: '/tab',
        abstract: true,
        templateUrl: 'js/common/views/tabs.html'
        })

        // Each tab has its own nav history stack:

      .state('tab.dash', {
        url: '/dash',
        views: {
          'tab-dash': {
            templateUrl: 'js/common/views/splash.html',
            controller: 'EventWizardController'
          }
        }
        })
        .state('tab.chats', {
          url: '/chats',
          views: {
            'tab-chats': {
              templateUrl: 'js/profile/views/profileMain.html',
              controller: 'EventWizardController'
            }
          }
        })
        .state('tab.account', {
          url: '/account',
          views: {
            'tab-account': {
              templateUrl: 'js/eventWizard/views/evtStretchGoal.html',
              controller: 'EventWizardController'
            }
          }
        });

});








}());
