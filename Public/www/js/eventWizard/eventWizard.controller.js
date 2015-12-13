(function() {
  'use strict';
  angular
  .module("eventWizard")

  .controller("EventWizardController", function($scope, $http, $stateParams, $cordovaDatePicker, EventWizardService){
      var vm = this;
      ////GET WIZARD DATA////

      EventWizardService.getWizard().then(function(data){
        $scope.wizardItems = data;
        $scope.get = function(nameId) {
          var id = parseInt(nameId.nameId);
          for (var i = 0; i < data.data.length; i++) {
            if (i === id) {
              return data.data[i-1];
            }
          }
          return null;
        };
        $scope.partySubType = $scope.get($stateParams);
      });

      /////POST NEW PARTY/////
      $scope.newWizPartyPost = function(subType, partyType){
        var item = {subType, partyType}
        EventWizardService.newWizPartyPost(item).success(function(data){
          console.log('promise return', data);
        });
      }

});




}());
