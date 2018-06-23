carreraApp.controller("CarreraController", function($scope) {
	$scope.title = "";

	$scope.init = function() {

	};

	$scope.txtDuracion = {
		text : "",
		length : 2,
		enabled : false,
		visible : true
	};

	$scope.txtNombre = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};

	$scope.txtPopularidad = {
		text : "",
		length : 2,
		enabled : false,
		visible : true
	};

	$scope.txtRemuneracion = {
		text : "",
		length : 6,
		enabled : false,
		visible : true
	};
	
	$scope.txtBuscar = {
		text : "",
		length : 25,
		enabled : false,
		visible : true
	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});