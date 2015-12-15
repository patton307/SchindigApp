(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http, $state){
      var ip = 'http://10.0.10.32';
      var wizCreateUrl = ip + ":8080/party/create";
      var wizUpdateUrl = ip + ":8080/party/update";
      var favorUpdateUrl = ip + ":8080/party/favor";
      var favorGetUrl = ip + ":8080/favor";
      var getWizardUrl = ip + ":8080/wizard"
      var wizardData = {};

      var getWizard = function() {
        return $http.get(getWizardUrl);
      };
      var newWizPartyPost = function(item){
        return $http.post(wizCreateUrl, item);
      };
      var getOneWizParty = function (wizID){
        return $http.get("http://localhost:8080/wizard");
      };
      var updateWizData = function(updatedWizData){
        return $http.patch(wizUpdateUrl, updatedWizData);
      };
      var postFavorParty = function (postFavor){
        return $http.put(favorUpdateUrl + '/' );
      };
      var getFavors = function () {
        return $http.get(favorGetUrl);
      };

      return {
        getWizard: getWizard,
        newWizPartyPost: newWizPartyPost,
        updateWizData: updateWizData,
        getOneWizParty: getOneWizParty,
        getFavors: getFavors
      };

    });





}());
