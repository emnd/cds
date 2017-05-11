define(['angular',
        '../../app/zmod-model/equipement.controller'
        ], function (angular, EquipementController) {
/*
                   .___    .__
  _____   ____   __| _/_ __|  |   ____
 /     \ /  _ \ / __ |  |  \  | _/ __ \
|  Y Y  (  <_> ) /_/ |  |  /  |_\  ___/
|__|_|  /\____/\____ |____/|____/\___  >
      \/            \/               \/
                   .___     .__      __                        .__          __
  _____   ____   __| _/____ |  |   _/  |_  ____   _____ ______ |  | _____ _/  |_  ____
 /     \ /  _ \ / __ |/ __ \|  |   \   __\/ __ \ /     \\____ \|  | \__  \\   __\/ __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__  |  | \  ___/|  Y Y  \  |_> >  |__/ __ \|  | \  ___/
|__|_|  /\____/\____ |\___  >____/  |__|  \___  >__|_|  /   __/|____(____  /__|  \___  >
      \/            \/    \/                  \/      \/|__|             \/          \/

* Use this model as a base of your own new module. A module is bind to a use case and can contain many controllers, components, directives.
This is where you define all the specific routes.
*/

  'use strict';

  angular.module('app.model',['ui.grid.selection'])
           .config(['$routeProvider',
                function($routeProvider) {
                  $routeProvider.
                    when('/model', {
                      templateUrl: 'app/zmod-model/equipement.template.html',
                      controller: 'EquipementController'
                    }).
                    otherwise({
                      redirectTo: '/'
                    });
                }])
            .controller('EquipementController', EquipementController)
            ;
});
