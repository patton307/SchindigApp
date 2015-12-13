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
      $scope.newWizPartyPost = function(subtype){
        var item = {partyType: "Christmas", subType: subtype}
        console.log("posting item: ", item);
        EventWizardService.newWizPartyPost(item);
      }
      // EventWizardService.newWizPartyPost(item).then(function(data){
      //   console.log('post', data);
      // });






      // DATE AND TIME PICKER
    var options = {
      date: new Date(),
      mode: 'date', // or 'time'
      minDate: new Date() - 10000,
      allowOldDates: false,
      allowFutureDates: true,
      doneButtonLabel: 'DONE',
      doneButtonColor: '#F2F3F4',
      cancelButtonLabel: 'CANCEL',
      cancelButtonColor: '#000000'
    };


    $scope.showDatePicker = function() {
   $cordovaDatePicker.show(options).then(function(date){
       alert(date);
   });
 };

  //END DATE AND TIME PICKER
});




}());
