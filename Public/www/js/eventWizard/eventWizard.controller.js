(function() {
  'use strict';
  angular
  .module("eventWizard")

  .controller("EventWizardController", function($scope, EventWizardService){
      var vm = this;
      var getWizard = function() {
        return $http.get('localhost:8080/get-wizard').then(function(data){
          console.log(data);
        })
      // var getWizard = function(data) {
      //   return $http.get('localhost:8080/get-wizard').then(function(data){
      //     console.log(data);
      //   })
      // EventWizardService.getWizard().success(function(wizardItems){
      //   $scope.wizardItems = wizardItems
      //   console.log(wizardItems);
      // })
  });



}());
