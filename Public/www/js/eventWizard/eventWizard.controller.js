(function() {
  'use strict';
  angular
    .module("eventWizard")

<<<<<<< HEAD
    .controller("EventWizardController", function($scope, $http, $state, $stateParams, $cordovaContacts, EventWizardService){
=======
    .controller("EventWizardController", function($scope, $http, $state, $stateParams, $cordovaDatePicker, EventWizardService){
>>>>>>> ba355c8bea43229dcdb3d27fbf4953700415e696
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
        var item = {subType: subType, partyType: partyType};
        EventWizardService.newWizPartyPost(item).success(function(data){
          console.log('newly created party: ', data);
          localStorage.setItem('partyID', data.partyID);
          $state.go('whenwhere');
        });
      };

<<<<<<< HEAD
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
       var partyID = +localStorage.getItem('partyID');
       var data = {
           stretchStatus,
           stretchGoal,
           stretchName,
           partyID
       };
       console.log('updated stretchgoal:', data);
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('new-stretchgoal', updatedWizData);
=======
      ///POST DATE, TIME AND NAME/////
    $scope.dateAndTimePost = function(partyDate, partyName){
      var partyID = +localStorage.getItem('partyID');
      console.log('partyId in localstorage', partyID);
      var data = {
        partyName: partyName,
        partyID: partyID,
        partyDate: partyDate
      };
      data.partyDate = JSON.stringify(data.partyDate);
      data.partyDate = JSON.parse(data.partyDate);
      console.log('updated party data: ', data);
      EventWizardService.updateWizData(data).success(function(updatedWizData){
        console.log('promise return of updated wizdata', updatedWizData);
        $state.go('favors');
      });
    };



     ////STRETCHGOAL POST and SCOPES////

     $scope.stretchGoalData = function (stretchStatus, stretchGoal, stretchName){
       var partyID = +localStorage.getItem('partyID');
       console.log(partyID);
       var data = {
         stretchStatus: stretchStatus,
         stretchGoal: stretchGoal,
         stretchName: stretchName,
         partyID: partyID
       };
       console.log('updated stretchgoal:', data);
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('new-stretchgoal updated data', updatedWizData);
>>>>>>> ba355c8bea43229dcdb3d27fbf4953700415e696
         $state.go('invites');
       });
     };



       /////GET STATIC FAVORS///////
       EventWizardService.getFavors().success(function(data){
         $scope.favors = data;
       });

      ////POST FAVORS TO PARTY ///////
      $scope.favorPost = function(){
        $state.go('stretchgoal');
      };

      //CORDOVA CONTACTS AND INVITATIONS //
      $scope.getAllContacts = function (){
        $cordovaContacts.then(function(allContacts) { //omitting parameter to .find() causes all contacts to be returned
          $scope.contacts = allContacts;
          console.log(allContacts);
        });
      };

    });
}());
