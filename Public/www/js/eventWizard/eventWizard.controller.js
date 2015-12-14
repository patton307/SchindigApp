(function() {
  'use strict';
  angular
  .module("eventWizard")

  .controller("EventWizardController", function($scope, $http, $stateParams, $cordovaDatePicker, EventWizardService){
      var vm = $scope;

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
          vm.dogData = data[0];
          console.log('vm data', vm.dogData);
          return vm.dogData;
        });
      };
      console.log('this is what i need', vm.dogData);

      ///POST DATE, TIME AND NAME/////
     $scope.dateAndTimePost = function(partyDate, partyName, partyID){
       var data = {date:partyDate, name:partyName, id:partyID};
       data.date = JSON.stringify(data.date);
       data.date = JSON.parse(data.date);
       console.log('this is the data', data);
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('dogdgo', updatedWizData);
       });
     };


});

}());
