'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('notifications',function(){
		return {
        templateUrl:'/app/layout/directives/notifications/notifications.template.html',
        restrict: 'E',
        replace: true,
    	}
	});


