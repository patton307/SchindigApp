(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic',

    ])
    .config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise('/wizard/name')
    $stateProvider
      .state('wizard', {
        url: "/wizard",
        abstract: true,
        templateUrl: "js/eventWizard/views/evtNameType.html"
      })
      .state('wizard.subType', {
        url: '/subType',
        views: {
          'wizard-subType': {
            templateUrl:"js/eventWizard/views/evtWhenWhere.html",
            controller: 'EventWizardController'
          }
        }
      })
      // .state('tab.account.settings', {
      //   url: '/settings',
      //   views: {
      //     'tab-account@tab': {
      //       templateUrl: 'templates/tab-account-settings.html',
      //       controller: 'SettingsCtrl'
      //     }
      //   }
      // });
    // $stateProvider
    //   .state('wizard', {
    //     url:'/wizard',
    //     abstract: true,
    //     templateUrl: "js/eventWizard/views/evtNameType.html",
    //     controller: 'EventWizardController'
    //   })
    //   .state('wizard.name', {
    //     url:'/subtype',
    //     views: {
    //       'evtSubType': {
    //         templateUrl:"js/eventWizard/views/evtSubType.html",
    //         controller: 'EventWizardController'
    //       }
    //     }
    //   })
      // .state('whenwhere', {
      //   url:"/wizard/whenwhere",
      //   templateUrl:"js/eventWizard/views/evtWhenWhere.html"
      // })
      // .state('favors', {
      //   url:"/wizard/favors",
      //   templateUrl:"js/eventWizard/views/evtFavors.html",
      // })
      // .state('stretchgoal', {
      //   url:"/wizard/strechgoal",
      //   templateUrl:"js/eventWizard/views/evtStrechGoal.html"
      // })
      // .state('invites', {
      //   url:"/wizard/invites",
      //   templateUrl:"js/eventWizard/views/evtInvites.html"
      // })
    });

}());
