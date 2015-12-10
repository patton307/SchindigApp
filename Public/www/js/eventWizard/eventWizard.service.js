(function() {
  'use strict';

  angular
    .module('eventWizard')

    .factory('EventWizardService', function($http){

      var getData = function() {
        console.log('wizard service');
        return $http.get('http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart2');
      };

      return {
        getData: getData
      };
    });





}());
