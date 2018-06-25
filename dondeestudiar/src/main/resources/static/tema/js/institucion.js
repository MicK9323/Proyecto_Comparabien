$(document).ready(function() {
	$('#listaUbigueos').hide();
	var nomSede = "";
	var ubigueo = "";
	var direccion = "";
	var telf = "";
	var cx = "";
	var cy = "";

	function limpiarCampos(){
		nomSede="";
		ubigueo="";
		direccion="";
		telf="";
		$('#nomSede').val("");
		$('#direccion').val("");
		$('#telfSede').val("");
		$('#ubiSede').val("");
		$('#listaUbigueos').hide();
	}
	
	$('#ubiSede').keyup(function(){
        var term = $('#ubiSede').val().trim().split(" ").join("_");        
        if( term.length >= 3 ){
            $('#spinner').toggleClass('d-none');
        	$('#listaUbigueos').load("/instituciones/cargar-ubigueo/"+term,function () {
                $('#spinner').toggleClass('d-none');
            })
        	$('#listaUbigueos').show()
        }        
    });
	
	$('#listaUbigueos').on('click','.ubicacion', function(){
		console.info('Click');
		ubigueo = $(this).find('.codUbigueo').val();
		$('#ubiSede').val($(this).find('.desc').text());
		$('#listaUbigueos').hide();
		console.info('Ubicacion: '+$('#ubiSede').val());
		console.info('Codigo: '+ubigueo);
	});

	function populateTable(data){
        $('#detSedes tbody').html("");
        if(data.length >= 1){
            for(i = 0; i < data.length; i++){
                var item = i+1;
                $('#detSedes tbody').append(
                    '<tr>'+
                    '<td class="text-center index">'+item+'</td>'+
                    '<td class="text-left">'+data[i]['nomSede']+'</td>'+
                    '<td class="text-left">'+data[i]['direccion']+'</td>'+
                    '<td>' +
                    '<div class="btn btn-outline-danger btnEliminar">' +
                    '<i class="fa fa-trash-o"></i>' +
                    '</div>' +
                    '</td>'+
                    '</tr>'
                );
            }
		}
	}

	$('#detSedes').on('click','.btnEliminar',function () {
		var index = $(this).parent().parent().find('.index').text();
		console.log(index);
		alertify.confirm("Está seguro de retirar esta sede",
			function () {
                $.ajax({
                    type: "GET",
                    url: "/instituciones/delsede/"+index,
                    timeout: 600000,
                    success: function (result) {
                        console.log(result);
                        populateTable(result);
                        alertify.message('Sede retirada!');
                    }
                });
        	},function () {
                alertify.message('Cancelado por el usuario');
            });
		return false;
    });
	
	$('#agregarSede').click(function(){
		console.info("click");
		nomSede = $('#nomSede').val().trim();
		direccion = $('#direccion').val().trim();
		telf = $('#telfSede').val().trim();
		cx = $('#cx').val().trim();
		cy = $('#cy').val().trim();
		console.info("Latitud: "+cx);
		console.info("Longitud: "+cy);
		if( nomSede == "" || direccion == "" || telf == "" || ubigueo == ""){
			alertify
			  .alert("Debe ingresar todos los campos!");
		}
		if(cx == "" || cy == "" ){
			alertify.alert("¡Debe localizar su sede antes de agregarla!");
		}else {
			alertify.confirm("¿Esta seguro de agregar esta sede?",
					  function(){
						$('#agregarSede').prop("disabled",true);
						$.ajax({
							type: "POST",
							url: "/instituciones/agregarsede",
							data: {nomSede: nomSede, ubigueo: ubigueo, direccion: direccion,
									telf: telf, cx: cx, cy: cy},
							timeout: 600000,
							success: function (result) {
                                limpiarCampos();
                                console.log(result);
                                populateTable(result);
                                $('#agregarSede').prop("disabled",false);
                                alertify.success('Sede Agregada');
                            },
							error: function (error) {
                                $('#agregarSede').prop("disabled",false);
								alertify.alert("Error: "+error);
                            }
						});
					  },
					  function(){
					    alertify.message('Cancelado por el usuario');
					  });
		}
	});
	

  // Basic
  $(".dropify").dropify();

  // Translated
  $(".dropify-fr").dropify({
    messages: {
      default: "Pulse para seleccionar una imagen",
      replace: "Pulse para reemplazar la imagen",
      remove: "Elimnar",
      error: "Error al leer el fichero"
    }
  });

  // Used events
  var drEvent = $("#input-file-events").dropify();

  drEvent.on("dropify.beforeClear", function(event, element) {
    return confirm(
      'Do you really want to delete "' + element.file.name + '" ?'
    );
  });

  drEvent.on("dropify.afterClear", function(event, element) {
    alert("File deleted");
  });

  drEvent.on("dropify.errors", function(event, element) {
    console.log("Has Errors");
  });

  var drDestroy = $("#input-file-to-destroy").dropify();
  drDestroy = drDestroy.data("dropify");
  $("#toggleDropify").on("click", function(e) {
    e.preventDefault();
    if (drDestroy.isDropified()) {
      drDestroy.destroy();
    } else {
      drDestroy.init();
    }
  });
});
