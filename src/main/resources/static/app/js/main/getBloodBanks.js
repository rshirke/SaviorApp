var myApp = angular.module('helloworld', ['ui.router']);

myApp.config(function($stateProvider) {
	var helloState = {
			name: 'hello',
			url: '/acceptor',
			/*template: "<form><div class=\"form-group\"><input type=\"text\" class=\"form-control\" id=\"bloodGroup\" placeholder=\"blood group type\" ng-model=\"bdata.bType\"></div>" +
					"<div class=\"form-group\"><label>Quantity</label><input type=\"text\" class=\"form-control\" id=\"bloodqty\" placeholder=\"quantity\" ng-model=\"bdata.qty\"></div>" +
					"<button class=\"btn btn-default\" ng-click=\"getBanksData()\">Search Banks</button></form> +
					"<div id=\"table1\">{{bdata.qty}}</div>" +
					"<div id=\"map1\"> {{bdata.bType}}</div>" */
			
		/*	template: '<form method=\"POST\" action="/bloodbanks/getallbb1"><div class=\"form-group\"><input type=\"text\" class=\"form-control\" id=\"bloodGroup\" placeholder=\"blood group type\" name=\"bgroup\"></div>" +
            "<div class=\"form-group\"><input type=\"text\" class=\"form-control\" id=\"bloodqty\" placeholder=\"quantity\" name=\"bquantity\"></div>" +
            "<button type=\"submit\" class=\"btn btn-default\">Search</button></form>' */
			
            template: "<div id=\"testid\" ng-controller=\"MainCtrl\"><form id=\"searchBanks\"><div class=\"form-group\"><input type=\"text\" class=\"form-control\" id=\"bloodGroup\" placeholder=\"blood group type\" name=\"bgroup\" ng-model=\"bdata.bType\"></div>" +
            "<div class=\"form-group\"><input type=\"text\" class=\"form-control\" id=\"bloodqty\" placeholder=\"quantity\" name=\"bquantity\" ng-model=\"bdata.qty\></div>" +
            "<button type=\"submit\" ng-click=\"getBanksData()\">Search</button></form>" +
            "<div id=\"table1\"></div> {{bdata.bType}}" +
            "<div id=\"map1\"> </div></div>{{bdata.qty}}"
	}

	var aboutState = {
			name: 'about',
			url: '/donor',
			template: '<h3>Donation of the blood page..</h3>'
	}

	$stateProvider.state(helloState);
	$stateProvider.state(aboutState);
});


myApp.factory('myService', function($http) {
	var promise;
	var myService = {
			async: function() {
				if ( !promise ) {
					// $http returns a promise, which has a then function, which also returns a promise
					promise = $http.get('/users.json').then(function (response) {
						// then function here is an opportunity to modify the response
						console.log(response);
						// The return value gets picked up by then in the controller.
						return response.data;
					});
				}
				// Return the promise to the controller
				return promise;
			}
	};
	return myService;
});


myApp.factory('bbsearchSvc', function($http) {
	var promise;
	var bbsearchSvc = {
			async: function() {
				if ( !promise ) {
					// $http returns a promise, which has a then function, which also returns a promise
					promise = $http.get('/bloodbanks/getallbb').then(function (response) {
						// then function here is an opportunity to modify the response
						console.log(response);
						// The return value gets picked up by then in the controller.
						return response.data;
					});
				}
				// Return the promise to the controller
				return promise;
			}
	};
	return bbsearchSvc;
});

/*
myApp.controller('bbSearchCtrl',  function($scope, $http) {
	
	console.log("1");
	 $scope.data = {};
	 
	 $scope.submitForm = function() {
		 
		 console.log("2");
	        // Posting data to php file
	        $http({
	          method  : 'POST',
	          url     : '/bloodbanks/getallbb1',
	          data1    : $scope.data, 
	          headers : createAuthorizationTokenHeader(),
	          //{'Content-Type': 'application/x-www-form-urlencoded'} 
	         })
	          .success(function(data1) {
	            
	              $scope.banks = data1,
	         
	          });
	        }; 
});*/

myApp.controller('MainCtrl', function( myService, bbsearchSvc, $scope) {
	$scope.clearData = function() {
		$scope.data = {};
	};
	$scope.getData = function() {
		// Call the async method and then do stuff with what is returned inside our own then function
		myService.async().then(function(d) {
			$scope.data = d;
		});
	};
	
		$scope.getBanksData = function() {
		
		console.log($scope.bdata.qty);
		console.log($scope.bdata.bType);
		
		// Call the async method and then do stuff with what is returned inside our own then function
		bbsearchSvc.async().then(function(d) {
			$scope.data = d;
		});
	};
});