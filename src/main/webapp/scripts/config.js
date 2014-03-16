'use strict';

/*jshint unused: vars */
require.config({
  paths: {
    angular: '../../bower_components/angular/angular'
  },
  shim: {
    angular: {
      exports: 'angular'
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
  'app',
  'controllers/main'
], function (angular, app) {
  /* jshint ignore:start */
  var $html = angular.element(document.getElementsByTagName('html')[0]);
  /* jshint ignore:end */
  angular.element().ready(function() {
    angular.resumeBootstrap([app.name]);
  });
});