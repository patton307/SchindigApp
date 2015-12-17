(function() {
 'use strict';
 angular
  .module('schindig',[
    'ionic',
    'eventWizard',
    'loginRegister',
    'profile',
    'manageParty',
    'underscore',
    'ionic-material'
    ])
    .run(function($ionicPlatform) {
      $ionicPlatform.ready(function() {
        if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
          cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
          cordova.plugins.Keyboard.disableScroll(true);
        }
        if (window.StatusBar) {
          StatusBar.styleDefault();
        }
      });
    })
    .config(function($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/login');
      $stateProvider
      .state('login', {
        url: '/login',
        templateUrl: 'js/common/views/login.html',
        controller: 'LoginRegisterController'
      })
      .state('createNewUser', {
        url: '/createNewUser',
        templateUrl: 'js/common/views/createNewUser.html',
        controller: 'LoginRegisterController'
      })
        // Each tab has its own nav history stack:
      .state('splash', {
        url: '/splash',
        templateUrl: 'js/common/views/splash.html',
        controller: 'EventWizardController'
        })

      })
      angular
        .module('underscore', [])
        .factory('_', function ($window) {
          return $window._;
        });
}());
