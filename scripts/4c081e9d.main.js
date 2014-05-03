"use strict";var fastgrades=angular.module("fastgrades",["ngRoute","angularFileUpload","config"]);fastgrades.factory("exam",function(){var a=!1,b=null,c=null,d=null;return{answerKey:function(a){return a?void(b=a):b},title:function(a){return a?void(c=a):c},initializeExam:function(a){b=new Array(a);for(var c=0;c<b.length;c++)b[c]={value:null}},saveAnswerKey:function(){a=!0},studentMarks:function(a){return a?void(d=a):d}}}),fastgrades.controller("IntroCtrl",function(){}),fastgrades.controller("StepOneCtrl",["$scope","$location","exam",function(a,b,c){function d(){b.url("step-two")}a.callInitializeExam=function(){c.initializeExam(a.numQuestions),c.title(a.title),d()}}]),fastgrades.controller("StepTwoCtrl",["$scope","$location","exam",function(a,b,c){function d(){b.url("step-three")}if(!c.answerKey())throw"Exam's answer key must be initialized by step two";a.answerKey=c.answerKey(),a.callSaveAnswerKey=function(){c.saveAnswerKey(),d()}}]),fastgrades.controller("StepThreeCtrl",["$scope","exam",function(a,b){a.examUrl="print-exam.html?title="+b.title()+"&numQuestions="+b.answerKey().length}]),fastgrades.controller("StepFourCtrl",["$scope","$location","$upload","exam","api",function(a,b,c,d,e){a.file=null,a.onFileSelect=function(b){if(b.length<=0)throw"Files must not be empty list";a.file=b[0]},a.onSubmit=function(){if(!a.file)throw"File was never set";a.upload=c.upload({url:e+"/files",method:"POST",file:a.file,fileFormDataName:"exam"}).progress(function(a){console.log("percent: "+parseInt(100*a.loaded/a.total))}).success(function(a){d.studentMarks(a),b.url("grade-report")})}}]),fastgrades.controller("GradeReportCtrl",["$scope","exam",function(a,b){function c(a){return a[0]?"A":a[1]?"B":a[2]?"C":a[3]?"D":null}a.report={title:b.title(),problems:[]};for(var d=0;d<b.answerKey().length;d++){var e={answer:b.answerKey()[d].value,mark:c(b.studentMarks()[d])};e.isCorrect=function(a){return function(){return console.log("is correct? "+a.answer+" === "+a.mark),a.answer===a.mark}}(e),a.report.problems[d]=e}for(var f=0,d=0;d<a.report.problems.length;d++){var e=a.report.problems[d];e.isCorrect()&&f++}a.report.score=f/a.report.problems.length*100}]),fastgrades.controller("PrintExamCtrl",["$scope",function(a){function b(a){a=a.replace(/[\[]/,"\\[").replace(/[\]]/,"\\]");var b=new RegExp("[\\?&]"+a+"=([^&#]*)"),c=b.exec(location.search);return null==c?"":decodeURIComponent(c[1].replace(/\+/g," "))}var c=parseInt(b("numQuestions"),10),d=b("title");if(!c)throw"Query parameter numQuestions is required";if(!d)throw"Query parameter title is required";a.questions=new Array(c),a.title=d}]),fastgrades.config(["$routeProvider","$locationProvider",function(a,b){a.when("/",{templateUrl:"views/intro.html",controller:"IntroCtrl"}).when("/step-one",{templateUrl:"views/step-one.html",controller:"StepOneCtrl"}).when("/step-two",{templateUrl:"views/step-two.html",controller:"StepTwoCtrl"}).when("/step-three",{templateUrl:"views/step-three.html",controller:"StepThreeCtrl"}).when("/step-four",{templateUrl:"views/step-four.html",controller:"StepFourCtrl"}).when("/grade-report",{templateUrl:"views/grade-report.html",controller:"GradeReportCtrl"}).otherwise({redirectTo:"/"}),b.html5Mode(!1)}]);