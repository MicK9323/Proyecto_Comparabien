$(document).ready(function () {
    $('#listaCarreras').hide();

    var idCarrera = "";
    var nomCarrera = "";

    var models = new Array();
    var sedes = new Array();

    function Sede(id, sede) {
        this.id = parseInt(id);
        this.sede = sede;
    }

    function Model(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes) {
        this.idCarrera = parseInt(idCarrera);
        this.nomCarrera = nomCarrera;
        this.idSede = parseInt(idSede);
        this.nomSede = nomSede;
		this.acreditado = acreditado == "1" ? true : false;
		this.costo = parseFloat(costo);
        this.ingresantes = parseInt(ingresantes);
    }

    function boolToString(value) {
        if (value)
            return "Si";
        else
            return "No";
    }

    function deshabilitarBoton(boton, value) {
        if (value) {
            boton.prop('disabled', true);
        } else {
            boton.prop('disabled', false);
        }
    }

    function resetCarrera() {
        idCarrera = "";
        nomCarrera = "";
        $('#carrera').val("");
        $('#nomCarrera').val("");
        $('#idCarrera').val("");
    }

    // Seleccionar sedes

    function resetSede() {
        sedes = new Array();
        $('#tbSedes tbody tr').each(function () {
            $(this).removeClass('true');
            $(this).removeClass('text-success');
            $(this).find('.seleccionar').find('i').removeClass('fa-check-square-o').addClass('fa-square-o');
        });
    }

    function seleccionar(tr) {
        if ($(tr).hasClass('true')) {
            $(tr).removeClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-check-square-o').addClass('fa-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id, nom);
            sedes.forEach(function (value, index) {
                if (sede.id === value.id) {
                    sedes.splice(index, 1);
                }
            });
        } else {
            $(tr).addClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-square-o').addClass('fa-check-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id, nom);
            sedes.push(sede);
        }
    }

    function populateAsignar() {
        $('#tbAsignacion').html("");
        sedes.forEach(function (value, index) {
            $('#tbAsignacion').append(
                '<tr>' +
                '<td class="nomCarrera text-left">' + nomCarrera + '</td>' +
                '<td class="idCarrera d-none">' + idCarrera + '</td>' +
                '<td class="nomSede text-left">' + value.sede + '</td>' +
                '<td class="idSede d-none">' + value.id + '</td>' +
                '<td class="select text-center">' +
                '<select class="acreditado form-control input-sm custom-select">' +
                '<option value="1">Si</option>' +
                '<option value="0">No</option>' +
                '</select>' +
                '</td>' +
                '<td class="input1 text-center">' +
                '<input type="text" placeholder="Costo S/." class="costo form-control input-sm">' +
                '</td>' +
                '<td class="input2 text-center">' +
                '<input type="text" placeholder="%" class="ingresantes form-control input-sm">' +
                '</td>' +
                '<td class="text-center">' +
                '<button class="btnEnviarFila btn btn-outline-success">' +
                '<i class="mdi mdi-arrow-down-bold-hexagon-outline"></i>' +
                '</button>' +
                '</td>' +
                '<td>' +
                '<button class="btnBorrarFila btn btn-outline-danger">' +
                '<i class="fa fa-trash-o"></i>' +
                '</button>' +
                '</td>' +
                '</tr>'
            );
        });
        resetSede();
        resetCarrera();
        deshabilitarBoton($('#btnSedes'), true);
    }

    function populateAsignados() {
        $('#tbDetalle').html("");
        models.forEach(function (value, index) {
            $('#tbDetalle').append(
                '<tr>' +
                '<td class="text-center font-bold">' + (1 + index) + '</td>' +
                '<td class="d-none">' + index + '</td>' +
                '<td class="text-left font-bold">' + value.nomCarrera + '</td>' +
                '<td class="text-left font-bold">' + value.nomSede + '</td>' +
                '<td class="text-center font-bold">' + boolToString(value.acreditado) + '</td>' +
                '<td class="text-center font-bold">' + value.costo + '</td>' +
                '<td class="text-center font-bold">' + value.ingresantes + '</td>' +
                '<td class="text-center font-bold">' +
                '<button class="btnBorrarModel btn btn-outline-danger">' +
                '<i class="fa fa-trash-o"></i>' +
                '</button>' +
                '</td>' +
                '</tr>'
            );
        });
    }

    function eliminarFila(fila) {
        $(fila).parent().parent().remove();
    }

    function validar(idCarrera, nomCarrera, idSede, nomSede, costo, ingresantes) {
        if (idCarrera == "" || nomCarrera == "") {
            return false;
        } else if (idSede == "" || nomSede == "") {
            return false;
        } else if (costo == "") {
            return false;
        } else if (ingresantes == "") {
            return false;
        } else {
            return true;
        }
    }

    // Promesa
    function getAjaxResponse(idCarrera, idSede) {
        var options = {
            type: "POST",
            url: '/carreras/validar',
            data: {idCarrera: idCarrera, idSede: idSede},
            timeout: 600000
        }
        return $.ajax(options);
    }

    // Buscar Carreras
    $('#carrera').keyup(function () {
        var term = $(this).val().trim().split(" ").join("_");
        if (term == "") {
            $('#listaCarreras').hide();
        }
        if (term.length > 5) {
            $('#spinner').toggleClass('d-none');
            $('#listaCarreras').load("/carreras/autocompletar/" + term, function () {
                $('#spinner').toggleClass('d-none');
            });
            $('#listaCarreras').show();
        }
    });

    $('#listaCarreras').on('click', '.carrera', function () {
        idCarrera = $(this).find('.idCarrera').val();
        nomCarrera = $(this).find('.desc').text();
        deshabilitarBoton($('#btnSedes', false));
        $('#carrera').val("");
        $('#nomCarrera').val(nomCarrera);
        $('#idCarrera').val(idCarrera);
        $('#listaCarreras').hide();
    });

    // Seleccionar todas las sedes
    $('#btnAll').click(function () {
        $('#tbSedes tbody tr').each(function () {
            seleccionar($(this));
        })
    });

    $('.fila').click(function () {
        seleccionar($(this));
    })

    $('#btnCancelar').click(function () {
        resetSede();
    });

    $('#btnAceptar').click(function () {
        populateAsignar();
    });

    $('#tbAsignacion').on('click', '.btnEnviarFila', function () {
        var idCarrera = $(this).parent().parent().find('.idCarrera').text();
        var nomCarrera = $(this).parent().parent().find('.nomCarrera').text();
        var idSede = $(this).parent().parent().find('.idSede').text();
        var nomSede = $(this).parent().parent().find('.nomSede').text();
        var acreditado = $(this).parent().parent().find('.select').find('select').val();
        var costo = $(this).parent().parent().find('.input1').find('.costo').val();
        var ingresantes = $(this).parent().parent().find('.input2').find('.ingresantes').val();
        if (validar(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes)) {
            var model = new Model(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes);
            var fila = $(this);
            var promise = getAjaxResponse(model.idCarrera, model.idSede);
            promise.done(function (result) {
                if (result == true) {
                    if (models.length >= 1) {
                        models.forEach(function (value, index, array) {
                            if (model.idSede == value.idSede && model.idCarrera == value.idCarrera) {
                                alertify.alert('Ya ingreso este registro!');
                                throw array;
                            } else {
                                models.push(model);
                                eliminarFila(fila);
                                populateAsignados();
                            }
                        });
                    } else {
                        models.push(model);
                        $(fila).parent().parent().remove();
                        populateAsignados();
                    }
                } else {
                    alertify.alert('Este registro ya se encuentra en la base de datos');
                }
            })

        } else {
            alertify.alert("Ingrese todos los campos");
        }
    });

    $('#tbAsignacion').on('click', '.btnBorrarFila', function () {
        var fila = $(this);
        alertify.confirm('Seguro de eliminar este registro?',
            function () {
                eliminarFila(fila);
            },
            function () {
                // no quiero hacer nada
            });
    });

    $('#tbDetalle').on('click','.btnBorrarModel',function () {
        console.log('Antes de eliminar: '+models);
        var fila = $(this);
        var index = $(this).parent().parent().find('.index').text();
        alertify.confirm('Esta seguro de borrar este registro?',
            function () {
                models.splice(index,1);
                eliminarFila(fila);
                console.log('Luego de eliminar: '+models);
            },function () {
                // No quiero hacer nada
            });
    });

    $('#btnRegistrar').click(function () {
        if(models.length == 0){
            alertify.alert('No ha ingresado ningun registro');
        }else{
            alertify.confirm('Esta seguro registrar todos los registros ingresados?',
                function () {
                    $.ajax({
                        type: "POST",
                        url: '/carreras/detalle/guardar',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(models),
                        success: function (data) {
                            console.log(data.responseText);
                        },
                        error: function (error) {
                            var url = error.responseText;
                            console.log(error.responseText);
                            $(location).attr("href",url);
                        }
                    });
                },function () {
                    // TODO
                });
        }
    });

})