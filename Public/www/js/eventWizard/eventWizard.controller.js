(function() {
  'use strict';
  angular
  .module("eventWizard")

  .controller("EventWizardController", function($scope, $state, $http, EventWizardService){
      var vm = this;

      EventWizardService.getWizard().then(function(data){
        console.log('event controler', data);
        $scope.wizardItems = data.data;
      });
      vm.selectParty = function (){
          wizardData = {};
          Event.WizardService.postWizard(wizardData);
        };

  });



}());
