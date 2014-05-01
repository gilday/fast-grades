'use strict';
/*jshint unused: vars */

/**
 * Define main module
 */
var fastgrades = angular.module('fastgrades', ['ngRoute', 'angularFileUpload', 'config']);

fastgrades.factory('exam', function () {

    var isAnswerKeySaved = false,
        answerKey = null,
        title = null,
        studentMarks = null;

    return {
        answerKey: function (value) {
            if (value) {
                answerKey = value;
            } else {
                return answerKey;
            }
        },
        title: function (value) {
            if (value) {
                title = value;
            } else {
                return title;
            }
        },
        initializeExam: function (numQuestions) {
            answerKey = new Array(numQuestions);
            for (var i = 0; i < answerKey.length; i++) {
                answerKey[i] = { value: null };
            }
        },
        saveAnswerKey: function () {
            isAnswerKeySaved = true;
        },
        studentMarks: function (value) {
            if (value) {
                studentMarks = value;
            } else {
                return studentMarks;
            }
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

fastgrades.controller('StepFourCtrl', ['$scope', '$location', '$upload', 'exam', 'api', function ($scope, $location, $upload, exam, api) {

    $scope.file = null;

    $scope.onFileSelect = function (files) {
        if (files.length <= 0) throw 'Files must not be empty list';
        $scope.file = files[0];
    };

    $scope.onSubmit = function () {
        if (!$scope.file) throw 'File was never set';
        $scope.upload = $upload.upload({
            url: api + '/files',
            method: 'POST',
            // withCredentials: true,
            // data: {myObj: $scope.myModelObj},
            file: $scope.file, // or list of files: $files for html5 only
            /* set the file formData name ('Content-Desposition'). Default is 'file' */
            fileFormDataName: 'exam', //or a list of names for multiple files (html5).
            /* customize how data is added to formData. See #40#issuecomment-28612000 for sample code */
            //formDataAppender: function(formData, key, val){}
            })
        .progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
        }).success(function(data, status, headers, config) {
            // file is uploaded successfully
            exam.studentMarks(data);
            $location.url('grade-report');
        });
    };

}]);

fastgrades.controller('GradeReportCtrl', ['$scope', 'exam', function ($scope, exam) {

    function booleanArrayLetterMark(bools) {
        if (bools[0]) return 'A';
        if (bools[1]) return 'B';
        if (bools[2]) return 'C';
        if (bools[3]) return 'D';
        return null;
    }

    $scope.report = {
        title: exam.title(),
        problems: []
    };
    for (var i = 0; i < exam.answerKey().length; i++) {
        var problem = {
            answer: exam.answerKey()[i].value,
            mark: booleanArrayLetterMark(exam.studentMarks()[i])
        };
        problem.isCorrect = function() {
            return problem.answer === problem.mark;
        };
        $scope.report.problems[i] = problem;
    }
    var correct = 0;
    for (var i = 0; i < $scope.report.problems.length; i++) {
        var problem = $scope.report.problems[i];
        if (problem.answer === problem.mark) {
            correct++;
        }
    }
    $scope.report.score = (correct / $scope.report.problems.length) * 100;
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
        .when('/step-four', {
            templateUrl: 'views/step-four.html',
            controller: 'StepFourCtrl'
        })
        .when('/grade-report', {
            templateUrl: 'views/grade-report.html',
            controller: 'GradeReportCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
    $locationProvider.html5Mode(false);
}]);