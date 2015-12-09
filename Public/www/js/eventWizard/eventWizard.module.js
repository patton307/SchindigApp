(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic',
      'ui.router'
    ])

    .config(function($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise('/')
      $stateProvider
        .state('evtWiz', {
          url: '/wizard',
          templateUrl: "js/eventWizard/views/evtNameType.html"
        })
        .state('subType', {
          url: '/subType',
          templateUrl: "js/eventWizard/views/evtSubType.html"
        })
    })

}());
