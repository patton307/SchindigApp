(function() {
  'use strict';

  angular
    .module('eventWizard', [
      'ionic'
    ])
    .config(function($stateProvider){

    $stateProvider
      .state('wizard', {
        url:'wizard',
        templateUrl: "js/eventWizard/views/evtNameType.html",
        abstract:true,
        controller:"EventWizardController as EventWizCtrl"

      })
      .state('wizard.name', {
        url:'/name-type',
        view: {
           'wizard-nameType':{
            templateUrl:"js/eventWizard/views/evtSubType.html",
            controller:"EventWizCtrl"
          }
        }

      })
      .state('wizard.subtype', {
        url:"/subtype",
        view: {
          'wizard-subType':{
            templateUrl:"js/eventWizard/views/evtSubType.html",
            controller:"EventWizCtrl"
          }
        }

      })
      .state('wizard.whenwhere', {
        url:"/whenwhere",
        view: {
          'wizard-whenWhere':{
            templateUrl:"js/eventWizard/views/evtSubType.html",
            controller:"EventWizCtrl"
          }
        }
      })
      .state('wizard.favors', {
        url:"/favors",
        view: {
          'wizard-favors':{
            templateUrl:"js/eventWizard/views/evtFavors.html",
            controller:"EventWizCtrl"
          }
        }
      })
      .state('wizard.strechgoal', {
        url:"/strechgoal",
        view: {
          'wizard-strechgoal':{
            templateUrl:"js/eventWizard/views/evtStrechGoal.html",
            controller:"EventWizCtrl"
          }
        }
      })
      .state('wizard.invites', {
        url:"/invites",
        view: {
          'wizard-invites':{
            templateUrl:"js/eventWizard/views/evtInvites.html",
            controller:"EventWizCtrl"
          }
        }
      });
    });

}());
