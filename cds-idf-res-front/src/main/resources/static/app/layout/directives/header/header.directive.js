'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('header',function(){
		return {
        templateUrl:'/app/layout/directives/header/header.template.html',
        restrict: 'E',
        replace: true,
    	}
	});


