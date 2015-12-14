(function() {
  'use strict';
  angular
    .module('profile')
    .controller('ProfileController', function(
      $scope,
      $cordovaCamera,
      $cordovaVibration,
      ProfileService,
      $cordovaGeolocation,
      $ionicPlatform,
      $cordovaDatePicker,
      $http
    ){
///get the contacts//
$scope.getAllContacts = function() {
  $cordovaContacts.find().then(function(allContacts) { //omitting parameter to .find() causes all contacts to be returned
    $scope.contacts = allContacts;
  }
};

//MAKES PHONE VIBRATE///
      $scope.vibrate = function() {
        $cordovaVibration.vibrate(100);
      };

      var posOptions = {timeout: 10000, enableHighAccuracy: true};
        $cordovaGeolocation
          .getCurrentPosition(posOptions)
          .then(function (position) {
            var lat  = position.coords.latitude
            var long = position.coords.longitude
            $scope.lat = lat;
            $scope.long = long;
          }, function(err) {
            // error
          });


//TAKE PICTURE AND SET IT TO {{PROFILEPICTURE}}//
      var url = 'http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart4';

      $scope.getProfilePicture = function(){
        var profilePicture = $http.get('http://tiny-tiny.herokuapp.com/collections/ng-shoppingcart4')
        .success(function(data){
          $scope.profilePicture = data[1].image;
          console.log(data[5].image);
        });

      }
      $scope.takePicture = function() {
        var options = {
          destinationType: Camera.DestinationType.DATA_URL,
          encodingType: Camera.EncodingType.JPEG
        }
        $cordovaCamera.getPicture({})
          .then(function(data) {
            $scope.profilePicture =  data;
          }, function(error){

          });
      };

       $scope.savePhoto = function(item) {
       var data = {
         'image': item
       }
        $http.post(url, data)
      }

    });




}());
