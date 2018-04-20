$(document).ready(function() {

  var arrayDep = [];
  var arrayCod = [];
  var departamentos = [];
  
  function Departamento(codigo, nombre) {
	  this.codigo = codigo;
	  this.nombre = nombre;
  }
  
  function imagenBase64(imagen){
	  this.imagen = imagen
  }
  
  function mostrarDatos() {
      console.log('Codigos: '+arrayCod.toString());
      console.log('Departamentos: '+arrayDep.toString());
    }
    
    function eliminarItem(array, i){
  	  array.splice(i,1);
    }

    function modArray(cod, dep) {    	  
      if (arrayCod.indexOf(cod) === -1) {
        arrayCod.push(cod);
        arrayDep.push(dep);
        console.log("Agrega");
      } else {
        arrayCod.splice(arrayCod.indexOf(cod), 1);
        arrayDep.splice(arrayDep.indexOf(dep), 1);
        console.log("Elimina");
      }
    }

    function pintarLista() {
      $("#departamentos").html("");
      for (var i = 0; i < arrayDep.length; i++) {
        $("#departamentos").fadeIn("slow", function() {
          $("#departamentos").append(
            '<span class="badge badge-pill badge-primary">' +
              arrayDep[i] +
              "</span>"
          );
        });
      }
    }
    
    function generarJSONArray(){
    	if( arrayCod.length === arrayDep.length ){
    		for( var i=0; i < arrayCod.length; i++ ){
    			var obj = new Departamento(arrayCod[i],arrayDep[i]);
    			departamentos.push(obj);    			
    		}    		
    	}
    	arrayCod = [];
		arrayDep = [];
    	return (JSON.stringify(departamentos));
    }
    
    function getBase64FromFile(img, callback){
    	  let fileReader = new FileReader();
    	  fileReader.addEventListener('load', function(evt){
    	    callback(fileReader.result);
    	  });
    	  fileReader.readAsDataURL(img);
    	};
  
  $(function() {
    $(".list-group.checked-list-box .list-group-item").each(function() {
      // Settings
      var $widget = $(this),
        $checkbox = $('<input type="checkbox" class="hidden" />'),
        color = $widget.data("color") ? $widget.data("color") : "primary",
        style = $widget.data("style") == "button" ? "btn-" : "list-group-item-",
        settings = {
          on: {
            icon: "mdi mdi-checkbox-marked-outline"
          },
          off: {
            icon: "mdi mdi-checkbox-blank-outline"
          }
        };

      $widget.css("cursor", "pointer");
      $widget.append($checkbox);

      // Event Handlers
      $widget.on("click", function() {
        $checkbox.prop("checked", !$checkbox.is(":checked"));
        $checkbox.triggerHandler("change");
        updateDisplay();
      });
      $checkbox.on("change", function() {
        var cod = $widget.find('#id').text();
        var nom = $widget.find('#nombre').text();
        modArray(cod, nom);
        updateDisplay();
        pintarLista();
        mostrarDatos();
      });

      // Actions
      function updateDisplay() {
        var isChecked = $checkbox.is(":checked");

        // Set the button's state
        $widget.data("state", isChecked ? "on" : "off");

        // Set the button's icon
        $widget
          .find(".state-icon")
          .removeClass()
          .addClass("state-icon " + settings[$widget.data("state")].icon);

        // Update the button's color
        if (isChecked) {
          $widget.addClass(style + color + " active");
        } else {
          $widget.removeClass(style + color + " active");
        }
      }

      

      // Initialization
      function init() {
        if ($widget.data("checked") == true) {
          $checkbox.prop("checked", !$checkbox.is(":checked"));
        }

        updateDisplay();

        // Inject the icon if applicable
        if ($widget.find(".state-icon").length == 0) {
          $widget.prepend(
            '<span class="state-icon ' +
              settings[$widget.data("state")].icon +
              '"></span>'
          );
        }
      }
      init();
    });

    $("#get-checked-data").on("click", function(event) {
      cargarItems(event);
    });

    function cargarItems(event) {
      event.preventDefault();
      var checkedItems = {},
        counter = 0;
      $("#check-list-box li.active").each(function(idx, li) {
        checkedItems[counter] = $(li).text();
        counter++;
      });
      $("#display-json").html(JSON.stringify(checkedItems, null, "\t"));
    }
  });
  
// Consultar Ruc
  $('#btnConsulta').click(function(){
	  var tecactusApi = new TecactusApi("9CP0vrdxq6OJbSw2iqjsWS9auuHXlRb8pxgeyiEp")
	  tecactusApi.Sunat.getByRuc($('#ruc').val())
	  .then(function (response) {
          console.log(response.data)
          if( response.data.ruc == null ){
        	  swal("Error!", "Ruc Invalido", "error");
          }else{
        	  $('#razonsoc').val(response.data.razon_social);
    		  $('#dir').val(response.data.direccion);
          }		  
	  })
	  .catch(function (response) {
	      console.log("algo ocurrió")
	      console.log("código de error: " + response.code)
	      console.log("mensaje de respuesta: " + response.status)
	      console.log(response.data)
	      swal("Error!", response.status, "error");
	      
	  })
  })
  
  
  $('#btnGuardar').click(function() {
	  var ruc = $('#ruc').val();
	  var raz = $('#razonsoc').val();
	  var telf = $('#telf').val();
	  var dir = $('#dir').val();
	  var email = $('#email').val();
	  var deps = generarJSONArray();
	  $.ajax({
		  type: "POST",
            url:"submitEmpresa",
            data:"empresa.ruc_empresa="+ruc+"&empresa.nom_empresa="+raz+"&empresa.telf_empresa="+telf+
            		"&empresa.dir_empresa="+dir+"&empresa.email_empresa="+email+"&empresa.cobertura_dep="+deps,
// "&empresa.logo="+imgB64,
            success:function(data){
              var msg = data.mensaje;
              if(msg == 'ok'){
            	  swal("Empresa Registrada", {
        		      icon: "success",
        		    });
                setTimeout(function(){
                  $(location).attr("href","listaEmpresas");
                },1000);
              }else{
            	  swal("Error!", msg, "error");
              }
            },
            error:function(data){
              var msg = data.mensaje;
              swal("Error!", msg, "error");
            }
	  });
// var input = document.querySelector('#input-file-now');
// var archivo = input.files[0];
// getBase64FromFile(archivo, function(base64){
// var imgB64 = JSON.stringify(base64);
// console.log(imgB64);
//		  
// });
  });
  
 
  // Actualizar empresa
  $('#btnUpdate').click(function() {
	  var cod = $('#cod').val();
	  var telf = $('#telf').val();
	  var dir = $('#dir').val();
	  var email = $('#email').val();
	  console.log(cod);
	  console.log(telf);
	  console.log(dir);
	  console.log(email);
	  $.ajax({
		  type: "POST",
            url:"uptEmpresa",
            data:"empresa.id_emp="+cod+"&empresa.telf_empresa="+telf+"&empresa.dir_empresa="+
            	dir+"&empresa.email_empresa="+email,
            success:function(data){
              var msg = data.mensaje;
              if(msg == 'ok'){
            	  swal("Empresa Actualizada", {
        		      icon: "success",
        		    });
                setTimeout(function(){
                  $(location).attr("href","listaEmpresas");
                },1000);
              }else{
            	  swal("Error!", msg, "error");
              }
            },
            error:function(data){
              var msg = data.mensaje;
              swal("Error!", msg, "error");
            }
	  });
  });
  
  
  
  

  // Subir imagen

  // Basic
  $(".dropify").dropify();

  // Translated
  $(".dropify-fr").dropify({
    messages: {
      default: "Glissez-déposez un fichier ici ou cliquez",
      replace: "Glissez-déposez un fichier ou cliquez pour remplacer",
      remove: "Supprimer",
      error: "Désolé, le fichier trop volumineux"
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
