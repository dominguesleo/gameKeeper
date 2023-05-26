const juegoSeleccionado = JSON.parse(localStorage.getItem("juegoSeleccionado")); // Juego seleccionado almacenado en el almacenamiento local

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
        if(datos[i].id == juegoSeleccionado.consola) {
            nConsola.selected = true;
        }
        document.querySelector("#ed-consJue").appendChild(nConsola);
    }
}

function copiarJuego() { // Pinta los datos del juego seleccionado en el html 
    let portada = document.querySelector("#ed-portadaJue");
    if (juegoSeleccionado.foto != null) {
        portada.src = "fotosJuegos/"+juegoSeleccionado.foto;
        portada.alt = juegoSeleccionado.nombre;
    } else {
        portada.src = "";
    }
    document.querySelector("#ed-idJue").value = juegoSeleccionado.id || "";
    document.querySelector("#ed-nomJue").value = juegoSeleccionado.nombre || "";
    document.querySelector("#ed-forJue").value = juegoSeleccionado.formato || null;
    document.querySelector("#ed-estJue").value = juegoSeleccionado.estado || null;   
    document.querySelector("#ed-ediJue").value = juegoSeleccionado.edicion || "";
    document.querySelector("#ed-genJue").value = juegoSeleccionado.genero || "";
    document.querySelector("#ed-fechaJue").value = juegoSeleccionado.fecha || "";
    document.querySelector("#ed-valJue").value = juegoSeleccionado.valoracion || null;     
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
    document.querySelector("#ed-juego").addEventListener("click", function(event) {

        event.preventDefault();
        let valido = true;
        let nombre = document.querySelector("#ed-nomJue").value.trim();
        let formato = document.querySelector("#ed-forJue").value;
        let consola = document.querySelector("#ed-consJue").value;
        let estado = document.querySelector("#ed-estJue").value;
        let edicion = document.querySelector("#ed-ediJue").value.trim();
        let genero = document.querySelector("#ed-genJue").value.trim();

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
            document.querySelector("#editarJuego").submit(); 
        }
    });
}

window.onload = function(){
    listarConsolas(); 
    copiarJuego();  
    validacion(); 
}
