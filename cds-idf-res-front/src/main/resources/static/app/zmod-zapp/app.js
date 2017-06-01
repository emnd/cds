define(['require'], function (require) {


  'use strict';

/**
 * @ngdoc overview
 * @name odeadomApp
 * @description
 *
 * Main module of the application.
 */
var app = angular.module('app', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'ui.select',
    'ngSanitize',
    'pascalprecht.translate',
    'ui.bootstrap.tpls',
    'angular-loading-bar',
    'ui.grid',
    'ui.grid.selection',
    'ui.grid.i18n'
  ]);
  app.config(function ($translateProvider) {
                 $translateProvider.useStaticFilesLoader({
                   files:[{
                     prefix: 'app/layout/i18n/locale-',
                     suffix: '.json'
                   },{
                     prefix: '/app/zmod-model/i18n/locale-',
                     suffix: '.json'
                   }]
                 });
                 $translateProvider.preferredLanguage('fr_FR');
                 $translateProvider.useSanitizeValueStrategy('sanitizeParameters');
                 //$translateProvider.useLocalStorage();// saves selected language to localStorage
             })

           .config(function (uibDatepickerConfig, uibDatepickerPopupConfig){
                 uibDatepickerPopupConfig.showButtonBar = false;
                 uibDatepickerPopupConfig.clearText =  'Effacer';
                 uibDatepickerPopupConfig.closeText =  'Fermer';
                 uibDatepickerPopupConfig.currentText =  'Aujourd\'hui';
                 //Alternative input accepted from the user
                 uibDatepickerPopupConfig.altInputFormats =  ["dd/MM/yyyy", "dd-MM-yyyy", "dd.MM.yyyy",
                                                              "dd/MM/yy", "dd-MM-yy", "dd.MM.yy"];
                 uibDatepickerConfig.startingDay=1; //Lundi
           })
           .constant('LOCALES', {
             'locales': {
                 'fr_FR': 'Français',
                 'en_US': 'English'
             },
             'preferredLocale': 'fr_FR'
           })
           //Constantes  diverses
           .constant('UTIL', {
               //Regex for valid date (after 1600). Source: http://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy
               datePattern: '^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$'
               , homeState: 'dashboard.home'
               , noClicked: 'lksdofgkodsno'
               , yesClicked: 'sdfsdfdccyesc'
           }
           )
           ;
  app.config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', '$translateProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider, $translateProvider) {

    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('login', {
          url :'/login',
          templateUrl: '/app/layout/pages/login.template.html',
          controller:'LoginCtrl',
          resolve: {
            loadMyFiles:function($ocLazyLoad) {
                return $ocLazyLoad.load({
                        name:'app',
                        files:
                        ['/app/core/login.controller.js']
                })
            }
          }
    })

      .state('dashboard', {
        url:'/dashboard',
        templateUrl: '/app/core/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'app',
                    files:[
                    '/app/layout/directives/header/header.directive.js',
                    '/app/layout/directives/header/header-notification/header-notification.directive.js',
                    '/app/layout/directives/sidebar/sidebar.directive.js',
                    '/app/core/directives/slimScroll.directive.js',
                    '/app/layout/directives/sidebar/sidebar-search/sidebar-search.directive.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["/webjars/angular-toggle-switch/1.3.0/angular-toggle-switch.min.js",
                          "/webjars/angular-toggle-switch/1.3.0/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['/webjars/angularjs/1.5.8/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['/webjars/angularjs/1.5.8/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['/webjars/angularjs/1.5.8/angular-resource.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['/webjars/angularjs/1.5.8/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['/webjars/angularjs/1.5.8/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'MainCtrl',
        templateUrl:'/app/layout/dashboard/home.template.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'app',
              files:[
              '/app/layout/dashboard/home.controller.js',
              '/app/layout/directives/timeline/timeline.directive.js',
              '/app/layout/directives/notifications/notifications.directive.js',
              '/app/layout/directives/chat/chat.directive.js',
              '/app/layout/directives/dashboard/stats/stats.directive.js'
              ]
            })
          }
        }
      })
      .state('dashboard.record',{
        url:'/dossier/creation',
        controller: 'ModelController',
        templateUrl:'/app/zmod-model/model.template.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'app',
              files:[
              '/app/zmod-model/model.module.js',
              '/app/zmod-model/model.controller.js'
              ]
            })
          }
        }
      })
      .state('dashboard.form',{
        templateUrl:'/app/layout/forms/form.template.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'/app/layout/pages/blank.template.html',
        url:'/blank'
    })
     /* .state('login',{
        templateUrl:'/app/layout/pages/login.template.html',
        url:'/login'
    })*/
      .state('dashboard.table',{
        templateUrl:'/app/layout/tables/table.template.html',
        url:'/table'
    })
      .state('dashboard.panels-wells',{
          templateUrl:'/app/layout/ui-elements/panels-wells.template.html',
          url:'/panels-wells'
      })
      .state('dashboard.buttons',{
        templateUrl:'/app/layout/ui-elements/buttons.template.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'/app/layout/ui-elements/notifications.template.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'/app/layout/ui-elements/typography.template.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'/app/layout/ui-elements/icons.template.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'/app/layout/ui-elements/grid.template.html',
       url:'/grid'
   })
   	.state('dashboard.formulaire.acces',{
   		templateUrl:'/app/layout/ui-elements/formulaire.demande.acces.template.html',
        url:'/demandeAcces'
   	})
   	.state('dashboard.liste.demande',{
   		templateUrl:'/app/layout/ui-elements/liste.demande.acces.template.html',
        url:'/listeDemandes'
   	})
   	
   	.state('dashboard.authorisation',{
        url:'/gestion-des-autorisations/liste-des-demandes',
        controller: 'AuthorisationController',
        templateUrl:'/app/zmod-model/authorisation.template.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'app',
              files:[
              '/app/zmod-model/authorisation.module.js',
              '/app/zmod-model/authorisation.controller.js'
              ]
            })
          }
        }
      })
      
      .state('dashboard.authorisations',{
        url:'/gestion-des-autorisations/toutes-les-demandes',
        controller: 'AuthorisationsController',
        templateUrl:'/app/zmod-model/authorisations.template.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'app',
              files:[
              '/app/zmod-model/authorisations.module.js',
              '/app/zmod-model/authorisations.controller.js'
              ]
            })
          }
        }
      })
      .state('dashboard.formulaireDeDemande',{
        url:'/gestion-des-autorisations/formulaire-de-demande',
        controller: 'FormulaireDeDemandeController',
        templateUrl:'/app/layout/forms/formulaireDeDemande.template.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'app',
              files:[
              '/app/layout/forms/formulaireDeDemande.module.js',
              '/app/layout/forms/formulaireDeDemande.controller.js'
              ]
            })
          }
        }
      })

      //equipement
         .state('dashboard.equipement',{
              url:'/Gestion-des-ressources/Liste-des-equipements',
              controller: 'EquipementController',
              templateUrl:'/app/zmod-model/equipement.template.html',
              resolve: {
                loadMyFiles:function($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    name:'app',
                    files:[
                    '/app/zmod-model/equipement.module.js',
                    '/app/zmod-model/equipement.controller.js'
                    ]
                  })
                }
              }
            })

         //AjoutEquipement
            .state('dashboard.AjoutEquipement',{
              url:'/Gestion-des-ressources/formulaire-equipement',
              controller: 'AjoutEquipementController',
              templateUrl:'/app/layout/forms/form.ajout.equipement.html',
              resolve: {
                loadMyFiles:function($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    name:'app',
                    files:[
                    '/app/layout/forms/form.ajout.equipement.module.js',
                    '/app/layout/forms/form.ajout.equipement.controller.js'
                    ]
                  })
                }
              }
            })
   	
  }]);
});