'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('app')
	.directive('chat',function(){
		return {
        templateUrl:'/app/layout/directives/chat/chat.template.html',
        restrict: 'E',
        replace: true,
    	}
	});


