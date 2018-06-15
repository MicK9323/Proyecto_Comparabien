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
        	console.info('termino de busqueda: '+term);
        	$('#listaUbigueos').load("/instituciones/cargar-ubigueo/"+term)
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
	
	
	$('#agregarSede').click(function(){
		console.info("click");
		nomSede = $('#nomSede').val().trim();
		direccion = $('#direccion').val().trim();
		telf = $('#telfSede').val().trim();
		cx = $('#cx').val().trim();
		cy = $('#cy').val().trim();
		console.info("Latitud: "+cx);
		console.info("Longitud: "+cy);
		if( nomSede == "" || direccion == "" || telf == "" || ubigueo == "" || cx == "" || cy == "" ){
			alertify
			  .alert("Porfavor ingrese todos los campos");
		}else {
			alertify.confirm("Esta seguro de agregar esta sede?",
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
                                $('#detSedes tbody').html("");
                                for(i = 0; i < result.length; i++){
                                	$('#detSedes tbody').append(
                                    	'<tr>'+
											'<td class="col-md-6">'+result[i]['nomSede']+'</td>'+
											'<td class="col-md-6">'+result[i]['direccion']+'</td>'+
											'<td class="col-md-6">'+result[i]['telf']+'</td>'+
                                        '</tr>'
									);
								}
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
