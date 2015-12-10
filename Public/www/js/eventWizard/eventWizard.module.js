(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic'
    ])
    .config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise('/wizard/name')
    $stateProvider
      .state('wizard', {
        url:'/wizard/name-type',
        templateUrl: "js/eventWizard/views/evtNameType.html",
      })
      .state('name', {
        url:'/wizard/subtype',
        templateUrl:"js/eventWizard/views/evtSubType.html",
      })
      .state('whenwhere', {
        url:"/wizard/whenwhere",
        templateUrl:"js/eventWizard/views/evtWhenWhere.html"
      })
      .state('favors', {
        url:"/wizard/favors",
        templateUrl:"js/eventWizard/views/evtFavors.html",
      })
      .state('stretchgoal', {
        url:"/wizard/strechgoal",
        templateUrl:"js/eventWizard/views/evtStrechGoal.html"
      })
      .state('invites', {
        url:"/wizard/invites",
        templateUrl:"js/eventWizard/views/evtInvites.html"
      })
    });

}());
