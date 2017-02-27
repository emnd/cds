/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('FormulaireDeDemandeController', FormulaireDeDemandeController);

  FormulaireDeDemandeController.$inject = ['$rootScope', '$scope', '$timeout', '$q','$http', '$log', 'uiGridConstants','$uibModal'];

  function FormulaireDeDemandeController($rootScope, $scope, $timeout, $q, $http, $log, uiGridConstants,$uibModal) {
	  
	  $rootScope.moduleLoaded = false;
	  var self=$scope;
	  
	 // var user,deciderName,loginOpen,idAuthorisation,motive,periodEnd,periodStart,projectName,replyDate,requestDate,status,firstName,lastName, extendedDate,emailOpen,text;
  //(self.loginOpen != null) && (self.firstName != null) && (self.lastName != null)  && (self.projectName != null) && (periodStart != null) && ) (periodEnd != null)
	  $scope.submitForm = function() {
	    	
		  if($scope.loginOpen != null) // rajouter des + pour comparer deux dates
			{ // a revoir les conditions
				$http({
					method : 'POST',
					url : '/authorisation/',
					data : 
						{
							"request": {
						      "requestDate": new Date(),
						      "replyDate": null,
						      "applicant": "Moussa",
						      "decider": "",
						      "applicantEmail" :"moussa@moussa.com"
						    },
						    "collaborator": {
						      "loginOpen": $scope.loginOpen,
						      "lastName": $scope.lastName,
						      "firstName": $scope.firstName,
						      "emailOpen": $scope.emailOpen,
						      "buOpen": $scope.buOpen
						    },
						    "project": {
						      "projectName": $scope.projectName,
						      "periodStart": $scope.periodStart,
						      "periodEnd": $scope.periodEnd
						    },
						    "periodStart": $scope.periodStart,
						    "periodEnd": $scope.periodEnd,
						    "equipment": $scope.equipement,
						    "motive": $scope.motive,
						    "status": ""
					  }
				}).then(function successCallback(response) {
					$scope.text=" Demande au CDS envoyé";
					console.log($scope.text);
					location.reload();  		// pour recharger la page courante
				}, function errorCallback(response) {
	
				});
			}
			else{
				$scope.text = self.text +" le statut "+self.status+" de la demande ou la date de prolongation ne permet pas la prolongation de votre demande";
				console.log($scope.text);
			}

	   };
	  
  };

