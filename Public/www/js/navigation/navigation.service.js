(function() {
  'use strict';

  angular
    .module('navigation')
    .factory('NavigationService', function($http, $state){
        var test = function(){
          console.log('doggydog');
        };
        return {
          test: test
        };
    });
}());
