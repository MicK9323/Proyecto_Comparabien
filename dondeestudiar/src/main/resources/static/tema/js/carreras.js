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
        if (acreditado == "1") {
            this.acreditado = true;
        } else {
            this.acreditado = false;
        }
        this.costo = parseFloat(costo);
        this.ingresantes = parseInt(ingresantes);
    }

    function deshabilitarBoton(boton,value){
        if(value){
            boton.prop('disabled',true);
        }else{
            boton.prop('disabled',false);
        }
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
        deshabilitarBoton($('#btnSedes',false));
        $('#carrera').val("");
        $('#nomCarrera').val(nomCarrera);
        $('#idCarrera').val(idCarrera);
        $('#listaCarreras').hide();
    });

    function resetCarrera(){
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

    function seleccionar(tr){
        if($(tr).hasClass('true')){
            $(tr).removeClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-check-square-o').addClass('fa-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id,nom);
            sedes.forEach(function (value, index) {
                if(sede.id === value.id){
                    sedes.splice(index,1);
                }
            });
        }else{
            $(tr).addClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-square-o').addClass('fa-check-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id,nom);
            sedes.push(sede);
        }
    }

    function populateAsignar(){
        $('#tbAsignacion').html("");
        sedes.forEach(function (value, index) {
           $('#tbAsignacion').append(
             '<tr>' +
               '<td class="nomCarrera text-left">'+nomCarrera+'</td>'+
               '<td class="idCarrera d-none">'+idCarrera+'</td>'+
               '<td class="nomSede text-left">'+value.sede+'</td>'+
               '<td class="idSede d-none">'+value.id+'</td>'+
               '<td class="select text-center">' +
                '<select class="acreditado form-control input-sm custom-select">' +
                    '<option value="1">Si</option>' +
                    '<option value="0">No</option>' +
                '</select>'+
               '</td>'+
               '<td class="input1 text-center">' +
                '<input type="text" placeholder="Costo S/." class="costo form-control input-sm">' +
               '</td>'+
               '<td class="input2 text-center">' +
                '<input type="text" placeholder="%" class="ingresantes form-control input-sm">' +
               '</td>'+
               '<td class="text-center">' +
                '<button class="btnEnviarFila btn btn-outline-success">' +
                    '<i class="mdi mdi-arrow-down-bold-hexagon-outline"></i>' +
                '</button>'+
               '</td>'+
             '</tr>'
           );
        });
        resetSede();
        resetCarrera();
        deshabilitarBoton($('#btnSedes'),true);
    }

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

    $('#tbAsignacion').on('click','.btnEnviarFila',function () {
        var idCarrera = $(this).parent().parent().find('.idCarrera').text();
        var nomCarrera = $(this).parent().parent().find('.nomCarrera').text();
        var idSede = $(this).parent().parent().find('.idSede').text();
        var nomSede = $(this).parent().parent().find('.nomSede').text();
        var acreditado = $(this).parent().parent().find('.select').find('select').val();
        var costo = $(this).parent().parent().find('.input1').find('.costo').val();
        var ingresantes = $(this).parent().parent().find('.input2').find('.ingresantes').val();
        if(validar(idCarrera,nomCarrera,idSede,nomSede,acreditado,costo,ingresantes)){
            var model = new Model(idCarrera,nomCarrera,idSede,nomSede,acreditado,costo,ingresantes);
            /*if(models.length>=1){
                models.forEach(function (value) {
                    if(model.idSede == value.idSede && model.idCarrera == value.idCarrera){
                        alertify.alert('Ya ingreso este registro!');
                    }else{
                        models.push(model);
                    }
                    $(this).parent().parent().remove();
                    console.log(models);
                })
            }else{
                models.push(model);
                $(this).parent().parent().remove();
                console.log(models);
            }*/
        }else{
            alertify.alert("Ingrese todos los campos");
        }
    });

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


})