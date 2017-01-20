'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('timeline',function() {
    return {
        templateUrl:'/app/layout/directives/timeline/timeline.template.html',
        restrict: 'E',
        replace: true,
    }
  });
