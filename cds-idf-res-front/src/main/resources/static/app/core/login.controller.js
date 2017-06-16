/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('LoginCtrl', LoginCtrl)
  .factory('LoginFactory',function($http){
    var apiUrl='localhost:8080';
    var username, password;
    var self = this;
    	var loggedIn = window.localStorage.getItem('LoggedIn');

        // If the user is not logged in, we set a variable loggedIn to 'null' and save it into the local storage
        if(loggedIn==null) {
          loggedIn = false;
          localStorage.setItem("LoggedIn", loggedIn);
        }

    		return {

          // This function does a POST request with the user's credentials.
          // If the user is authenticated, we save the JWT in the local storage and we put it in the Authorization header,
          // then,  we set a variable loggedIn to 'true' and save it into the local storage
    			/*login: function(credentials) {
    				var credentialsJson = JSON.stringify(credentials);
    				return $http.post(apiUrl + '/loginjwt', credentialsJson)
    					.then(function(response){
    						var token = '';
    						var bearerToken = response.headers().authorization.split(" ");
                token = bearerToken[1];
    						localStorage.setItem("token", token);
    						loggedIn = true;
                localStorage.setItem("LoggedIn", loggedIn);
    						$http.defaults.headers.common['Authorization'] = token;
    					});
    			},*/

          // Function to logout the user
    			logout: function() {
    			  // We delete the JWT and the LoggedIn value from local storage.
    			  localStorage.removeItem('LoggedIn');
    		   	localStorage.removeItem('token');
    		    //localStorage.removeItem('feeling');
          	return;
    			},

          // Function to check if the user is logged in.
    			isLoggedIn : function() {
    				return loggedIn;                     // return true or false
    			},

    			// Function to get an user's manager details from the request
          getManager : function() {
                      return $http.get(apiUrl + '/services/user-manager/').then(function(response){
                          return response.data;
                      });
          },


          // Function to get user details from the request
    			get : function() {
    			      console.log(self.username+"  "+self.password);
                      return $http.get(apiUrl + '/services/user/'+self.username+'/'+self.password).then(function(response){
                          return response.data;
                          console.log(response.data);
                      });
          }

    		};

  }
  );

  LoginCtrl.$inject = ['$rootScope', '$scope', '$timeout', '$q','$http', '$log', 'uiGridConstants','$uibModal','LoginFactory','$state'];

  function LoginCtrl($rootScope, $scope, $timeout, $q, $http, $log, uiGridConstants,$uibModal,LoginFactory, $state) {
    $rootScope.moduleLoaded = false;
    var username,password;
        console.log('CROISSSANT !!!!!!!!!!!!!!!!!!!');
       //init credentials values
       $scope.credentials = {
         username : '',
         password : ''
       };


       $scope.login = function (username,password) {
         console.log('login controller');
         console.log($scope.username+" "+$scope.password);
         //chech username and password existance


         // le 01-06-2017
            $http(
            {
              method : 'GET',
              url : '/services/user/'+$scope.username+'/'+$scope.password
            }).then(function successCallback(response)
            {
              var userInfos = response.data;
              /*console.log(userInfos); */
              // le 02-06-2017
                $http(
                {
                    method : 'GET',
                    url : '/userInfo'
                }).then(function successCallback(resp)
                {
                    var infos = resp.data;
                    var role = infos[2];
                    console.log(role);
                   if(role === "ROLE_USER")
                   $state.go('dashboard.authorisation'); // redirection sur la page /gestion-des-autorisations/liste-des-demandes
                   else if(role === "ROLE_ATTRIBUTE")
                   $state.go('dashboard.AjoutEquipement');
                   else if(rolle === "ROLE_CDSMANAGER")
                   $state.go('dashboard.authorisations');
                   else
                   $state.go('dashboard');
                },
                function errorCallback(resp){ console.log("roles pas récupérés")}
                );
              //
            }, function errorCallback(response)
            {
                console.log("Erreur d'authentification");
            }
            );
       };


    $timeout(function(){
        $rootScope.moduleLoaded = true;
    }, 500);
  };


