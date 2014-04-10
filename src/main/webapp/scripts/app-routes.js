var fastgrades = angular.module('fastgrades');

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