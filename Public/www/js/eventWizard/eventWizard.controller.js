(function() {
  'use strict';
  angular
    .module("eventWizard")
    .controller("WizardLandingController", function(
      $scope,
      $http,
      $state,
      $stateParams,
      $ionicPlatform,
      EventWizardService
    ){
      var vm = this;
      ////GET WIZARD DATA////
      EventWizardService.getWizard().then(function(data){
        $scope.wizardItems = data;
        console.log($scope.wizardItems);
        $scope.get = function(nameId) {
          var id = parseInt(nameId.nameId);
          for (var i = 0; i < data.data.length; i++) {
            if (i === id) {
              console.log('wizard data',data.data[i]);
              return data.data[i-1];
            }
          }
        return null;
      };
      $scope.partySubType = $scope.get($stateParams);
      console.log('does this exist',$scope.partySubType);
    });

    /////POST NEW PARTY/////

    $scope.partyType='none';
    $scope.subType='none';
    $scope.getValue = function(value){
      var newVal = JSON.parse(value);
      console.log(newVal);
      $scope.partyType = newVal.partyType;
      $scope.subType = newVal.subType;
      console.log(newVal.subType);
    };
    $scope.getSubValue = function(subValue){
      console.log(subValue);
      console.log('parsed');
      $scope.subTypeVal = subValue;
    };
    $scope.newWizPartyPost = function(partyType, subTypeVal, partyName, description){
      console.log(partyType);
      var rawUserID = +localStorage.getItem('userID');
      var item = {
        party: {
          description: description,
          subType: subTypeVal,
          partyType: partyType,
          partyName: partyName
        },
        userID: rawUserID
      };
      EventWizardService.newWizPartyPost(item).success(function(data){
        console.log('You made a party', data.partyID);
        localStorage.setItem('partyID', data.partyID);
        $state.go('whenwhere');
      });
    };
    })
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



      ///PATCH DATE, TIME AND NAME/////
    $scope.dateAndTimePost = function(partyDate, location, description){
      var partyID = +localStorage.getItem('partyID');
      var data = {
        party: {
          description: description,
          local: location.formatted_address,
          partyID: partyID,
          partyDate: partyDate
        }
      };
      console.log('doomba',data.party.local);


      console.log('dooadfsadfmba',data.party.local);

      data.party.partyDate = JSON.stringify(data.party.partyDate);
      data.party.partyDate = JSON.parse(data.party.partyDate);
      EventWizardService.updateWizData(data).success(function(updatedWizData){
        console.log('date and time post', updatedWizData);
        $state.go('stretchgoal');
      });
    };


     ////STRETCHGOAL PATCH and SCOPES////

     $scope.stretchGoalData = function (stretchGoal, stretchName){
       var byobElements = document.getElementsByClassName('byob');
       var themeElements = document.getElementsByClassName('theme');
       console.log(themeElements);
       console.log(byobElements);
       var byobStatus;
       var themeStatus;
       if(byobElements.length != 0){
         byobStatus = true;
       } else{
         byobStatus = false;
       };
       if(themeElements.length!= 0){
         byobStatus = true;
       } else {
         themeStatus = false;
       }
       console.log(byobStatus);
       var partyID = +localStorage.getItem('partyID');
       var data = {
         party: {
           theme: themeStatus,
           byob: byobStatus,
           stretchGoal: stretchGoal,
           stretchName: stretchName,
           partyID: partyID
         }
       };
       EventWizardService.updateWizData(data).success(function(updatedWizData){
         console.log('success', updatedWizData);
         $state.go('favors');
       });
     };


    })



    .controller('ContactsController', function(
      $scope,
      $http,
      $state,
      $stateParams,
      EventWizardService,
      $cordovaContacts,
      $ionicPopup
    ){
      var vm = this;

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
        $scope.isChecked = false;

        ////PUSH TO CONTACT ARRAY////
        vm.contactArray = [];
        $scope.pushToContactArray = function(){
           var myElements = document.getElementsByClassName('true');
            _.each(myElements, function(el,idx,array){
              var parsed = JSON.parse(el.id);
              vm.contactArray.push(parsed);
            });
        };
        ///CONTACT DOM STUFF
        vm.contactDataArray=[];
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
              _.each(vm.contactArray, function(el,idx,array){
               contactData = {
                 name: el.name.formatted,
                 phone: el.phoneNumbers[0].value
              };
              vm.contactDataArray.push(contactData);
              data = {
                inviteDump: vm.contactDataArray,
                party: {
                  partyID: partyID
                }
              };
            });
              EventWizardService.updateWizData(data).then(function(data){
                console.log('contacts have been sent', data);
                $state.go('home');
              });
            }
            else {
              alert("There was an error");
            }
          });
        };
    })

    .controller('FavorsController', function(
      $scope,
      $http,
      $state,
      $stateParams,
      EventWizardService
    ){
      var vm = this;
      ////GET FAVORS////
       EventWizardService.getFavors().then(function(data){
         $scope.favors = data.data;
       });

      /////FAVORS PATCH/////
      vm.favorArray = [];
      $scope.favorCheck = false;
      $scope.pushToFavorArray = function(data){
       var myElements = document.getElementsByClassName('yes');
        _.each(myElements, function(el,idx,array){
          var parsed = JSON.parse(el.id);
          vm.favorArray.push(parsed);
        });
        console.log('this is my array',vm.favorArray);
        var partyID = +localStorage.getItem('partyID');
        var rawUserID = +localStorage.getItem('userID');
        var data = {
          userID: rawUserID,
          partyID: partyID,
          favorDump: vm.favorArray
        };
        EventWizardService.updatePartyFavorList(data).success(function(data){
          console.log('the favor list has been updated', data);
          $state.go('invites');
        });
      };


      /////ADD FAVOR TO DATA/////
      $scope.addFavorToData = function(favorData){
        var partyID = +localStorage.getItem('partyID');
        var favorData = {
          favor: {
            favorName: 'favorData'
          },
          partyID: partyID
        };
        console.log(favorData);
        EventWizardService.addFavorToData(favorData);
      };
    });
}());
