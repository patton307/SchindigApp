(function() {
  'use strict';

  angular
    .module('eventWizard')
    .factory('EventWizardService', function($http){

      var getWizard = function() {
        return $http.get('http://localhost:8080/get-wizard');
      };

      var postWizard = function(){
        return $http.post('http://localhost:8080/post-wizard', newParty);
      };

      return {
        getWizard: getWizard,
        postWizard: postWizard
      };
    });





}());
