(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http, $state){
      var wizCreateUrl = "http://localhost:8080/party/create";
      var wizUpdateUrl = "http://localhost:8080/party/update";
      var favorUpdateUrl = "http://localhost:8080/party/favor";
      var favorGetUrl = "http://localhost:8080/catalog";
      var wizardData = {};

      var getWizard = function() {
        return $http.get('http://localhost:8080/wizard');
      };
      var newWizPartyPost = function(item){
        return $http.post(wizCreateUrl, item);
      };
      var getOneWizParty = function (wizID){
        return $http.get("http://localhost:8080/wizard");
      };
      var updateWizData = function(updatedWizData){
        return $http.put(wizUpdateUrl, updatedWizData);
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
