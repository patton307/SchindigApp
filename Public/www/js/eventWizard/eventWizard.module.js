(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic',

    ])
    .config(function($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise('/wizard/name');
    $stateProvider
      .state('wizard', {
        url:'/wizard',
        templateUrl: "js/eventWizard/views/evtLanding.html",
        controller: 'EventWizardController'
      })
      .state('wizard', {
        url:'/wizard',
        templateUrl: "js/eventWizard/views/evtLanding.html",
        controller: 'EventWizardController'
      })
      .state('wizard.name', {
        url:'/wizard/subtype',
        templateUrl:"js/eventWizard/views/evtSubType.html",
        controller: 'EventWizardController'
      })
      .state('wizard.whenwhere', {
        url:"/wizard/whenwhere",
        templateUrl:"js/eventWizard/views/evtWhenWhere.html",
        controller: 'EventWizardController'
      })
      .state('wizard.favors', {
        url:"/wizard/favors",
        templateUrl:"js/eventWizard/views/evtFavors.html",
        controller: 'EventWizardController'
      })
      .state('wizard.stretchgoal', {
        url:"/wizard/strechgoal",
        templateUrl:"js/eventWizard/views/evtStrechGoal.html",
        controller: 'EventWizardController'
      })
      .state('wizard.invites', {
        url:"/wizard/invites",
        templateUrl:"js/eventWizard/views/evtInvites.html",
        controller: 'EventWizardController'
      });
});

    // $stateProvider
    //   .state('wizard', {
    //     abstract: true,
    //     url:'/wizard',
    //     templateUrl: "<>"
    //   })
    //   .state('wizard.name', {
    //     url:'/wizard/nameType',
    //     views: {
    //       'name-type': {
    //           templateUrl:"js/eventWizard/views/evtSubType.html",
    //           controller: "EventWizardController"
    //       }
    //     }
    //
    //   })
    //   .state('wizard.whenwhere', {
    //     url:"/wizard/whenwhere",
    //     views: {
    //       'when-where': {
    //           templateUrl:"js/eventWizard/views/evtWhenWhere.html",
    //           controller: "EventWizardController"
    //       }
    //     }
    //
    //   })
    //   .state('wizard.favors', {
    //     url:"/wizard/favors",
    //     views: {
    //       'wiz-favors': {
    //         templateUrl:"js/eventWizard/views/evtFavors.html",
    //         controller: "EventWizardController"
    //       }
    //     }
    //
    //   })
    //   .state('wizard.stretchgoal', {
    //     url:"/wizard/strechgoal",
    //     views: {
    //       'stretch-goal': {
    //           templateUrl:"js/eventWizard/views/evtStrechGoal.html",
    //           controller: "EventWizardController"
    //       }
    //     }
    //
    //   })
    //   .state('wizard.invites', {
    //     url:"/wizard/invites",
    //     templateUrl:"js/eventWizard/views/evtInvites.html"
    //   });
    // });


}());
