(function() {
  'use strict';

  angular
    .module('profile')
    .factory('ProfileService', function($http){


      var getData = function() {
        console.log('profile service')
        return $http.get('http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart2')
      }
      return {
        getData: getData
      }
    })


}());
