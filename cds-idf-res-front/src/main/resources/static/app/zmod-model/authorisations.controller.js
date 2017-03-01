/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('AuthorisationsController', AuthorisationsController);

  AuthorisationsController.$inject = ['$rootScope', '$scope', '$timeout', '$q','$http', '$log', 'uiGridConstants','$uibModal'];

  function AuthorisationsController($rootScope, $scope, $timeout, $q, $http, $log, uiGridConstants,$uibModal) {
    $rootScope.moduleLoaded = false;
    var self = $scope;
    var userInfos = []; // recuperation des informations de l'utilisateur courant
    var userName, userEmail;
    
    var applicant,applicantEmail,decider,firstName,lastName,id,motive,periodEnd,periodStart,projectName,replyDate,requestDate,status,firstName,lastName, extendedDate,emailOpen,loginOpen,text;
	  
	  $http({ 
			method : 'GET',
			url : '/userInfo'
		}).then(function successCallback(response) {
			$scope.userInfos = response.data;
			console.log(" les infos users : "+$scope.userInfos);
			console.log("UserName : "+$scope.userInfos[0]); // userName du LDAP
			console.log("UserEmail : "+$scope.userInfos[1]); // userEmail du LDAP
		}, function errorCallback(response) {

		});

	  $scope.filterOptions = {
              filterText: ''
          };
	  
    $scope.gridOptions = {
        enableRowSelection: true,
        enableSelectAll: true,
        selectionRowHeaderWidth: 35,
        rowHeight: 35,
        columnWidth: 25,
        showGridFooter:true,
        columnDefs : [
                      { name: 'id', field:'id',enableFiltering: false,width :40, visible:false},
                      { name: 'idReq', field:'request.id',enableFiltering: false,width :40, visible:false},
                      { name: 'RequestDate', field:'request.requestDate',enableFiltering: false,width :40, visible:false},
                      { name: 'ReplyDate', field:'request.replyDate',enableFiltering: false,width :40, visible:false},
                      { name: 'decideur', field:'request.decider',enableFiltering: false,width :40, visible:false},
                      { name: 'nom_du_demandeur', field:'request.applicant',enableFiltering: true, filter: {term: $scope.filterOptions.filterText}, width:"20%" },
                      { name: 'nom_du_collaborateur', field:'collaborator.firstName',enableFiltering: false, width:"22%"},
                      { name: 'prénom_du_collaborateur', field:'collaborator.lastName',enableFiltering: false, width:"22%"},
                      { name: 'date_debut', field:'periodStart',enableFiltering: false, width:"18%"},
                      { name: 'date_fin', field:'periodEnd',enableFiltering: false, width:"18%"},
                      { name: 'projet' , field: 'project.projectName', cellTooltip: function(row) { return row.entity.projectName; },enableFiltering: false, width:"18%"},
                      { name: 'motivation', field: 'motive',cellTooltip: function(row) { return row.entity.motive; },enableFiltering: false, width:"18%"},
                      { name: 'statut', field: 'status', enableFiltering: false, width:"18%"},
                      { name: 'Modifier le statut', width : 350,enableFiltering: false, cellTemplate: '<div><input ng-click="grid.appScope.getProperty(row,decision,decider)"  ng-model="decision" type="radio" value="Attente" style="width:20px" checked >Attente<input  ng-click="grid.appScope.getProperty(row,decision,decider)" ng-model="decision" type="radio" value="Acceptée" style="width:20px">Acceptée<input ng-click="grid.appScope.getProperty(row,decision,decider)" ng-model="decision" type="radio" value="Refusée"  style="width:20px">Refusée</div>', width:"30%"},
                      { name: 'Prolonger',field: 'id',cellTemplate: '/app/zmod-model/extends.button.template.html',enableFiltering: false,width:"15%"} 
                     ],
          data: [
                 {
                     "id":"",
                     "applicant": "",
                      "periodStart": "",
                      "periodEnd":"",
                      "projectName":"",
                      "motive": "",
                       "status":""
                  }
                 ]
             };
             
    		// debut changement du satut
             $scope.getProperty = function(row,decision,decider){
                 row.entity.status = decision;
                 $scope.dec = decision;
                 $scope.id = row.entity.id;
                 $scope.decider = $scope.userInfos[0];  //console.log("Mon decider : "+$scope.decider)
                 $http({
                                     method : 'PUT',
                                     url : $scope.dec+'/'+$scope.id+':'+$scope.decider,
                                     data : 
                                     { 
                                    	 "id" : +$scope.id,
                                     	"decider" : +$scope.decider
                                     }
                                 })
                                 .success(function(data){  location.reload();
                                 });
                 //console.log($scope.dec +" "+row.entity.status+" "+row.entity.id+" "+$scope.decider);
                };
              // fin changement du statut
                
    $scope.gridOptions.multiSelect = true;
    
    
    // Debut de  la prolongation
    $scope.extend = function(){
   	 var ligne = $scope.row.entity;  // ligne d'une demande avec toutes les colonnnes (attributs)
 	console.log(ligne);
   	 self.applicant = ligne.request.applicant;
   	 self.decider = ligne.request.decider;
   	 self.emailOpen = ligne.collaborator.emailOpen;
   	 self.firstName = ligne.collaborator.firstName; // le firsrtName du collaborateur
   	 self.lastName = ligne.collaborator.lastName; //// le lastName du collaborateur
   	 
   	 self.id =  ligne.id;
   	 self.motive = ligne.motive;
   	 self.periodEnd= new Date(ligne.periodEnd);       // new Date() permet de recuperer la donnée en format date
   	 self.periodStart = new Date(ligne.periodStart);  // new Date() permet de recuperer la donnée en format date
   	 self.projectName = ligne.project.projectName;
   	 self.replyDate = new Date(ligne.request.replyDate);     // new Date() permet de recuperer la donnée en format date
   	 self.requestDate = new Date(ligne.request.requestDate);  // new Date() permet de recuperer la donnée en format date
   	 self.status = ligne.status;
   	self.loginOpen = ligne.collaborator.emailOpen;
   	self.buOpen= ligne.collaborator.buOpen;
   	
   	 
   	console.log("demandeur : " +self.applicant+ "\n decider : "+ligne.request.decider+ 
   			"\n firstName : " +self.firstName+"\n lastName : " +self.lastName+ "\n id : "+self.id+"\n motive : " +self.motive+
   			"\n periodEnd :"+self.periodEnd+"\n periodStart : "+self.periodStart+ "\n projectName : "+self.projectName+
   			"\n replyDate : "+self.replyDate+ "\n requestDate : "+self.requestDate+"\n status : "+ self.status+ "\n emailOpen :"+self.emailOpen);
   	
   	// ouverture du popup après click sur prolongation
   	var $uibModalInstance = $uibModal.open({
        templateUrl: '/app/zmod-model/extends.authorisations.template.html',
        scope: $scope
   	});
   	
   	 // pour l'annulation de la prolongation
   	$scope.cancel = function() {
   		console.log("Prolongation annulée !"); 
   		$uibModalInstance.dismiss();
   	      
   	    };
   	    
   	    self.extendedDate= new Date($scope.extendedDate); // la date de prolongation recuperéé par $scope.extendedDate le 19/12/2016
   	    self.emailOpen = $scope.emailOpen;
   	
   };
   
   self.requestDate = new Date(); // la date du jour 
   //$scope.emailOpen = self.firstName.toLowerCase()+"."+self.lastName.toLowerCase()+$scope.emailOpen;
   // pour la validation de la prolongation
   self.text=" Le statut de la demande et/ou la date de prolongation ne permet pas la prolongation de votre demande";
   
   $scope.save = function() {
   	//console.log("je suis dans le save de la prolongation");
			if( (self.status=="Acceptée") && ( (+$scope.periodEnd)  > (+$scope.requestDate) ) && ((+$scope.periodEnd) < (+$scope.extendedDate))) // rajouter des + pour comparer deux dates
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
             deferred.resolve('/authorisations/'); 
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

