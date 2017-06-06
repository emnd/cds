/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('AuthorisationController', AuthorisationController);

  AuthorisationController.$inject = ['$rootScope', '$scope', '$timeout', '$q','$http', '$log', 'uiGridConstants','$uibModal'];

  function AuthorisationController($rootScope, $scope, $timeout, $q, $http, $log, uiGridConstants,$uibModal) {
    $rootScope.moduleLoaded = false;
    
    var userInfos = []; // recuperation des informations de l'utilisateur courant
    var userName, userEmail;
    //le 18-05-2017
    $scope.userRoles = []; /* les roles de l'utilisateur courant*/
    $scope.userR , $scope.attributeR, $scope.cdsManagerR;
    // var userR , attributeR, cdsManagerR;
    //le 18-05-2017
    var applicant,applicantEmail,decider,firstName,lastName,id,motive,periodEnd,periodStart,projectName,replyDate,requestDate,status,firstName,lastName, extendedDate,emailOpen,loginOpen,text, buOpen;
	  
	  $http({ 
			method : 'GET',
			url : '/userInfo'
		}).then(function successCallback(response) {
			$scope.userInfos = response.data;
			console.log(" les infos users : "+$scope.userInfos);
			console.log("UserName : "+$scope.userInfos[0]); // userName du LDAP
			self.userName = $scope.userInfos[0];
			console.log("UserEmail : "+$scope.userInfos[1]); // userEmail du LDAP
			// le 18-05-2017
			/* recuperation des  rôles de l'utilisateur courant*/
			$scope.role;
			for(var i=0; i<$scope.userInfos.length; i++)
			{
			    if( (i > 1) )
			    {
                    $scope.role = $scope.userInfos[i];
                    $scope.userRoles.push($scope.role);
			    }
			}
			console.log("les Roles : ");
			console.log($scope.userRoles);
			if($scope.userRoles.length == 1)
			{
			    $scope.userR = $scope.userRoles[0];
			    $scope.attributeR = $scope.userRoles[0];
			    console.log("userR :"+$scope.userR+"\n"+"attributeR :"+$scope.attributeR+"\n");
			}
			if($scope.userRoles.length >= 2)
			{
			    $scope.userR = $scope.userRoles[0];
			    $scope.attributeR = $scope.userRoles[1];
			    $scope.cdsManagerR = $scope.userRoles[2];
			    console.log("userR :"+$scope.userR+"\n"+"attributeR :"+$scope.attributeR+"\n");
			}
			if($scope.userRoles.length == 3)
            {
            	$scope.userR = $scope.userRoles[0];
            	$scope.attributeR = $scope.userRoles[1];
            	$scope.cdsManagerR = $scope.userRoles[2];
            	console.log("userR :"+$scope.userR+"\n"+"attributeR :"+$scope.attributeR+"\n"+"cdsManagerR :"+$scope.cdsManagerR+"\n");
            }
			// le 18-05-2017
		}, function errorCallback(response) {

		});

    $scope.gridOptions = {
        enableRowSelection: true,
        enableSelectAll: true,
        selectionRowHeaderWidth: 35,
        rowHeight: 35,
        columnWidth: 25,
        showGridFooter:true,
    columnDefs : [
                  { name: 'demandeID', field:'request.id',visible:false},
                  { name: 'date_de_la_demande', field:'request.requestDate',width:"18%"}, //for now, these are String
                  { name: 'decideur', field:'request.decider',enableFiltering: false,width :40, visible:false},
                  { name: 'nom_du_demandeur', field:'request.applicant',enableFiltering: false, width :40, visible:false },
                  { name: 'collaborateurID', field: 'collaborator.id',visible:false},
                  { name: 'collaborateurLoginOpen', field: 'collaborator.loginOpen', visible:false},
                  { name: 'collaborateurEmail', field: 'collaborator.emailOpen',visible:false},
                  { name: 'Nom', field:'collaborator.firstName',width:"15%"},
                  { name: 'Prénom', field:'collaborator.lastName',width:"15%"},
                  { name: 'BuOpen', field:'collaborator.buOpen',width:"15%",visible:false},
                  { name: 'LoginOpen', field:'collaborator.loginOpen',width:"15%",visible:false},
                  { name: 'date de début', field:'periodStart',width:"12%"}, //for now, these are String
                  { name: 'date de fin', field:'periodEnd',width:"10%"},
                  { name: 'projetID' , field: 'project.id', visible:false},
                  { name: 'projet' , field: 'project.projectName', cellTooltip: function(row) { return row.entity.projet; },width:"20%"},
                  { name: 'statut', field: 'status',width:"10%"},
                  { name: 'Prolonger',field: 'id',cellTemplate: '/app/zmod-model/extend.button.template.html',enableFiltering: false,width:"15%"} 
                  
                  ]
             };

    $scope.gridOptions.multiSelect = true;
    
    // Debut de  la prolongation
    $scope.extend = function(){
   	 var ligne = $scope.row.entity;  // ligne d'une demande avec toutes les colonnnes (attributs)
   	 console.log(ligne);
   	 $scope.applicant = ligne.request.applicant;
   	$scope.decider = ligne.request.decider;
   	$scope.emailOpen = ligne.collaborator.emailOpen;
   	$scope.firstName = ligne.collaborator.firstName; // le firsrtName du collaborateur
   	$scope.lastName = ligne.collaborator.lastName; //// le lastName du collaborateur
   	 
   	$scope.id =  ligne.id;
   	$scope.motive = ligne.motive;
   	$scope.periodEnd= new Date(ligne.periodEnd);       // new Date() permet de recuperer la donnée en format date
   	$scope.periodStart = new Date(ligne.periodStart);  // new Date() permet de recuperer la donnée en format date
   	$scope.projectName = ligne.project.projectName;
   	$scope.replyDate = new Date(ligne.request.replyDate);     // new Date() permet de recuperer la donnée en format date
   	$scope.requestDate = new Date(ligne.request.requestDate);  // new Date() permet de recuperer la donnée en format date
   	$scope.status = ligne.status;
   	$scope.loginOpen = ligne.collaborator.emailOpen;
   	$scope.buOpen= ligne.collaborator.buOpen;
   	
   	 
   	console.log("demandeur : " +$scope.applicant+ "\n decider : "+$scope.decider+ 
   			"\n firstName : " +$scope.firstName+"\n lastName : " +$scope.lastName+ "\n id : "+$scope.id+"\n motive : " +$scope.motive+
   			"\n periodEnd :"+$scope.periodEnd+"\n periodStart : "+$scope.periodStart+ "\n projectName : "+$scope.projectName+
   			"\n replyDate : "+$scope.replyDate+ "\n requestDate : "+$scope.requestDate+"\n status : "+ $scope.status+ "\n emailOpen :"+$scope.emailOpen);
   	
   	// ouverture du popup après click sur prolongation
   	var $uibModalInstance = $uibModal.open({
        templateUrl: '/app/zmod-model/extend.authorisation.template.html',
        scope: $scope
   	});
   	
   	 // pour l'annulation de la prolongation
   	$scope.cancel = function() {
   		console.log("Prolongation annulée !"); 
   		$uibModalInstance.dismiss();
   	      
   	    };
   	    
   	    self.extendedDate= new Date($scope.extendedDate); // la date de prolongation recuperéé par $scope.extendedDate le 19/12/2016
   	    console.log(" date de prolongation : "+self.extendedDate);
   	    self.emailOpen = $scope.emailOpen;
   	
   };
   
   self.requestDate = new Date(); // la date du jour 
   //$scope.emailOpen = self.firstName.toLowerCase()+"."+self.lastName.toLowerCase()+$scope.emailOpen;
   // pour la validation de la prolongation
   self.text=" Le statut de la demande et/ou la date de prolongation ne permet pas la prolongation de votre demande";
   
   $scope.save = function() {
   	//console.log("je suis dans le save de la prolongation");
			if( ($scope.status=="Acceptée") && ( (+$scope.periodEnd)  > (+$scope.requestDate) ) && ((+$scope.periodEnd) < (+$scope.extendedDate))) // rajouter des + pour comparer deux dates
			{ // a revoir les conditions
				$http({
					method : 'POST',
					url : '/authorisation/',
					data : 
						{
							"request": {
						      "requestDate": new Date(),
						      "replyDate": null,
						      "applicant": $scope.userInfos[0],
						      "decider": "",
						      "applicantEmail" :$scope.userInfos[1]
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
						    "periodStart": $scope.periodEnd,
						    "periodEnd": $scope.extendedDate,
						    "equipment": $scope.equipement,
						    "motive": $scope.motive,
						    "status": ""
					}
				}).then(function successCallback(response) {
					$scope.text=" Demande de prolongation envoyé";
					console.log($scope.text);
					//alert($scope.text);
					
					console.log(" extentedDate "+$scope.extendedDate+" emailOpen "+$scope.emailOpen);
					console.log( $scope.periodEnd + " " + $scope.extendedDate );
					location.reload();  		// pour recharger la page courante
				}, function errorCallback(response) {
	
				});
			}
			else{
				$scope.text = self.text +" le statut "+self.status+" de la demande ou la date de prolongation ne permet pas la prolongation de votre demande";
				console.log($scope.text);
				
				console.log(self.status=="Acceptée");
				console.log(+$scope.periodEnd > +$scope.requestDate);
				console.log("$scope.extentedDate is "+$scope.extendedDate);
				console.log("le scope est "+$scope.extendedDate);
				console.log( +$scope.periodEnd > +$scope.extendedDate );
				//location.reload();  		// pour recharger la page courante
			}
   		};
    
    // fin de la prolongation
    
  //REST resource for the current user
    //We might have to wait for AuthService to load the user
    var deferred= $q.defer();
        //resolve the promise when user is loaded
    var interval = setInterval(function() {
              //if not undefined
            //restURL = '/requestList/' + AuthService.userLogin();
             deferred.resolve('/requestList/'+self.userName); // userName = $scope.userInfos[0] pour recuperer la liste des demandes de l'utilisateur courant
         }, 50);
    //... and then we load the table data
    deferred.promise.then(function(restURL){
            clearInterval(interval);    //not sure is very clean
            $http.get(restURL)
                .then(function(response) {
                        //First function handles success
                        $scope.gridOptions.data = response.data;
                       console.log("Mes données "+response.data+" \n"+restURL); // le 23/02/2017
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

      $scope.gridOptions.isRowSelectable = function(row){
        if(row.entity.age > 30){
          return false;
        } else {
          return true;
        }
      };
      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);

      $scope.gridOptions.data[0].age = 31;
      $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.EDIT);
    };

    $scope.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.gridApi = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(row){
        var msg = 'row selected ' + row.isSelected;
        $log.log(msg);
      });

      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        var msg = 'rows changed ' + rows.length;
        $log.log(msg);
      });
    };

    $timeout(function(){
        $rootScope.moduleLoaded = true;
    }, 500);
  };

