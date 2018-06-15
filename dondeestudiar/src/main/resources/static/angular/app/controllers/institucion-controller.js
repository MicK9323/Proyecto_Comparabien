institucionApp.controller("InstitucionController", function($scope) {
	$scope.title = "";

	$scope.txtRuc = {
		text : "",
		maxLength : 11,
		enabled : false,
		visible : true
	};

	$scope.txtNombre = {
		text : "",
		maxLength : 50,
		enabled : false,
		visible : true
	};

	$scope.txtSitioWeb = {
		text : "",
		maxLength : 80,
		enabled : false,
		visible : true
	};

	$scope.txtCentralTelf = {
		text : "",
		maxLength : 15,
		enabled : false,
		visible : true
	};

	

	$scope.init = function() {

	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});