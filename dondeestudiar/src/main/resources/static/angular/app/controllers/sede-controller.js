sedeApp.controller("SedeController", function($scope) {
	$scope.title = "";

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