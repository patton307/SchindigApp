(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http, $state){
      wizCreateUrl = "http://localhost:8080/party/create";
      wizUpdateUrl = "http://localhost:8080/party";
      favorUpdateUrl = "http://localhost8080/party/favor";

      var getWizard = function() {
        return $http.get('http://localhost:8080/wizard');
      };

      var newWizPartyPost = function(wizardData){
        return $http.post( wizardPostUrl, newParty);
      };
      var getOneWizParty = function (wizID){
        return $http.get ("http://localhost:8080/wizard/");
      };
      var updateWizData = function (updatedWizData){
        return $http.put(wizUpdateUrl + '/' + updatedWizData.id, updatedWizData);

      };
      var postFavorParty = function (postFavor){
        return $http.put(favorUpdateUrl + '/' );
      };

      return {
        getWizard: getWizard,
        postWizard: postWizard,
        updateWizData: updateWizData,
        getOneWizParty: getOneWizParty
      };
    });





}());
