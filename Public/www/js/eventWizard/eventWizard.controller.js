(function() {
  'use strict';
  angular
    .module("eventWizard")

    .controller("EventWizardController", function($scope, $http, $state, $stateParams, $cordovaDatePicker, EventWizardService){
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
        EventWizardService.newWizPartyPost(item).success(function(data){
          console.log('newly created party: ', data);
          localStorage.setItem('partyID', data.partyID);
          $state.go('whenwhere');
        });
      };

        ///POST DATE, TIME AND NAME/////
       $scope.dateAndTimePost = function(partyDate, partyName){
         var partyID = +localStorage.getItem('partyID');
         console.log('partyId in localstorage', partyID);
         var data = {
           partyDate: partyDate,
           partyName: partyName,
           partyID: partyID
         };
         data.partyDate = JSON.stringify(data.partyDate);
         data.partyDate = JSON.parse(data.partyDate);
         console.log('updated party data: ', data);
         EventWizardService.updateWizData(data).success(function(updatedWizData){
           console.log('promise return of updated wizdata', updatedWizData);
           $state.go('favors');
         });
       };


     //STRETCHGOAL POST and SCOPES//


     $scope.stretchGoalData = function (stretchStatus, stretchGoal, stretchName){
       var partyID = +localStorage.getItem('partyData.partyID');
       var partyName = localStorage.getItem('partyData.partyName');
       var partyDate = localStorage.getItem('partyData.partyDate');
       var data = {
         stretchStatus,
         stretchGoal,
         stretchName,
         partyID,
         partyDate,
         partyName
       };
       console.log('updated stretchgoal:', data);
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('new-stretchgoal', updatedWizData);
         localStorage.setItem('stretchStatus', 'stretchGoal', 'stretchName');
         $state.go('invites');
       });
     };



       /////GET STATIC FAVORS///////
       EventWizardService.getFavors().success(function(data){
         $scope.favors = data;
         console.log(data);
       });

      ////POST FAVORS TO PARTY ///////
      $scope.favorPost = function(){
        $state.go('stretchgoal');
      };
    });
}());
