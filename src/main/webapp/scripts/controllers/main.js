'use strict';

define(['app'], function (app) {

  var mainCtrl = function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  };

  app.controller('MainCtrl', ['$scope', mainCtrl]);
  return mainCtrl;
});
