institucionApp.controller("InstitucionController", function($scope) {
	$scope.title = "";

	$scope.txtRuc = {
		text : "",
		length : 11,
		enabled : false,
		visible : true
	};

	$scope.txtNombre = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};

	$scope.txtSitioWeb = {
		text : "",
		length : 70,
		enabled : false,
		visible : true
	};

	$scope.txtCentralTelf = {
		text : "",
		length : 12,
		enabled : false,
		visible : true
	};
	
	$scope.txtNomSede = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};
	
	$scope.txtDirSede = {
		text : "",
		length : 70,
		enabled : false,
		visible : true
	};
	
	$scope.txtTefSede = {
		text : "",
		length : 10,
		enabled : false,
		visible : true
	};
	
	$scope.Ubicacion = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};

	

	$scope.init = function() {

	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});