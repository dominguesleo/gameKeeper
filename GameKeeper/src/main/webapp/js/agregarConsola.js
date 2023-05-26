function aviso(estado, mensaje) {  //Estructura para crear el aviso
    let aviso = document.createElement("DIV");
    aviso.textContent = mensaje;
    aviso.classList.add(estado);
    document.querySelector("main").appendChild(aviso);
    setTimeout(() => {
        aviso.remove();
    }, 3000); 
}

function enviado() { // Llamada al aviso con url del servlet para confirmar envio de formulario
    if (location.search.includes("validado")) {
        aviso("validado", "Consola añadida a la biblioteca");
    }   
}

function validacion() { // Validacion de formulario
    document.querySelector("#ag-consola").addEventListener("click", function(event) {

        event.preventDefault();
        let valido = true;
        let nombre = document.querySelector("#ag-nomCons").value.trim();
        let desarrollador = document.querySelector("#ag-desCons").value.trim();
        let edicion = document.querySelector("#ag-ediCons").value.trim();

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
            document.querySelector("#formularioConsola").submit(); 
        }
    });
}

window.onload = function(){
    enviado();
    validacion();
}
