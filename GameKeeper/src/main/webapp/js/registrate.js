
function aviso(estado, mensaje) { //Estructura para crear el aviso
    let aviso = document.createElement("DIV");
    aviso.textContent = mensaje;
    aviso.classList.add(estado);
    document.querySelector("main").after(aviso);
    setTimeout(() => {
        aviso.remove();
    }, 3000); 
}

function duplicado() { // Llamada al aviso con url del servlet en caso de existir un usuario con el mismo correo
    if (location.search.includes("error")) {
        aviso("error", "Ya existe un usuario con el correo indicado");
      } 
}

function validacion() { // Validacion de formulario
    document.querySelector("#registro").addEventListener("click", function(event) {

        event.preventDefault();
        
        let valido = true;
        let nombre = document.querySelector("#nombre").value.trim();
        let apellido = document.querySelector("#apellido").value.trim();
        let fecha = document.querySelector("#fecha").value;
        let pais = document.querySelector("#pais").value.trim();
        let correo = document.querySelector("#correo").value.trim();
        let password = document.querySelector("#password").value.trim();

        let regexCorreo = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;

        if (nombre === "" || apellido === "" || correo === "" || password === "") {
            aviso("error", "Los campos Nombre, Apellido, Correo y Password son obligatorios");
            valido = false;
        }

        if (nombre.length > 45) {
            aviso("error", "El campo Nombre no debe tener más de 45 caracteres");
            valido = false;
        }
    
        if (apellido.length > 80) {
            aviso("error", "El campo Apellido no debe tener más de 80 caracteres");
            valido = false;
        }
    
        if (correo.length > 50) {
            aviso("error", "El campo Correo no debe tener más de 50 caracteres");
            valido = false;
        }

        if (password.length > 0 && password.length < 8) {
            aviso("error", "El campo Password no debe tener menos de 8 caracteres");
            valido = false;
        }
    
        if (password.length > 45) {
            aviso("error", "El campo Password no debe tener más de 45 caracteres");
            valido = false;
        }
    
        if (pais.length > 25) {
            aviso("error", "El campo País no debe tener más de 25 caracteres");
            valido = false;
        }

        if (correo.length > 1 && !regexCorreo.test(correo)) {
            aviso("error", "Por favor, introduce un correo válido");
            valido = false;
        }

        var fechaActual = new Date();
        var fechaIntroducida = new Date(fecha);
        if (fechaIntroducida > fechaActual) {
            aviso("error", "La fecha introducida no puede ser mayor a la fecha actual");
            valido = false;
        }

        if (valido) {
            document.querySelector("#formularioRegistro").submit(); 
        }
    });
}

window.onload = function(){
    validacion();
    duplicado();
}
