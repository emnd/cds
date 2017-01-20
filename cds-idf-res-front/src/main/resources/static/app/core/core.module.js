define(['angular', 'ngRoute', 'ngRoute', 'ngResource',
        'ngAnimate', 'ngTouch', 'ngSanitize','uiGrid','uiBootstrap','angularTranslate',
        'angularTranslateLoaderStaticFiles', 'angularTranslateStorageLocal', 'angularTranslateHandlerLog',
        'angularTranslateLoaderUrl'
        ], function (angular) {
/*
            .-.
           [.-''-.,
           |  //`~\)
           (<| 0\0|>_
           ";\  _"/ \\_ _,
          __\|'._/_  \ '='-,
         /\ \    || )_///_\>>
        (  '._ T |\ | _/),-'
         '.   '._.-' /'/ |
         | '._   _.'`-.._/
         ,\ / '-' |/
         [_/\-----j
    _.--.__[_.--'_\__
   /         `--'    '---._
  /  '---.  -'. .'  _.--   '.
  \_      '--.___ _;.-o     /
    '.__ ___/______.__8----'

 Core module centralize common module and their common configuration - It can be an abstraction on external libraries
*/
  'use strict';

  angular
    .module('app.core', [
        'ngRoute',
        'ngResource',
        'ngAnimate',
        'ngTouch',
        'ngSanitize',
        'ui.grid',
        'pascalprecht.translate',
        'ui.bootstrap'
    ])
    .config(function ($translateProvider) {
          $translateProvider.useStaticFilesLoader({
            files:[{
              prefix: 'app/layout/i18n/locale-',
              suffix: '.json'
            },{
              prefix: 'app/zmod-model/i18n/locale-',
              suffix: '.json'
            }]
          });
          $translateProvider.preferredLanguage('fr_FR');// is applied on first load
          $translateProvider.useSanitizeValueStrategy('sanitizeParameters');
          //$translateProvider.useLocalStorage();// saves selected language to localStorage
      })
    .constant('LOCALES', {
      'locales': {
          'fr_FR': 'Français',
          'en_US': 'English'
      },
      'preferredLocale': 'fr_FR'
    })
    ;
});