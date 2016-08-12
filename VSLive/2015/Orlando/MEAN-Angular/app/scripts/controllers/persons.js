'use strict';

/**
 * @ngdoc function
 * @name meanAngularApp.controller:MainCtrl
 * @description
 * # PersonCtrl
 * Controller of the meanAngularApp
 */
angular.module('meanAngularApp')
  .controller('PersonCtrl', ['$http','$log','$scope'],
  function ($http,$log,$scope) {
    
    var Persons = { };
    Persons.query = function(pid, successCallback, errorCallback) {
      $http.get("/api/v1/persons").then(successCallback, errorCallback});
    }
    Persons.update = function(pid, person, successCallback, errorCallback) {
      $http.put("/api/v1/persons/" + pid, person).then(successCallback, errorCallback);
    }
    
    
  });
