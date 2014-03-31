'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', []);

fastgrades.controller('TestCtrl', ['$scope', function ($scope) {
	$scope.greeting = 'it works!';
}]);
