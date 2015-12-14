(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic',

    ])
    .config(function($stateProvider){
    $stateProvider
      .state('wizard', {
        url: "/wizard",
        templateUrl: "js/eventWizard/views/evtNameType.html",
        controller: "EventWizardController"
      })
      .state('subtype', {
        url:"/wizard/:nameId",
        templateUrl: "js/eventWizard/views/evtSubType.html",
        controller: "EventWizardController as evtCtrl"
      })
      .state('whenwhere', {
        url:"/wizard/whenwhere",
        templateUrl:"js/eventWizard/views/evtWhenWhere.html",
        controller: "EventWizardController as evtCtrl"
      })
      .state('stretchgoal', {
          url:"/wizard/stretchgoal",
          templateUrl:"js/eventWizard/views/evtStretchGoal.html",
          controller:"EventWizardController as evtCtrl"
      })
      .state('invites', {
        url:"/wizard/invites",
        templateUrl:"js/eventWizard/views/evtInvites.html",
        controller:"EventWizardController as evtCtrl"
      });
    });

}());





// .state('favors', {
//   url:"/wizard/favors",
//   templateUrl:"js/eventWizard/views/evtFavors.html",
// })
// .state('stretchgoal', {
//   url:"/wizard/strechgoal",
//   templateUrl:"js/eventWizard/views/evtStretchGoal.html"
// })
// .state('invites', {
//   url:"/wizard/invites",
//   templateUrl:"js/eventWizard/views/evtInvites.html"
// });
