'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('headerNotification',function(){
		return {
        templateUrl:'/app/layout/directives/header/header-notification/header-notification.template.html',
        restrict: 'E',
        replace: true,
    	}
	});


