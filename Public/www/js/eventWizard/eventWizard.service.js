(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http, $state){
      var wizCreateUrl = "http://localhost:8080/party/create";
      var wizUpdateUrl = "http://localhost:8080/party";
      var favorUpdateUrl = "http://localhost8080/party/favor";

      var getWizard = function() {
        return $http.get('http://localhost:8080/wizard');
      };

      var newWizPartyPost = function(wizardData){
        return $http.post( wizardPostUrl, newParty);
      };
      var getOneWizParty = function (wizID){
        return $http.get ("http://localhost:8080/wizard");
      };
      var updateWizData = function (updatedWizData){
        return $http.put(wizUpdateUrl + '/' + updatedWizData.id, updatedWizData);

      };
      var postFavorParty = function (postFavor){
        return $http.put(favorUpdateUrl + '/' );
      };

      return {
        getWizard: getWizard,
        newWizPartyPost: newWizPartyPost,
        updateWizData: updateWizData,
        getOneWizParty: getOneWizParty
      };

    });





}());
