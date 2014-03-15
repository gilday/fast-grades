/*jshint unused: vars */
require.config({
  paths: {
    angular: '../../bower_components/angular/angular',
    angularMocks: '../../bower_components/angular-mocks/angular-mocks',
    text: '../../bower_components/requirejs-text/text'
  },
  shim: {
    'angular' : {'exports' : 'angular'},
    'angularMocks': {
      deps:['angular'],
      'exports':'angular.mock'
    }
  },
  priority: [
    'angular'
  ]
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = 'NG_DEFER_BOOTSTRAP!';

require([
  'angular',
  'app'
], function(angular, app) {
  'use strict';
  /* jshint ignore:start */
  var $html = angular.element(document.getElementsByTagName('html')[0]);
  /* jshint ignore:end */
  angular.element().ready(function() {
    angular.resumeBootstrap([app.name]);
  });
});