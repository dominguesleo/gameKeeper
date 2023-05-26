const consolaSeleccionada = JSON.parse(localStorage.getItem("consolaSeleccionada")); // Consola seleccionada almacenada en el almacenamiento local

function copiarConsola() { // Pinta los datos de la consola seleccionada en el html
    let portada = document.querySelector("#ed-portadaCons");
    if (consolaSeleccionada.foto != null) {
        portada.src = "fotosConsolas/"+consolaSeleccionada.foto;
        portada.alt = consolaSeleccionada.nombre;
    } else {
        portada.src = "";
    }
    document.querySelector("#ed-idCons").value = consolaSeleccionada.id || "";
    document.querySelector("#ed-nomCons").value = consolaSeleccionada.nombre || "";
    document.querySelector("#ed-desCons").value = consolaSeleccionada.desarrollador || "";
    document.querySelector("#ed-ediCons").value = consolaSeleccionada.edicion || "";
    document.querySelector("#ed-fechaCons").value = consolaSeleccionada.fecha || "";     
}

function aviso(estado, mensaje) { //Estructura para crear el aviso 
    let aviso = document.createElement("DIV");
    aviso.textContent = mensaje;
    aviso.classList.add(estado);
    document.querySelector("main").appendChild(aviso);
    setTimeout(() => {
        aviso.remove();
    }, 3000); 
}

function validacion() { // Validacion de formulario
    document.querySelector("#ed-consola").addEventListener("click", function(event) {

        event.preventDefault();
        let valido = true;
        let nombre = document.querySelector("#ed-nomCons").value.trim();
        let desarrollador = document.querySelector("#ed-desCons").value.trim();
        let edicion = document.querySelector("#ed-ediCons").value.trim();

        if (nombre === "" || desarrollador === "") {
            aviso("error", "Los campos Nombre y Desarrollador son obligatorios");
            valido = false;
        }

        if (nombre.length > 30) {
            aviso("error", "El campo Nombre no debe tener más de 30 caracteres");
            valido = false;
        }

        if (desarrollador.length > 30) {
            aviso("error", "El campo Desarrollador no debe tener más de 30 caracteres");
            valido = false;
        }
    
        if (edicion.length > 30) {
            aviso("error", "El campo Edición no debe tener más de 30 caracteres");
            valido = false;
        }
  
        if (valido) {
            document.querySelector("#editarConsola").submit(); 
        }
    });
}

window.onload = function(){
    copiarConsola();
    validacion();
}