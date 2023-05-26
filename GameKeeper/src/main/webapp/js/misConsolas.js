function obtenerConsolas() { // Llama al servler para recuperar todos los datos de las consolas del usuario.
    fetch("MisConsolas?opcion=1&valor=" + "")
    .then(resultado => resultado.json())
    .then(resultado => pintarConsolas(resultado));
}


function buscar() { // Toma la opcion y el valor de la busqueda del usuario, lo envia al servlet y recupera los resultados 
    document.querySelector("#botonBusCos").addEventListener("click", function() {
        let opcion = document.querySelector("#opcionConsola").value;
        let valor = document.querySelector("#valorConsola").value;
    
        fetch("MisConsolas?opcion=" + opcion + "&valor=" + valor)
        .then(resultado => resultado.json())
        .then(resultado => pintarConsolas(resultado)); 
    });
}

function pintarConsolas(datos) { // Pinta el listado de las consolas en el html, ya sea usando el buscador con la funcion "buscar()" o apenas entrar a la pagina con "obtenerConsolas()"
    let resultadoConsolas = document.querySelector("#resultadoConsolas");
    resultadoConsolas.innerHTML = "";

    if (datos == null) { // Mensaje si no se encontraron resultados de busqueda
        let listaVacia = document.createElement("LI");
        listaVacia.textContent = "No se encontraron resultados";
        resultadoConsolas.appendChild(listaVacia);  
    } else {
        for(var i=0;i<datos.length;i++){
            let listaConsola = document.createElement("LI");
            let consola = document.createElement("A");
            consola.href = "consola.html";    
            consola.classList.add("juego_consola");
            consola.dataset.consola = JSON.stringify(datos[i]); // Agregar los datos de la consola como atributo de datos

            if (datos[i].foto == null) {
                consola.textContent = datos[i].nombre;
            } else{
                consola.style.backgroundImage = "url(fotosConsolas/"+datos[i].foto+")"
            }  
    
            consola.addEventListener("click", (event) => { // Al hacer clic se guardan los datos en el almacenamiento local para llevarlo a "consola.html" y/o "editarconsola.html"
                event.preventDefault(); // Evitar que se siga el enlace
                const consolaDatos = JSON.parse(event.target.dataset.consola);
                localStorage.setItem("consolaSeleccionada", JSON.stringify(consolaDatos)); // Guardar los datos en localStorage
                window.location.href = event.target.href; // Redirigir a la página de destino
            });
            listaConsola.appendChild(consola);
            resultadoConsolas.appendChild(consola);
        }      
    }
}

function filtroBuscar() { // Genera las opciones de busqueda dentro del buscador 

    let opcion = document.querySelector("#opcionConsola");
    let buscar = document.querySelector("#buscarConsola");

    opcion.addEventListener("change", function () {

        const opcionMarcada = this.value;
        buscar.innerHTML = "";

        switch (opcionMarcada) {
            case "1": // Buscar por nombre
                let nombreConsola = document.createElement('input');
                nombreConsola.id = "valorConsola";
                nombreConsola.type = "text";
                nombreConsola.classList.add("opcion");
                nombreConsola.placeholder = "Nombre de la Consola";
                buscar.appendChild(nombreConsola);
                break;
            case "2": // Buscar por Desarrollador
                let desarrollador = document.createElement('input');
                desarrollador.id = "valorConsola";
                desarrollador.type = "text";
                desarrollador.classList.add("opcion");
                desarrollador.placeholder = "Desarollador de la Consola";
                buscar.appendChild(desarrollador);           
                break;
            default:
                break;
        }; 
    });  
};

window.onload = function(){
    obtenerConsolas();
    filtroBuscar();
    buscar();
}
