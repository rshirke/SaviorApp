var myApp = angular.module('helloworld', ['ui.router','plunker']);

myApp.config(function($stateProvider) {
  var helloState = {
    name: 'hello',
    url: '/acceptor',
    template: "<form method=\"post\" action=\"/postlogin\"> <div id=\"form1\" class=\"header\">Please select the blood Group and Quantity<br></br> <input placeholder=\"Blood Group\" type=\"mail\" id=\"bbgroup\" /><br></br> <input placeholder=\"Quantity\" type=\"mail\" id=\"bbqty\" /><br></br>  </div> <div id=\"submit1\" class=\"header\"><input type=\"submit\" id=\"button1\" value=\"search\"/></div></form>"
  }

  var aboutState = {
    name: 'about',
    url: '/donor',
    template: '<h3>Donation of the blood page..</h3>'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(aboutState);
});
angular.bootstrap(document.getElementById("wrapper"), ['helloworld']);