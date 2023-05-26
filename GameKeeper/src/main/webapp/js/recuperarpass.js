function aviso(estado1, estado2, mensaje) { //Estructura para crear el aviso
    let aviso = document.createElement("DIV");
    aviso.textContent = mensaje;
    aviso.classList.add(estado1, estado2);
    document.querySelector("main").after(aviso);
    setTimeout(() => {
        aviso.remove();
    }, 3000); 
}

function errorMail() { // Llamada al aviso con url del servlet en caso de que el mail no este registrado
    if (location.search.includes("error")) {
        aviso("error", "mini", "Correo no registrado");
      }    
}

function validacion() { // Validacion de formulario
    document.querySelector("#re-pass").addEventListener("click", function(event) {

        event.preventDefault();
        
        let valido = true;
        let correo = document.querySelector("#re-mail").value.trim();
        let password = document.querySelector("#re-password").value.trim();

        let regexCorreo = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;

        if (correo === "" || password === "") {
            aviso("error", "mini", "Los campos Correo y Password son obligatorios");
            valido = false;
        }

        if (correo.length > 1 && !regexCorreo.test(correo)) {
            aviso("error", "mini", "Por favor, introduce un correo válido");
            valido = false;
        }

        if (correo.length > 50) {
            aviso("error", "mini", "El campo Correo no debe tener más de 50 caracteres");
            valido = false;
        }

        if (password.length > 0 && password.length < 8) {
            aviso("error", "mini", "El campo Password no debe tener menos de 8 caracteres");
            valido = false;
        }
    
        if (password.length > 45) {
            aviso("error", "mini", "El campo Password no debe tener más de 45 caracteres");
            valido = false;
        }

        if (valido) {
            document.querySelector("#formularioPassword").submit(); 
        }
    });
}

window.onload = function(){
    validacion();
    errorMail();
}