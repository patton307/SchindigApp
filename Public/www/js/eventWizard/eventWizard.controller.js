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
        var item = {subType, partyType};
        console.log("posting item: ", item);
        EventWizardService.newWizPartyPost(item);
      };
      ///POST DATE, TIME AND NAME/////
      $scope.dateAndTimePost = function(date ,title, time){
        var data = {date, time, title};
        console.log("posting item: ", data);
        EventWizardService.newWizPartyPost(item);
      };

      // EventWizardService.newWizPartyPost(item).then(function(data){
      //   console.log('post', data);
      // });
});




}());
