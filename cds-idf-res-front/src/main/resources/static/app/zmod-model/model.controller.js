/*
                   .___     .__                         __                .__  .__
  _____   ____   __| _/____ |  |     ____  ____   _____/  |________  ____ |  | |  |   ___________
 /     \ /  _ \ / __ |/ __ \|  |   _/ ___\/  _ \ /    \   __\_  __ \/  _ \|  | |  | _/ __ \_  __ \
|  Y Y  (  <_> ) /_/ \  ___/|  |__ \  \__(  <_> )   |  \  |  |  | \(  <_> )  |_|  |_\  ___/|  | \/
|__|_|  /\____/\____ |\___  >____/  \___  >____/|___|  /__|  |__|   \____/|____/____/\___  >__|
      \/            \/    \/            \/           \/                                  \/
*/

  'use strict';

  angular.module('app').controller('ModelController', ModelController);

  ModelController.$inject = ['$rootScope', '$scope', '$timeout', '$http', '$log', 'uiGridConstants'];

  function ModelController($rootScope, $scope, $timeout, $http, $log, uiGridConstants) {
    $rootScope.moduleLoaded = false;

    $scope.gridOptions = {
        enableRowSelection: true,
        enableSelectAll: true,
        selectionRowHeaderWidth: 35,
        rowHeight: 35,
        columnWidth: 25,
        showGridFooter:true
      };

    $scope.gridOptions.columnDefs = [
        { name: 'id' },
        { name: 'name'},
        { name: 'age', displayName: 'Age (not focusable)', allowCellFocus : false },
        { name: 'address.city' }
    ];

    $scope.gridOptions.multiSelect = true;

    $http.get('/mocked-data/500_complex.json')
    .success(function(data) {
      $scope.gridOptions.data = data;
      $timeout(function() {
        if($scope.gridApi.selection.selectRow){
          $scope.gridApi.selection.selectRow($scope.gridOptions.data[0]);
        }
      });
    });

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

