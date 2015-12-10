(function() {
  'use strict';

  angular
    .module('eventWizard')

    .factory('EventWizardService', function($http){
      var getWizard = function(data) {
        return $http.get('localhost:8080/get-wizard').then(function(data){
          console.log(data);
        })
        // console.log($http.get('../get-wizard'));
        // return $http.get('../get-wizard')
      }

      var getData = function() {
        console.log('wizard service')
        console.log($http.get('http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart2'));
        return $http.get('http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart2')
      }
      return {
        getData: getData,
        getWizard: getWizard
      }
    });




}());
