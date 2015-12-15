(function() {
 'use strict';
 angular
  .module('schindig',[
    'ionic',
    'eventWizard',
    'loginRegister',
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
        // Each tab has its own nav history stack:
      .state('splash', {
        url: '/splash',
        templateUrl: 'js/common/views/splash.html',
        controller: 'EventWizardController'
        })
      .state('createNewUser', {
        url: '/createNewUser',
        templateUrl: 'js/common/views/createNewUser.html',
        controller: 'LoginRegisterController'
      })
      });
}());
