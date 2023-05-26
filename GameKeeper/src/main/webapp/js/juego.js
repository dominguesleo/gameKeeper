const juegoSeleccionado = JSON.parse(localStorage.getItem("juegoSeleccionado")); // Juego seleccionado almacenado en el almacenamiento local

function pintarConsolas() { // Llama al servlet para recuperar las el nombre y el id de las consolas del usuario
    fetch("ListarConsolas")
    .then(resultado => resultado.json())
    .then(resultado => pintarNomConsola(resultado));
    }
    
function pintarNomConsola(datos) { // Utilizada en la funcion listarConsolas() para pintarlo en el html
    let encontrado = false;
    let i = 0;
    while(!encontrado && i < datos.length) {
        if (datos[i].id == juegoSeleccionado.consola ) {
        document.querySelector("#consolaJuego").textContent = datos[i].nombre || "";
        encontrado = true;
        }
        i++;
    }
}

function copiarJuego() { // Pinta los datos del juego seleccionado en el html 
    let portada = document.querySelector("#portadaJuego");
    if (juegoSeleccionado.foto != null) {
        portada.src = "fotosJuegos/"+juegoSeleccionado.foto;
        portada.alt = juegoSeleccionado.nombre;
    }else {
        portada.src = "";
    }
    document.querySelector("#nombreJuego").textContent = juegoSeleccionado.nombre || "";
    document.querySelector("#formatoJuego").textContent = juegoSeleccionado.formato || "";
    document.querySelector("#estadoJuego").textContent = juegoSeleccionado.estado || "";   
    document.querySelector("#edicionJuego").textContent = juegoSeleccionado.edicion || "";
    document.querySelector("#generoJuego").textContent = juegoSeleccionado.genero || "";
    document.querySelector("#fechaJuego").textContent = juegoSeleccionado.fecha || "";
    document.querySelector("#valoracionJuego").textContent = juegoSeleccionado.valoracion || "";    
}

function eliminarJuego() { // Boton eliminar, envia los datos al servlet para eliminar el juego
    document.querySelector("#eliminarJuego").addEventListener("click", function() {
        if (confirm("¿Estás seguro de que deseas eliminar el juego?")) {
            fetch("JuegoServlet?id="+juegoSeleccionado.id)
            .then(() => {window.location.href = "misjuegos.html"})
        } 
    });
}

window.onload = function(){
    copiarJuego();
    pintarConsolas();
    eliminarJuego();
}

