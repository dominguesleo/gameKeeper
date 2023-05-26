function aviso(estado1, estado2, mensaje) {  //Estructura para crear el aviso
    let aviso = document.createElement("DIV");
    aviso.textContent = mensaje;
    aviso.classList.add(estado1, estado2);
    document.querySelector("ul").after(aviso);
    setTimeout(() => {
        aviso.remove();
    }, 3000); 
  }
  
function mensaje() { // Llamada al aviso con url del servlet
    if (location.search.includes("bloqueado")) {
        aviso("validado", "mini", "Usuario bloqueado");
    } else if (location.search.includes("desbloqueado")) {
        aviso("validado", "mini", "Usuario desbloqueado");
    } else if (location.search.includes("admin")) {
        aviso("validado", "mini", "Se otorgaron los permisos");
    }   
}

function validacion(boton, input, formulario) { // Validacion de formulario
    document.querySelector(boton).addEventListener("click", function(event) {
        event.preventDefault();
        let valido = true;
        let campo = document.querySelector(input).value.trim();
        if (campo === "") {
            aviso("error", "mini", "Intruduce un ID"); // Campo sin agregar id
            valido = false;
        }
        if (!/^\d+$/.test(campo) && campo !== "") { // Si no corresponde a un valor numerico
            aviso("error", "mini", "El ID debe ser un número");
            valido = false;
        }
        if (valido) {
            document.querySelector(formulario).submit(); 
        }
    });
}

window.onload = function(){
    mensaje();
    validacion("#bloquear", "#bloquearUsuario", "#formBloq");
    validacion("#desbloquear", "#desbloquearUsuario","#formDesbloq");
    validacion("#admin", "#hacerAdmin", "#formAdmin");
}
