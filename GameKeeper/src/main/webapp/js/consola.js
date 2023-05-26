const consolaSeleccionada = JSON.parse(localStorage.getItem("consolaSeleccionada")); // Consola seleccionada almacenada en el almacenamiento local

function copiarConsola() { // Pinta los datos de la consola seleccionada en el html
    let portada = document.querySelector("#portadaConsola");
    if (consolaSeleccionada.foto != null) {
        portada.src = "fotosConsolas/"+consolaSeleccionada.foto;
        portada.alt = consolaSeleccionada.nombre;
    } else {
        portada.src = "";
    }
    document.querySelector("#nombreConsola").textContent = consolaSeleccionada.nombre || "";
    document.querySelector("#desarrolladorConsola").textContent = consolaSeleccionada.desarrollador || "";
    document.querySelector("#edicionConsola").textContent = consolaSeleccionada.edicion || "";
    document.querySelector("#fechaConsola").textContent = consolaSeleccionada.fecha || "";     
}

function eliminarConsola() { // Boton eliminar, envia los datos al servlet para eliminar la consola 
    document.querySelector("#eliminarConsola").addEventListener("click", function() {
        if (confirm("¿Estás seguro de que deseas eliminar la consola? Al hacerlo se eliminarán  todos los juegos juegos relacionados")) {
            fetch("ConsolaServlet?id="+consolaSeleccionada.id)
            .then(() => {window.location.href = "misconsolas.html"})
        } 
    });
}

window.onload = function(){
    copiarConsola(); 
    eliminarConsola();
}