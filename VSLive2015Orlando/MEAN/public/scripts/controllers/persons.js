'use strict';

/**
 * @ngdoc function
 * @name meanAngularApp.controller:MainCtrl
 * @description
 * # PersonCtrl
 * Controller of the meanAngularApp
 */
angular.module('meanAngularApp')
  .controller('PersonCtrl', ['$http','$log'],
  function ($http,$log) {
    
    $http.get("/api/v1/persons").then(function success(response) {
        $log.info(response);
      },
      function error(response) {
        $log.info(response);
      });
    
    $scope.persons = [
      { "_id": 1, "firstName": "Fred", "lastName": "FLintstone"}
    ];
  });
