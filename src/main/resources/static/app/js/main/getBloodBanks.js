var myApp = angular.module('helloworld', ['ui.router']);

myApp.config(function($stateProvider) {
	var helloState = {
			name: 'hello',
			url: '/acceptor',
			template: "<form method=\"post\" action=\"/postlogin\"> <div id=\"form1\" class=\"header\">Please select the blood Group and Quantity<br></br> <input placeholder=\"Blood Group\" type=\"mail\" id=\"bbgroup\" /><br></br> <input placeholder=\"Quantity\" type=\"mail\" id=\"bbqty\" /><br></br>  </div> <div id=\"submit1\" class=\"header\"><input type=\"submit\" id=\"button1\" value=\"search\"/></div></form> <div id=\"getUsersData\" ng-controller=\"MainCtrl\"> "
				+								"<button ng-click=\"getData()\">Get Data</button>" 
				+								"<button ng-click=\"clearData()\">Clear Data</button>"
				+								"<pre>{{ data | json }}</pre>"
				+							"</div>"
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

myApp.controller('MainCtrl', function( myService,$scope) {
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