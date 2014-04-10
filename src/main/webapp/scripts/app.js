'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', ['ngRoute']);

fastgrades.factory('exam', function () {

    var isAnswerKeySaved = false,
        answerKey = null,
        title = null;

    return {
        answerKey: function(value) {
            if (value) {
                answerKey = value;
            } else {
                return answerKey;
            }
        },
        title: function(value) {
            if (value) {
                title = value;
            } else {
                return title;
            }
        },
        initializeExam: function (numQuestions) {
            console.log('creating ' + numQuestions + ' questions');
            answerKey = new Array(numQuestions);
            for (var i = 0; i < answerKey.length; i++) {
                answerKey[i] = { value: null };
            }
        },
        saveAnswerKey: function () {
            isAnswerKeySaved = true;
        }
    };
});

fastgrades.controller('IntroCtrl', function () {

});

fastgrades.controller('StepOneCtrl', ['$scope', '$location', 'exam', function ($scope, $location, exam) {

    $scope.callInitializeExam = function () {
        exam.initializeExam($scope.numQuestions);
        exam.title($scope.title);
        transitionToStepTwo();
    };

    function transitionToStepTwo () {
        $location.url('step-two');
    }
}]);

fastgrades.controller('StepTwoCtrl', ['$scope', '$location', 'exam', function ($scope, $location, exam) {

    if (!exam.answerKey()) {
        throw 'Exam\'s answer key must be initialized by step two';
    }

    $scope.answerKey = exam.answerKey();
    $scope.callSaveAnswerKey = function () {
        exam.saveAnswerKey();
        transitionToStepThree();
    };

    function transitionToStepThree () {
        $location.url('step-three');
    }
}]);

fastgrades.controller('StepThreeCtrl', ['$scope', 'exam', function ($scope, exam) {
    $scope.examUrl = 'print-exam.html?title=' + exam.title() + '&numQuestions=' + exam.answerKey().length;
}]);

fastgrades.controller('PrintExamCtrl', ['$scope', function ($scope) {
    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    var numQuestions = parseInt(getParameterByName('numQuestions'), 10);
    var title = getParameterByName('title');

    if (!numQuestions) {
        throw 'Query parameter numQuestions is required';
    }
    if (!title) {
        throw 'Query parameter title is required';
    }

    $scope.questions = new Array(numQuestions);
    $scope.title = title;
}]);

fastgrades.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/intro.html',
            controller: 'IntroCtrl'
        })
        .when('/step-one', {
            templateUrl: 'views/step-one.html',
            controller: 'StepOneCtrl'
        })
        .when('/step-two', {
            templateUrl: 'views/step-two.html',
            controller: 'StepTwoCtrl'
        })
        .when('/step-three', {
            templateUrl: 'views/step-three.html',
            controller: 'StepThreeCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
    $locationProvider.html5Mode(false);
}]);