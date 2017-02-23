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

    $scope.gridOptions = {
        enableRowSelection: true,
        enableSelectAll: true,
        selectionRowHeaderWidth: 35,
        rowHeight: 35,
        columnWidth: 25,
        showGridFooter:true,
    columnDefs : [
                  { name: 'date_de_la_demandeID', field:'request.id',width:"18%",visible:false},
                  { name: 'date_de_la_demande', field:'request.requestDate',width:"18%"}, //for now, these are String
                  { name: 'collaborateurID', field: 'collaborator.id',width:"20%",visible:false},
                  { name: 'collaborateurLoginOpen', field: 'collaborator.loginOpen',width:"20%", visible:false},
                  { name: 'collaborateurEmail', field: 'collaborator.emailOpen',width:"20%",visible:false},
                  { name: 'Nom', field:'collaborator.firstName',width:"20%"},
                  { name: 'Prénom', field:'collaborator.lastName',width:"20%"},
                  { name: 'date de début', field:'periodStart',width:"10%"}, //for now, these are String
                  { name: 'date de fin', field:'periodEnd',width:"10%"},
                  { name: 'projetID' , field: 'project.id', visible:false},
                  { name: 'projet' , field: 'project.projectName', cellTooltip: function(row) { return row.entity.projet; },width:"20%"},
                  { name: 'statut', field: 'status',width:"10%"}
                  
                  ]
             };

    $scope.gridOptions.multiSelect = true;


    
  //REST resource for the current user
    //We might have to wait for AuthService to load the user
    var deferred= $q.defer();
        //resolve the promise when user is loaded
    var interval = setInterval(function() {
              //if not undefined
            //restURL = '/requestList/' + AuthService.userLogin();
             deferred.resolve('/authorisations');
         }, 50);
    //... and then we load the table data
    deferred.promise.then(function(restURL){
            clearInterval(interval);    //not sure is very clean
            $http.get(restURL)
                .then(function(response) {
                        //First function handles success
                        $scope.gridOptions.data = response.data;
                       //console.log("Mes données "+response.data+" \n"+restURL); // le 23/02/2017
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

