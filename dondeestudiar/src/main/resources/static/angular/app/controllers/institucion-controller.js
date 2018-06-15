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
		length : 80,
		enabled : false,
		visible : true
	};

	$scope.txtCentralTelf = {
		text : "",
		length : 15,
		enabled : false,
		visible : true
	};

	

	$scope.init = function() {

	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});