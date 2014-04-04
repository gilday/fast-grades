'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', []);

fastgrades.controller('TestCtrl', ['$scope', function ($scope) {
	$scope.greeting = 'it works!';
}]);

fastgrades.controller('ExamCtrl', ['$scope', '$location', '$anchorScroll', function ($scope, $location, $anchorScroll) {

    $scope.answerKey = null;

    $scope.isStepOneComplete = function() {
        return $scope.answerKey !== null;
    };

    $scope.createAnswerKey = function (numQuestions) {
        console.log('creating ' + numQuestions + ' questions');
        $scope.answerKey = new Array(numQuestions);
        for (var i = 0; i < $scope.answerKey.length; i++) {
            $scope.answerKey[i] = { value: null };
        }
        scrollToStepTwo();
    };

    function scrollToStepTwo () {
        $location.hash('step-two');
        $anchorScroll();
    }
}]);