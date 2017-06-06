requirejs.config({
    paths: {
        'angular': '/webjars/angularjs/1.5.8/angular.min',
        'ngSanitize': '/webjars/angularjs/1.5.8/angular-sanitize.min',
        'ngResource': '/webjars/angularjs/1.5.8/angular-resource.min',
        'ngAnimate': '/webjars/angularjs/1.5.8/angular-animate.min',
        'ngAria': '/webjars/angularjs/1.5.8/angular-aria.min',
        'ngTouch': '/webjars/angularjs/1.5.8/angular-touch.min',
        'ngCookies': '/webjars/angularjs/1.5.8/angular-cookies.min',
        'ngLocaleFR': '/webjars/angularjs/1.5.8/i18n/angular-locale_fr-fr',
        'uiGrid' : '/webjars/ui-grid/3.2.5/ui-grid.min',
        '$' : '/webjars/jquery/3.1.1/jquery.min',
        'jsPdf' : 'https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug',
        'pdfGenerator' : '/app/libs/pdfGenerator',
        'slimScroll': '/webjars/slimScroll/1.3.3/jquery.slimscroll.min',
        'bootstrap' : '/webjars/bootstrap/3.3.7/js/bootstrap.min',
        'uiSelect' : '/webjars/angular-ui-select/0.19.6/dist/select.min',
        'uiBootstrap' : '/webjars/angular-ui-bootstrap/2.2.0/ui-bootstrap.min',
        'uiBootstrapTpls' : '/webjars/angular-ui-bootstrap/2.2.0/ui-bootstrap-tpls.min',
        'angularTranslate' : '/webjars/angular-translate/2.11.0/angular-translate.min',
        'angularTranslateLoaderStaticFiles' : '/webjars/angular-translate-loader-static-files/2.6.1/angular-translate-loader-static-files.min',
        'angularTranslateStorageLocal' : '/webjars/angular-translate-storage-local/2.10.0/angular-translate-storage-local.min',
        'angularTranslateHandlerLog' : '/webjars/angular-translate-handler-log/2.11.0/angular-translate-handler-log.min',
        'angularTranslateLoaderUrl' : '/webjars/angular-translate-loader-url/2.11.0/angular-translate-loader-url.min',
        'uiRouter' : '/webjars/angular-ui-router/0.2.18/angular-ui-router.min',
        'json3' : '/webjars/json3/3.3.2/json3.min',
        'ocLazyLoad': '/webjars/oclazyload/1.0.9/ocLazyLoad.min',
        'ngLoadingBar' : '/webjars/angular-loading-bar/0.9.0/loading-bar.min',
        'app': '/app/zmod-zapp/app'
    },

    shim: {
        angular: {
            'exports' : 'angular',
            'deps':['$']
        },
        $ : {
            'exports' : '$'
        },
        //
        jsPdf : {
          'exports' : 'jsPdf',
          'deps' : ['$']
        },
        pdfGenerator : {
           'exports' : 'pdfGenerator',
           'deps' : ['jsPdf']
        },
        //
        slimScroll : {
            'exports': 'slimscroll',
            'deps':['angular','$']
        },
        ocLazyLoad: {
            'deps': ['angular']
        },
        bootstrap: {
            'exports' : 'bootstrap',
            'deps':['$']
        },
        uiBootstrap: {
                    'exports' : 'uiBootstrap',
                    'deps':['angular', 'bootstrap', 'uiBootstrapTpls']
                },
        uiSelect: {
                    'exports' : 'uiSelect',
                    'deps':['angular']
                },
        uiBootstrapTpls: {
                    'exports' : 'uiBootstrapTpls',
                    'deps':['angular']
                },
        ngSanitize: {
            'exports' : 'ngSanitize',
            'deps':['angular']
        },
        ngResource: {
            'exports' : 'ngResource',
            'deps':['angular']
        },
        uiRouter: {
            'exports' : 'uiRouter',
            'deps':['angular']
        },
        ngAnimate: {
            'exports' : 'ngAnimate',
            'deps':['angular']
        },
        ngAria: {
            'exports' : 'ngAria',
            'deps':['angular']
        },
        ngTouch: {
            'exports' : 'ngTouch',
            'deps':['angular']
        },
        ngCookies: {
            'exports' : 'ngCookies',
            'deps':['angular']
        },
        ngLoadingBar: {
            'exports' : 'ngLoadingBar',
            'deps':['angular']
        },
        ngLocaleFR: {
            'exports' : 'ngLocaleFR',
            'deps':['angular']
        },
        uiGrid: {
            'exports' : 'uiGrid',
            'deps':['angular']
        },
        angularTranslate: {
            'exports' : 'angularTranslate',
            'deps':['angular']
        },
        angularTranslateLoaderStaticFiles: {
            'exports' : 'angularTranslateLoaderStaticFiles',
            'deps':['angular','angularTranslate']
        },
        angularTranslateStorageLocal: {
            'exports' : 'angularTranslateStorageLocal',
            'deps':['angular','angularTranslate']
        },
        angularTranslateHandlerLog: {
            'exports' : 'angularTranslateHandlerLog',
            'deps':['angular','angularTranslate']
        },
        angularTranslateLoaderUrl: {
            'exports' : 'angularTranslateLoaderUrl',
            'deps':['angular','angularTranslate']
        },
        json3: {
           exports: 'JSON'
        },
        app: {
            'exports' : 'app',
            'deps':['angular', 'uiRouter', '$', 'ocLazyLoad', 'uiBootstrap', 'ngLoadingBar', 'ngResource',
                    'ngAnimate', 'ngTouch', 'ngSanitize','uiSelect','uiGrid','angularTranslate',
                    'angularTranslateLoaderStaticFiles', 'angularTranslateStorageLocal', 'angularTranslateHandlerLog',
                    'angularTranslateLoaderUrl', 'ngLocaleFR', 'uiBootstrapTpls', 'slimScroll', 'pdfGenerator', 'jsPdf']
        }
    }
});

define(['angular','app'],
    function (angular) {
        angular.bootstrap(window.document, ['app']);
});