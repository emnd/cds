/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('EquipementController', EquipementController);

  EquipementController.$inject = ['$rootScope', '$scope', '$timeout', '$q' ,'$http', '$log', 'uiGridConstants','$uibModal'];

  function EquipementController($rootScope, $scope, $timeout,$q ,$http, $log, uiGridConstants,$uibModal) {
	  
	  
		
		$rootScope.moduleLoaded = false;
		
		 // debut 06/01/2017
	    
		   var vm=$scope;  // redefinition du $scope en vm
		   var idEq,serialNumber,mark,stationName,model,equipementType,stateType,purchaseDate,
		    attributionDate,expectedDate,comments, returnDate, comments,idLoc, idCollab, emailOpen, loginOpen, firstName, lastName,buOpen, ligne,nameLocation,blockLocation,placeLocation; 
		    var equipement = null;
		    var equipementTypes=[];
		    var stateTypes = [];
		  var adresseUrl;
		    
		 // fin 06/01/2017
		
		// generation de la grid
		$scope.gridOptions = {
				enableRowSelection : true, // rendre disponible la selection de ligne
				enableSelectAll : true,
				selectionRowHeaderWidth : 35,
				rowHeight : 50,
				showGridFooter : false,
				 columnDefs :[
				              { name: 'Id' , field:'id', visible : false },
				              { name: 'stationName', field:'stationNameEquipement'},
				              { name: 'serialNumber', field:'serialNumberEquipement'},
				              { name: 'mark', field:'markEquipement' },
				              { name: 'model' , field:'modelEquipement'},
				              { name: 'attributionDate' , field:'attributionDateEquipement'},
				              { name: 'returnDate' , field:'returnDateEquipement'},
				              { name: 'purchaseDate', field:'purchaseDateEquipement' },
				              { name: 'expectedDate', field:'expectedDateEquipement' },
				              { name: 'comments', field: 'commentsEquipement'},
				              { name: 'equipmentType', field:'equipementType' },
				              { name: 'stateType' , field:'stateType'},
				              { name: 'locationId', field:'locationRepresentation.id', visible : false },
				              { name: 'locationPlace', field:'locationRepresentation.placeLocation', visible : false },
				              { name: 'NameLocation', field:'locationRepresentation.nameLocation', visible : false },
				              { name: 'BlockLocation', field:'locationRepresentation.blockLocation' },
				              { name: 'collaboratorId', field:'collaboratorRepresentation.id', visible : false },
				              { name: 'firstName', field:'collaboratorRepresentation.firstName', visible : false },
				              { name: 'lastName', field:'collaboratorRepresentation.lastName', visible : false },
				              { name: 'emailOpen', field:'collaboratorRepresentation.emailOpen', visible : false },
				              { name: 'buOpen', field:'collaboratorRepresentation.buOpen', visible : false },
				              { name: 'loginOpen', field: 'collaboratorRepresentation.loginOpen'},
				            {name:'Affecter', field:'id', cellTemplate: '/app/zmod-model/equipement-edit-button.html', width:35},
				            {name:'Supprimer', field:'id', cellTemplate: '/app/zmod-model/equipement-remove-button.html', width:35}
				              
				          ]
				
		};
		
		$scope.gridOptions.multiSelect = true;   // faire une selection multiple



  	//REST resource for the current user
    //We might have to wait for AuthService to load the user
    var deferred= $q.defer();
        //resolve the promise when user is loaded
    var interval = setInterval(function() {
      
    
             deferred.resolve('/services/equipement/');
          
         }, 50);
    //... and then we load the table data
    deferred.promise.then(function(restURL){
            clearInterval(interval);    //not sure is very clean
            $http.get(restURL)
                .then(function(response) {
                        //First function handles success
                        $scope.gridOptions.data = response.data;
                       console.log("Mes données "+response.data+" \n"+restURL); //
                    }, function(response) {
                        //Second function handles error
                        $scope.content = "Something went wrong";
                          $timeout(function() {
                            if($scope.gridApi.selection.selectRow){
                             $scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);
                     }
                  });
                });
            }, function(){}    //do nothing for error
    );




		
		/* debut 10-01-2017 */ 
	     
	     $scope.equipementEdit = function(){  /* Fonction d'edition d'une localisation d'accès */
	     	 ligne = $scope.row.entity;  // ligne d'une location avec toutes les colonnnes (attributs)
	     
	     	// recuperation des attributs d'une demande après click sur une ligne
	     	 vm.idEq =  ligne.id;
	  		 vm.stationName = ligne.stationNameEquipement; // PAR1LAP du pc
	  	     vm.serialNumber = ligne.serialNumberEquipement; // numero de serie du pc
	    	 vm.mark = ligne.markEquipement; // marque du pc	        
	    	 vm.model = ligne.modelEquipement; // model du pc
	    	 vm.attributionDate = new Date(ligne.attributionDateEquipement); // date d'attribution du pc
	    	 vm.returnDate = new Date(ligne.returnDateEquipement); // date de retour du pc
	    	 vm.purchaseDate = (ligne.purchaseDateEquipement != null) ? new Date(ligne.purchaseDateEquipement) : null; // date d'achat du pc
	    	 vm.expectedDate = new Date(ligne.expectedDateEquipement);
	    	 vm.comments = ligne.commentsEquipement; // commentaire sur le pc
	    	 vm.equipementType = ligne.equipementType; // le type de pc "desktop ou laptop"
	    	 vm.stateType = ligne.stateType; // lelocationRepresentation statut "disponible ou alloue"

	    	
	    	// vm.locationRepresentation = ligne.locationRepresentation ? ligne.locationRepresentation : null; // verification si equipement a été localisé ou pas
	    	vm.idLoc = (ligne.locationRepresentation != null) ? ligne.locationRepresentation.id : null;
	         vm.nameLocation = (ligne.locationRepresentation != null) ? ligne.locationRepresentation.nameLocation : null;
	    	 vm.blockLocation = (ligne.locationRepresentation != null) ? ligne.locationRepresentation.blockLocation : null;
	    	 vm.placeLocation = (ligne.locationRepresentation != null) ? ligne.locationRepresentation.placeLocation : null;
	    	 
	    	 vm.collaboratorRepresentation = ligne.collaboratorRepresentation ? ligne.collaboratorRepresentation : null; // verification si equipement a été affecté ou pas à un collaborateur
    	 	vm.loginOpen= (ligne.collaboratorRepresentation != null )? ligne.collaboratorRepresentation.loginOpen : null ;
				vm.lastName=(ligne.collaboratorRepresentation != null )? ligne.collaboratorRepresentation.lastName : null;
				vm.firstName=(ligne.collaboratorRepresentation != null)? ligne.collaboratorRepresentation.firstName : null;
				vm.emailOpen=(ligne.collaboratorRepresentation != null)? ligne.collaboratorRepresentation.emailOpen : null;
			    vm.buOpen=(ligne.collaboratorRepresentation != null) ? ligne.collaboratorRepresentation.buOpen : null;
			    vm.idCollab = (ligne.collaboratorRepresentation != null) ? ligne.collaboratorRepresentation.id: null;
	    	 


	    	 
	    	console.log("idEquipement : "+vm.idEq+"\n serialNumber : "+vm.serialNumber+"\n"+vm.mark+"\n"+vm.stationName+"\n"+vm.model+"\n"
	    			+vm.comments+"\n"+ligne.equipementType+"\n"+ligne.stateType+"\n"+vm.purchaseDate+"\n"
	    			+vm.attributionDate+"\n"+vm.returnDate+"\n"+vm.nameLocation+"\n"+vm.blockLocation+"\n"+vm.placeLocation+"\n idLocation : "+vm.idLoc
	    			+"\n"+vm.expectedDate+"\n"+vm.lastName+"\n"+vm.firstName+"\n"+vm.buOpen+"\n"+vm.emailOpen+"\n"+$scope.loginOpen+"\n"+vm.idCollab);
	    	
	     	// ouverture du popup après click sur modifier
	     	var $uibModalInstance = $uibModal.open({
	          templateUrl: '/app/zmod-model/equipement-edit.html',
	          scope: $scope
	     	});
	     	
	     	 // pour l'annulation
	     	$scope.cancel = function() {
	     		console.log("modification annulée !"); 
	     		$uibModalInstance.dismiss();
	     	      
	     	    };
	     	    
	     	    
	     	   $http({ // listes des statuts des équipements
					method : 'GET',
					url : 'services/stateType/'
				}).then(function successCallback(response) {
					$scope.stateTypes = response.data;
					console.log($scope.stateTypes);
				}, function errorCallback(response) {

				});
				
				$http({  // liste des types d'équipements
					method : 'GET',
					url : 'services/equipementType/'
				}).then(function successCallback(response) {
					$scope.equipementTypes = response.data;
					console.log($scope.equipementTypes);
				}, function errorCallback(response) {

				});
				
				
	     };
	     
	     vm.text="Enregistrement reussi avec succès";
	     
	     $scope.save = function() {
	    	console.log("idEquipement "+$scope.idEq);
	    	console.log("idEquipement : "+$scope.idEq+"\n serialNumber : "+$scope.serialNumber+"\n"+$scope.mark+"\n"+$scope.stationName+"\n"+$scope.model+"\n"
	    			+$scope.comments+"\n"+$scope.equipementType+"\n"+$scope.stateType+"\n"+$scope.purchaseDate+"\n"
	    			+$scope.attributionDate+"\n"+$scope.returnDate+"\n"+$scope.nameLocation+"\n"+$scope.blockLocation+"\n"+$scope.placeLocation
	    			+"\n"+$scope.expectedDate+"\n"+$scope.lastName+"\n"+$scope.firstName+"\n"+$scope.buOpen+"\n"+$scope.emailOpen+"\n"+$scope.loginOpen+"\n"+$scope.idCollab);
	    	$scope.adresseUrl = '/services/equipement/'+ $scope.idEq;
	    	$scope.adresseUrlWithOutCollab= '/services/equipementWithOutCollab/'+ $scope.idEq;
	    	console.log("adressUrl : "+$scope.adresseUrl);
	    	console.log("id location : "+$scope.idLoc);
	       if( $scope.idEq && $scope.stationName) 
			{
	    	   if($scope.lastName && $scope.firstName && $scope.buOpen && $scope.emailOpen && $scope.loginOpen)
	    	   //self.collaboratorRepresentation != null fausse affirmation
	    	   {
 		 		
				$http({
					method : 'PUT', // pour la modification
					url : '/services/equipement/'+ $scope.idEq,
					data : 
							{
								"id":$scope.idEq,
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
								  { "id" :$scope.idLoc,
								 	 "nameLocation":$scope.nameLocation,
									 "blockLocation":$scope.blockLocation,
									 "placeLocation":$scope.placeLocation
								 },
								  "collaboratorRepresentation":
								  { "id" : $scope.idCollab,
						  			"loginOpen":$scope.loginOpen,
						  			"lastName":$scope.lastName,
						  			"firstName":$scope.firstName,
						  			"emailOpen":$scope.emailOpen,
						  			"buOpen":$scope.buOpen
						  		 }
								
							}
	 					
	 				}).then(function successCallback(response) {
	 					$scope.text=" equipement modifiée";
	 					console.log($scope.text);
//	 					if($scope.collaboratorDtoIdCollab && $scope.locationDtoIdLocation) // debut le 19-01-2017
//	 						{
//	 							console.log("Je peux generer le pdf");
//	 							var $uibModalInstance = $uibModal.open({
//	 						          templateUrl: '/app/zmod-applicant/equipement-pdf.html',
//	 						          scope: $scope
//	 						     	});
//	 						} 
//	 					else {location.reload(); } 	// fin le 19-01-2017	// pour recharger la page courante
	 				}, function errorCallback(response) {
	 					$scope.text=" Erreur survenue";
	 					console.log($scope.text);
	 				});
				
				
			}
				else{
					$http({
						method : 'PUT', // pour la modification
						url : $scope.adresseUrlWithOutCollab,
						data : 
								{
									"id":$scope.idEq,
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
									  { "id" :$scope.idLoc,
									 	 "nameLocation":$scope.nameLocation,
										 "blockLocation":$scope.blockLocation,
										 "placeLocation":$scope.placeLocation
									 },
									  "collaboratorRepresentation":
									  { "id" : $scope.idCollab,
							  			"loginOpen":$scope.loginOpen,
							  			"lastName":$scope.lastName,
							  			"firstName":$scope.firstName,
							  			"emailOpen":$scope.emailOpen,
							  			"buOpen":$scope.buOpen
							  		 }
					
									
								}
		 					
		 				}).then(function successCallback(response) {
		 					$scope.text=" equipement modifiée sans Collab";
		 					console.log($scope.text);
//		 					if($scope.collaboratorDtoIdCollab && $scope.locationDtoIdLocation) // debut le 19-01-2017
//		 						{
//		 							console.log("Je peux generer le pdf");
//		 							var $uibModalInstance = $uibModal.open({
//		 						          templateUrl: '/app/zmod-applicant/equipement-pdf.html',
//		 						          scope: $scope
//		 						     	});
//		 						} 
//		 					else {location.reload(); } 	// fin le 19-01-2017	// pour recharger la page courante
		 				}, function errorCallback(response) {
		 					$scope.text=" Erreur survenue";
		 					console.log($scope.text);
		 				});
				}
				
				
				
	 		}
	 	/*		else
	 			{
	 				if(/*vm.collaboratorRepresentation == null$scope.loginOpen == null && $scope.lastName == null && $scope.firstName== null && $scope.emailOpen == null && $scope.buOpen== null)){

	 					
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
//	 								  "collaboratorRepresentation":
//	 									  {
//	 							  			"loginOpen": $scope.loginOpen,
//	 							  			"lastName":$scope.lastName,
//	 							  			"firstName":$scope.firstName,
//	 							  			"emailOpen":$scope.emailOpen,
//	 							  			"buOpen":$scope.buOpen
//	 							  		 }
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
	 					  
	 					  $scope.text="Equipement Ajouté "+self.loginOpen+" \n emailOpen "+self.emailOpen +" \n firstName"+self.firstName+" \n lastName "+self.lastName+" \n buOpen "+self.buOpen;
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
	 			}*/
	
	   };
	
	   
	   $scope.equipementRemove = function() // suppression d'une Localisation
	   {
		   ligne = $scope.row.entity;
		   vm.idEq =  ligne.id;
		   vm.stationName = ligne.stationNameEquipement;
		   console.log(ligne.id);
		   
		   var $uibModalInstance = $uibModal.open({
		          templateUrl: '/app/zmod-model/equipement-remove.html',
		          scope: $scope
		     	});
		   
		// pour l'annulation
	     	$scope.cancel = function() {
	     		console.log("suppression annulée !"); 
	     		$uibModalInstance.dismiss();
	     	      
	     	    };
		   // pour la suppression
	     	   $scope.equipementDelete = function()
	    	   { ligne = $scope.row.entity;
	    	   $scope.idEq =  ligne.id;
	    		   
	    		   console.log("idEq"+$scope.idEq);
	    		   $scope.adresseUrlDelete = '/services/equipement/'+ $scope.idEq;
	    		   
	    		   
	    		   $http({
	    				method : 'DELETE',
	    				url : $scope.adresseUrlDelete,
	    			}).then(function successCallback(response) {
	    				console.log("suppression de "+$scope.idEq);
	    				location.reload();
	    			}, function errorCallback(response) {
	    				console.log("Erreur survenue lors de la suppression");
	    				location.reload();
	    			});
	    		   
	    	   };
	     	    
	     	    
	   };
	   
	   
	  
	   
	   
	   
	    //TODO Using an event broadcast by the AuthService might be cleaner

	    $scope.info = {};

	    $scope.toggleMultiSelect = function() {
	      $scope.gridApi.selection.setMultiSelect(!$scope.gridApi.grid.options.multiSelect);
	    };

	    $scope.toggleModifierKeysToMultiSelect = function() {
	      $scope.gridApi.selection.setModifierKeysToMultiSelect(!$scope.gridApi.grid.options.modifierKeysToMultiSelect);
	    };

	    $scope.selectAll = function() {
	      $scope.gridApi.selection.selectAllRows();
	    };

	    $scope.clearAll = function() {
	      $scope.gridApi.selection.clearSelectedRows();
	    };
	    
	    $scope.toggleRow1 = function() {
	      $scope.gridApi.selection.toggleRowSelection($scope.gridOptions.data[0]);
	    };

	    $scope.toggleFullRowSelection = function() {
	      $scope.gridOptions.enableFullRowSelection = !$scope.gridOptions.enableFullRowSelection;
	      $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
	    };

	    $scope.setSelectable = function() {
	      $scope.gridApi.selection.clearSelectedRows();

	      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);

	      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.EDIT);
	    };

	    $scope.gridOptions.onRegisterApi = function(gridApi){
	      //set gridApi on scope
	      $scope.gridApi = gridApi;
	      gridApi.selection.on.rowSelectionChanged($scope,function(row){
	        var msg = 'row selected ' + row.isSelected;
	       // console.log("ligne selectionnée "+row.entity); // le 14/12/2016
	        $log.log(msg);
	       // editRow(this.grid,this.row);
	       
	      });

	      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
	        var msg = 'rows changed ' + rows.length;
	        $log.log(msg);
	      });
	    };

	    $timeout(function(){
	        $rootScope.moduleLoaded = true;
	    }, 300);
		
	
	    
	    
	    
    
  };

