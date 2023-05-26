function listarConsolas() { // Llama al servlet para recuperar las el nombre y el id de las consolas del usuario
    fetch("ListarConsolas")
    .then(resultado => resultado.json())
    .then(resultado => pintarNomConsola(resultado));
}

function pintarNomConsola(datos) { // Utilizada en la funcion listarConsolas() para pintarlo en el html
    for(var i=0;i<datos.length;i++){
        let nConsola = document.createElement("OPTION");
        nConsola.value = datos[i].id;
        nConsola.text = datos[i].nombre;
        document.querySelector("#ag-consJue").appendChild(nConsola);
    }
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

function enviado() { // Llamada al aviso con url del servlet para confirmar envio de formulario
    if (location.search.includes("validado")) {
        aviso("validado", "Juego añadido a la biblioteca");
    }   
}

function validacion() { // Validacion de formulario
    document.querySelector("#ag-juego").addEventListener("click", function(event) {
        event.preventDefault(); 
        let valido = true;
        let nombre = document.querySelector("#ag-nomJue").value.trim();
        let formato = document.querySelector("#ag-forJue").value;
        let consola = document.querySelector("#ag-consJue").value;
        let estado = document.querySelector("#ag-estJue").value;
        let edicion = document.querySelector("#ag-ediJue").value.trim();
        let genero = document.querySelector("#ag-genJue").value.trim();

        if (nombre === "" || formato === "" || consola === "" || estado === "") {
            aviso("error", "Los campos Nombre, Formato, Consola y Estado son obligatorios");
            valido = false;
        }

        if (nombre.length > 80) {
            aviso("error", "El campo Nombre no debe tener más de 80 caracteres");
            valido = false;
        }
    
        if (edicion.length > 30) {
            aviso("error", "El campo Edicion no debe tener más de 30 caracteres");
            valido = false;
        }
    
        if (genero.length > 30) {
            aviso("error", "El campo Género no debe tener más de 30 caracteres");
            valido = false;
        }

        if (valido) {
            document.querySelector("#formularioJuego").submit(); 
        }
    });
}

window.onload = function(){
    enviado();
    listarConsolas();
    validacion();
}



