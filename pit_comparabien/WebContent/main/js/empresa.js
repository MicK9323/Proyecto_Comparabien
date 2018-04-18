$(document).ready(function() {
  var arrayDep = [];
  var arrayCod = [];
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

        // actualizar la lista de departamentos
      }

      function mostrarDatos() {
        console.log("Codigo:" + arrayCod);
        console.log("Departamentos: " + arrayDep);
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
        // $('#departamentos').children('span').fadeOut('fast',function(){$(this).remove();});
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

  //	Subir imagen

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