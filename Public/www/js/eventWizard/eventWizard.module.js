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
        controller: "EventWizardController"
      })
      .state('whenwhere', {
        url:"/wizardwhenwhere",
        templateUrl:"js/eventWizard/views/evtWhenWhere.html"
      })
      .state('favors', {
        url:"/wizard/favors",
        templateUrl:"js/eventWizard/views/evtFavors.html",
      })
      .state('stretchgoal', {
        url:"/wizard/strechgoal",
        templateUrl:"js/eventWizard/views/evtStretchGoal.html"
      })
      .state('invites', {
        url:"/wizard/invites",
        templateUrl:"js/eventWizard/views/evtInvites.html"
      });
    });

}());
