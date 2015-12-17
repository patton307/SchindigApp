(function() {
  'use strict';
  angular
    .module("eventWizard")
    .controller("EventWizardController", function(
      $scope,
      $http,
      $state,
      $stateParams,
      $cordovaContacts,
      $ionicPlatform,
      EventWizardService,
      $ionicPopup,
      $timeout
    ){
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

      //////showSubtype()//////
      $scope.showSubtype = function(partyType){
        if(partyType.subType[0] === 'null'){
          return
          console.log('null');

        } else {
          console.log(partyType);
          return partyType

        }
      }



      /////POST NEW PARTY/////
      $scope.newWizPartyPost = function(subType, partyType){
        var rawUserID = +localStorage.getItem('userID')
        var item = {
          party: {
            subType: subType,
            partyType: partyType
          },
          userID: rawUserID
        };
        EventWizardService.newWizPartyPost(item)
      };


      ///PATCH DATE, TIME AND NAME/////
    $scope.dateAndTimePost = function(partyDate, partyName){
      var partyID = +localStorage.getItem('partyID');
      console.log('partyId in localstorage', partyID);
      var data = {
        party: {
          partyName: partyName,
          partyID: partyID,
          partyDate: partyDate
        }
      };
      data.party.partyDate = JSON.stringify(data.party.partyDate);
      data.party.partyDate = JSON.parse(data.party.partyDate);
      console.log('updated party data: ', data);
      EventWizardService.updateWizData(data).success(function(updatedWizData){
        console.log('promise return of updated wizdata', updatedWizData);
        $state.go('favors');
      });
    };



     ////STRETCHGOAL PATCH and SCOPES////

     $scope.stretchGoalData = function (stretchStatus, stretchGoal, stretchName){
       var partyID = +localStorage.getItem('partyID');
       console.log(partyID);
       var data = {
         party: {
           stretchStatus: stretchStatus,
           stretchGoal: stretchGoal,
           stretchName: stretchName,
           partyID: partyID
         }
       };
       console.log('updated stretchgoal:', data);
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('new-stretchgoal updated data', updatedWizData);
         $state.go('invites');
       });
     };


     /////FAVORS PATCH/////
     vm.favorArray = [];
     $scope.isChecked = false;
     $scope.pushToFavorArray = function(data){
      //  var $element.find('true');
      var myElements = document.getElementsByClassName('true');
       _.each(myElements, function(el,idx,array){
         var parsed = JSON.parse(el.id);
         vm.favorArray.push(parsed);
       });
       var partyID = +localStorage.getItem('partyID');
       var data = {
         partyID: partyID,
         favorDump: vm.favorArray
       };
       EventWizardService.updatePartyFavorList(data).success(function(data){
         console.log('favordata', data);
         $state.go('stretchgoal');
       });
     };

     /////ADD FAVOR TO DATA/////
     $scope.addFavorToData = function(favor){
       var partyID = +localStorage.getItem('partyID');
       var favorData = {
         favorName: favor,
         partyID: partyID
       };
       EventWizardService.addFavorToData(favorData).success(function(data){
         console.log('added favor to data', data);
       });
     };


       /////GET STATIC FAVORS///////
       EventWizardService.getFavors().success(function(data){
         $scope.favors = data;
       });


      //CORDOVA CONTACTS AND INVITATIONS //
      $scope.getContactList = function() {
                $cordovaContacts
                .find({})
                .then(function(result) {
                  // console.log('get result', result);
                  var stringData = JSON.stringify(result);
                  var parseData = JSON.parse(stringData);
                  $scope.contactName = parseData;
                  console.log($scope.contactName[0].name.formatted);
                  // console.log('dogdoo', $scope.contactName);
                  // console.log('catdog',$scope.contactName[0]);
               }, function(error){
                 console.log('error', error);
               });
           };
       $scope.isChecked = false;
       vm.contactArray = [];
       $scope.pushToContactArray = function(){
          var myElements = document.getElementsByClassName('true');
           _.each(myElements, function(el,idx,array){
             var parsed = JSON.parse(el.id);
             vm.contactArray.push(parsed);
           });
           console.log('this should be the contact array',vm.contactArray[0].name.formatted);

     };



         ///CONTACT DOM STUFF
         $scope.showConfirm = function() {
           var confirmPopup = $ionicPopup.confirm({
             title: 'Send Invitations',
             template: 'Are you ready to send out Invites and Create your Party?'
           });
           confirmPopup.then(function(res){
             if(res){
               var partyID = +localStorage.getItem('partyID');
               var contactData;
               var data;
               vm.contactDataArray=[];

               _.each(vm.contactArray, function(el,idx,array){
                 console.log('what the fuck is this',el.name.formatted);
                //  console.log('name', el.name.formatted);
                //  console.log('phone number', el.phoneNumbers[0].value);
                //  console.log('email',el.emails[0].value);
                contactData = {
                  name: el.name.formatted,
                  phone: el.phoneNumbers[0].value,
                  email: el.emails[0].value
               }
               vm.contactDataArray.push(contactData);
               data = {
                 inviteDump: vm.contactDataArray,
                 party: {
                   partyID: partyID
                 }
               }
             });
               EventWizardService.updateWizData(data).success(function(data){
                 console.log('invite list', data);
               });
             }
             else {
               alert("There was an error");
             }
           });
         };
    });
}());
