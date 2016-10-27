var app = angular.module('plunker', []);

app.factory('myService', function($http) {
  var promise;
  var myService = {
    async: function() {
      if ( !promise ) {
        // $http returns a promise, which has a then function, which also returns a promise
        promise = $http.get('/users.json').then(function (response) {
          // The then function here is an opportunity to modify the response
          console.log(response);
          // The return value gets picked up by the then in the controller.
          return response.data;
        });
      }
      // Return the promise to the controller
      return promise;
    }
  };
  return myService;
});

app.controller('MainCtrl', function( myService,$scope) {
  $scope.clearData = function() {
    $scope.data = {};
  };
  $scope.getData = function() {
    // Call the async method and then do stuff with what is returned inside our own then function
    myService.async().then(function(d) {
      $scope.data = d;
    });
  };
});
