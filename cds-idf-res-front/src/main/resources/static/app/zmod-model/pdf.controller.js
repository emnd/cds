'use strict';
/**
 * @ngdoc function
 * @name odeadomApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the odeadomApp
 */
angular.module('app').controller('PdfController', PdfController);
    
	  
	  PdfController.$inject = ['$stateParams','$scope', '$rootScope'];

	  function PdfController($stateParams,$scope, $rootScope)
	  {
        var firstName, lastName, loginOpen,  buOpen, emailOpen; // collaborator
        var name, serialNumber, mark, model, attributionDate, comments, type; // equipement
        var data, objet;
        $scope.data = $stateParams.obj; // recuperation des données envoyées depuis form.ajout.equipement.controller.js

	    console.log("Dans pdf controller: ");
	    $scope.objet = JSON.parse($scope.data); // parsage en JSON de l'objet string
	    console.log($scope.data);
	    console.log($scope.objet);
	    $scope.firstName = $scope.objet.firstName;
        $scope.lastName = $scope.objet.lastName;
        $scope.loginOpen = $scope.objet.loginOpen;
        $scope.buOpen = $scope.objet.buOpen;
        $scope.emailOpen = $scope.objet.emailOpen;
        //
        $scope.name = $scope.objet.name;
        $scope.serialNumber = $scope.objet.serialNumber;
        $scope.mark = $scope.objet.mark;
        $scope.model = $scope.objet.model;
        $scope.attributionDate = $scope.objet.attributionDate;
        $scope.comments = $scope.objet.comments;
        $scope.type = $scope.objet.type;

        angular.forEach($scope.objet, function(value, key) {
          console.log(key + ': ' + value);
        });

	  }
  