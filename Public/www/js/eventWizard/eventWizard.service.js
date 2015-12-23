(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http, $state){
      var ip = 'http://localhost';
      var wizCreateUrl = ip + ":8080/party/create";
      var wizUpdateUrl = ip + ":8080/party/update";
      var favorUpdateUrl = ip + ":8080/party/favor";
      var favorGetUrl = ip + ":8080/favor";
      var addFavorToDataUrl = ip + ":8080/favor/save";
      var getWizardUrl = ip + ":8080/wizard";
      var invitePostUrl = ip + ":8080/party/update";

      var getWizard = function() {
        return $http.get(getWizardUrl);
      };
      var newWizPartyPost = function(item){
        return $http.post(wizCreateUrl, item);
      };
      var getOneWizParty = function (wizID){
        return $http.get(getWizardUrl);
      };
      var updateWizData = function(updatedWizData){
        return $http.patch(wizUpdateUrl, updatedWizData)
          .success(function(data){
        });
      };
      var addFavorToData = function(favorData) {
        return $http.post(addFavorToDataUrl, favorData);
      };
      var updateFavorData = function(updatedFavorData){
        return $http.patch(wizUpdateUrl, updatedFavorData);
      };
      var updatePartyFavorList = function (data){
        return $http.post(favorUpdateUrl, data);
      };
      var getFavors = function () {
        return $http.get(favorGetUrl);
      };
      var postInviteData = function(inviteData){
        return $http.patch(invitePostUrl, inviteData);
      };

      return {
        getWizard: getWizard,
        newWizPartyPost: newWizPartyPost,
        updateWizData: updateWizData,
        getOneWizParty: getOneWizParty,
        getFavors: getFavors,
        updateFavorData: updateFavorData,
        addFavorToData: addFavorToData,
        postInviteData: postInviteData,
        updatePartyFavorList: updatePartyFavorList
      };

    });





}());
