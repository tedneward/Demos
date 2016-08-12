'use strict';

/**
 * @ngdoc overview
 * @name meanAngularApp
 * @description
 * # meanAngularApp
 *
 * Main module of the application.
 */
angular
  .module('meanAngularApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/persons', {
        templateUrl: 'views/persons.html',
        controller: 'PersonCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
