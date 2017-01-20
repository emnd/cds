/**
 * slimScroll - Directive for slimScroll with custom height
 */
angular.module('app').directive('slimScroll', slimScroll);

function slimScroll($timeout){
    return {
        restrict: 'A',
        scope: {
            boxHeight: '@'
        },
        link: function(scope, element) {
            $timeout(function(){
                element.slimscroll({
                    height: scope.boxHeight,
                    railOpacity: 0.9
                });

            });
        }
    };
}
