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
          return console.log('null');
        } else {
          console.log(partyType);
          return partyType;

        }
      };



      /////POST NEW PARTY/////
      $scope.newWizPartyPost = function(subType, partyType){
        var rawUserID = +localStorage.getItem('userID');
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
      $scope.dateAndTimePost = function(partyDate, location){
        var partyID = +localStorage.getItem('partyID');
        var data = {
          party: {
            // location: partyLocation,
            location: location,
            partyID: partyID,
            partyDate: partyDate
          }
        };
        data.party.partyDate = JSON.stringify(data.party.partyDate);
        data.party.partyDate = JSON.parse(data.party.partyDate);
        EventWizardService.updateWizData(data).success(function(updatedWizData){
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


     ////GET FAVORS////
     EventWizardService.getFavors().then(function(data){
       $scope.favors = data.data;
     });

    /////FAVORS PATCH/////
    vm.favorArray = [];
    $scope.isChecked = false;
    $scope.pushToFavorArray = function(data){
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
      EventWizardService.updatePartyFavorList(data);
      $state.go('stretchgoal');
    };

    /////ADD FAVOR TO DATA/////
    $scope.addFavorToData = function(favor){
      var partyID = +localStorage.getItem('partyID');
      var favorData = {
        favorName: favor,
        partyID: partyID
      };
      EventWizardService.addFavorToData(favorData);
    };

      //CORDOVA CONTACTS AND INVITATIONS //

      $scope.getContactList = function() {
                $cordovaContacts
                .find({})
                .then(function(result) {
                  var stringData = JSON.stringify(result);
                  var parseData = JSON.parse(stringData);
                  $scope.contactName = parseData;
               }, function(error){
                 console.log('error', error);
               });
           };
           vm.contactArray = [];
           $scope.isChecked = false;
           $scope.pushToContactArray = function(data){
            //  var $element.find('true');
            var myElements = document.getElementsByClassName('true');
             _.each(myElements, function(el,idx,array){
               vm.contactArray.push(el.id);
             });
             console.log('parsed',vm.contactArray);

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
              //  var data = {
              //    partyID: partyID,
              //    contactDump: vm.contactArray
              //  };
               var contactData = {
                 party: {
                   partyID:partyID
                 },
                 invite: {

                 }
               };
               EventWizardService.updateWizData(contactData).success(function(data){
                 console.log('invite list', data);
                 $state.go('');
               });
             }
             else {
               alert("There was an error");
             }
           });
         };
    });

}());
