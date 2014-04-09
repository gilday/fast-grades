'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', ['ngRoute']);

fastgrades.factory('exam', function () {

    var self = {};

    self.isAnswerKeySaved = false;
    self.answerKey = null;

    self.initializeExam = function (numQuestions) {
        console.log('creating ' + numQuestions + ' questions');
        self.answerKey = new Array(numQuestions);
        for (var i = 0; i < self.answerKey.length; i++) {
            self.answerKey[i] = { value: null };
        }
    };

    self.saveAnswerKey = function () {
        self.isAnswerKeySaved = true;
    };

    return self;
});

fastgrades.controller('IntroCtrl', function () {

});

fastgrades.controller('StepOneCtrl', ['$scope', '$location', 'exam', function ($scope, $location, exam) {

    $scope.callInitializeExam = function (numQuestions) {
        exam.initializeExam(numQuestions);
        transitionToStepTwo();
    }

    function transitionToStepTwo () {
        $location.url('step-two');
    }
}]);

fastgrades.controller('StepTwoCtrl', ['$scope', '$location', 'exam', function ($scope, $location, exam) {

    if (!exam.answerKey) {
        throw 'Exam\'s answer key must be initialized by step two';
    }

    $scope.answerKey = exam.answerKey;
    $scope.callSaveAnswerKey = function () {
        exam.saveAnswerKey;
        transitionToStepThree();
    }

    function transitionToStepThree () {
        $location.url('step-three');
    }
}]);

fastgrades.controller('StepThreeCtrl', ['$scope', function ($scope) {


}]);

fastgrades.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'intro.html',
            controller: 'IntroCtrl'
        })
        .when('/step-one', {
            templateUrl: 'step-one.html',
            controller: 'StepOneCtrl'
        })
        .when('/step-two', {
            templateUrl: 'step-two.html',
            controller: 'StepTwoCtrl'
        })
        .when('/step-three', {
            templateUrl: 'step-three.html',
            controller: 'StepThreeCtrl'
        });

    $locationProvider.html5Mode(true);
}]);