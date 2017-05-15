'use strict';
/**
 * @ngdoc function
 * @name odeadomApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the odeadomApp
 */
angular.module('app').controller('AjoutEquipementController', AjoutEquipementController); 
    
	  
	  AjoutEquipementController.$inject = ['$rootScope', '$scope', '$timeout', '$q' ,'$http', '$log', 'uiGridConstants','$uibModal'];

	  function AjoutEquipementController($rootScope, $scope, $timeout,$q ,$http, $log, uiGridConstants,$uibModal) {
	    $rootScope.moduleLoaded = false;
	    var self=$scope;
	    var stateTypes =[];
	    var equipementTypes = [];
	    
	    var idEq,serialNumber,mark,stationName,model,equipementType,stateType,purchaseDate,
	    attributionDate,expectedDate,comments, returnDate, comments,idLoc, idCollab, emailOpen, loginOpen, firstName, lastName,buOpen; 
	 
       $http({
			method : 'GET',
			url : '/services/stateType'
		}).then(function successCallback(response) {
			$scope.stateTypes = response.data;
			console.log($scope.stateTypes);
		}, function errorCallback(response) {
			$scope.text="EquipementState pas chargé";
			  console.log($scope.text);
		});
	    
	    $http({
			method : 'GET',
			url : '/services/equipementType'
		}).then(function successCallback(response) {
			$scope.equipementTypes = response.data;
			console.log($scope.equipementTypes);
		}, function errorCallback(response) {
			$scope.text="EquipementType pas trouvé";
			  console.log($scope.text);
		});
	    
		  
	  $scope.AjoutEquipementFormSave = function()
	  {
		  
		  $scope.text="Equipement Ajouté "/*+self.loginOpen+" \n emailOpen "+self.emailOpen +" \n firstName"+self.firstName+" \n lastName "+self.lastName+" \n buOpen "+self.buOpen*/;
		  console.log($scope.text);
		  
		  $http({
				method : 'GET',
				url : '/services/equipementType'
			}).then(function successCallback(response) {
				$scope.equipementTypes = response.data;
				console.log($scope.equipementTypes);
			}, function errorCallback(response) {
				$scope.text="EquipementType pas trouvé";
				  console.log($scope.text);
			});
		 
		  $http({
				method : 'GET',
				url : '/services/stateType'
			}).then(function successCallback(response) {
				$scope.stateTypes = response.data;
				console.log($scope.stateTypes);
			}, function errorCallback(response) {
				$scope.text="EquipementState pas chargé";
				  console.log($scope.text);
			});
		 
if($scope.loginOpen == null && $scope.lastName == null && $scope.firstName== null && $scope.emailOpen == null && $scope.buOpen== null){

	
	$http({
		  
		  	method : 'POST',
			 url : '/services/equipementWithOutCollab/',
			 
			 data :
				 {
				 	"stationNameEquipement" :$scope.stationName,
				 	"serialNumberEquipement" :$scope.serialNumber,
				 	"markEquipement" :$scope.mark,
				 	"modelEquipement" :$scope.model,
				 	"attributionDateEquipement": new Date($scope.attributionDate),
					 "returnDateEquipement" :new Date($scope.returnDate),
					 "purchaseDateEquipement" :new Date($scope.purchaseDate),
					 "expectedDateEquipement" :new Date($scope.expectedDate),
					 "commentsEquipement" :$scope.comments,
					 "equipementType" :$scope.equipementType,
					 "stateType" :$scope.stateType,
					 "locationRepresentation":
					  {
					 	 "nameLocation":$scope.nameLocation,
						 "blockLocation":$scope.blockLocation,
						 "placeLocation":$scope.placeLocation
					 }
//				  "collaboratorRepresentation":
//					  {
//			  			"loginOpen": $scope.loginOpen,
//			  			"lastName":$scope.lastName,
//			  			"firstName":$scope.firstName,
//			  			"emailOpen":$scope.emailOpen,
//			  			"buOpen":$scope.buOpen
//			  		 }
			 }
		  
	  }).then(function successCallback(response){
		  $scope.text="Equipement Ajouté";
		  console.log($scope.text);
		 // location.reload();
	  }, function errorCallback(reponse){
		  
	  });

}
else{
	self.loginOpen = $scope.loginOpen;
	  self.firstName = $scope.firstName;
	  self.emailOpen = $scope.emailOpen;
	  self.lastName = $scope.lastName;
	  self.buOpen = $scope.buOpen;
	  
	  $scope.text="Equipement Ajouté "/*+self.loginOpen+" \n emailOpen "+self.emailOpen +" \n firstName"+self.firstName+" \n lastName "+self.lastName+" \n buOpen "+self.buOpen*/;
	  console.log($scope.text);
	  $http({
		  
		  	method : 'POST',
			 url : '/services/equipement/',
			 
			 data :
				 {
				 	"stationNameEquipement" :$scope.stationName,
				 	"serialNumberEquipement" :$scope.serialNumber,
				 	"markEquipement" :$scope.mark,
				 	"modelEquipement" :$scope.model,
				 	"attributionDateEquipement": new Date($scope.attributionDate),
					 "returnDateEquipement" :new Date($scope.returnDate),
					 "purchaseDateEquipement" :new Date($scope.purchaseDate),
					 "expectedDateEquipement" :new Date($scope.expectedDate),
					 "commentsEquipement" :$scope.comments,
					 "equipementType" :$scope.equipementType,
					 "stateType" :$scope.stateType,
					 "locationRepresentation":
					  {
					 	 "nameLocation":$scope.nameLocation,
						 "blockLocation":$scope.blockLocation,
						 "placeLocation":$scope.placeLocation
					 },
					  "collaboratorRepresentation":
					  {
					    "id": null,
			  			"loginOpen":$scope.loginOpen,
			  			"lastName":$scope.lastName,
			  			"firstName":$scope.firstName,
			  			"emailOpen":$scope.emailOpen,
			  			"buOpen":$scope.buOpen
			  		 }
			 }
		  
	  }).then(function successCallback(response){
		  $scope.text="Equipement Ajouté";
		  console.log($scope.text);
		 // location.reload();
	  }, function errorCallback(reponse){
		  
	  });

}		  			
		
	  
	  }
	
	  
	 
	  }
  