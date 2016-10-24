angular.
  module('myApp').
  component('bloodbanklist', {
    template:   	
        '<ul>' +
          '<li ng-repeat="bb in $ctrl.bblist">' +
            '<span>{{bb.name}}</span>' +
            '<p>{{bb.location}}</p>' +
          '</li>' +
        '</ul>',
    controller: function bblistController() {
      this.bblist = [
        {
          name: 'Chicago Foundation',
          location: 'Chicago'
        }, {
          name: 'Redlands Foundation',
          snippet: 'Redlands',
        }, {
          name: 'New York Foundation',
          snippet: 'New York'
        }
      ];
    }
  });