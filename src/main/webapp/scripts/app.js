'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', []);

fastgrades.controller('FrameworkCtrl', ['$scope', function ($scope) {
	$scope.frameworks = ['Angular', 'JQuery', 'Bootstrap'];
}]);
