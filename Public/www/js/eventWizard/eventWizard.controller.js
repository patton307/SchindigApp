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
        EventWizardService.newWizPartyPost(item).success(function(data){
          console.log('newly created party: ', data);
          localStorage.setItem('partyID', data.partyID);
          $state.go('whenwhere');
        });
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
         stretchStatus: stretchStatus,
         stretchGoal: stretchGoal,
         stretchName: stretchName,
         partyID: partyID
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
         console.log(parsed);
         vm.favorArray.push(parsed);
       });
       var partyID = +localStorage.getItem('partyID');
       var data = {
         partyID: partyID,
         favorDump: vm.favorArray
       };
       EventWizardService.updatePartyFavorList(data).success(function(data){
         console.log('favordata', data);
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
                  var stringData = JSON.stringify(result);
                  var parseData = JSON.parse(stringData);
                  $scope.contactName = parseData;
                  vm.contactData = parseData;
               }, function(error){
                 console.log('error', error);
               });
           };


           vm.contactArray = [];
           console.log(vm.contactArray);
           $scope.isChecked = false;
           $scope.pushToContactArray = function(data){
            //  var $element.find('true');
            var myElements = document.getElementsByClassName('true');
             _.each(myElements, function(el,idx,array){
               var parsed = JSON.parse(el.id);
               vm.contactArray.push(parsed);
               console.log(parsed);
             });
          //    var partyID = +localStorage.getItem('partyID');
          //    var data = {
          //      partyID: partyID,
          //      contactDump: contactArray
          //    };
          //    EventWizardService.postInviteData(data).success(function(data){
          //      console.log('new-stretchgoal updated data', data);
          //      $state.go('');
          //  });
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
               var data = {
                 partyID: partyID,
                 contactDump: vm.contactArray
               };
               EventWizardService.postInviteData(data).success(function(data){
                 console.log('new-stretchgoal updated data', data);
                 $state.go('');
               });
             }
             else {
               alert("There was an error");
             }
           });
         };

        // var mappedContactData = _.map(contactData, function(idx, val, arr){
        //   return {inviteName: el.name.formatted, invitePhone: el.phoneNumbers[0].value, inviteEmail: el.emails[0].value};
        // });

      //  $scope.contactInfoForSMS = function(name, phone, email){
      //    var partyID = +localStorage.getItem('partyID');
      //
      //    };

    });
}());
