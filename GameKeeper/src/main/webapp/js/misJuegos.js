function obtenerJuegos() { // Llama al servler para recuperar todos los datos de los juegos del usuario.
    fetch("MisJuegos?opcion=1&valor=" + "")
    .then(resultado => resultado.json())
    .then(resultado => pintarJuegos(resultado));
}

function listarConsola() { // Llama al servlet para recuperar las el nombre y el id de las consolas del usuario, dentro de filtroBuscar()
    fetch("ListarConsolas")
    .then(resultado => resultado.json())
    .then(resultado => datosConsolas = resultado);
}

function buscar() { // Toma la opcion y el valor de la busqueda del usuario, lo envia al servlet y recupera los resultados 
    document.querySelector("#botonBusJue").addEventListener("click", function() {
        let opcion = document.querySelector("#opcionJuego").value;
        let valor = document.querySelector("#valorJuego").value;
    
        fetch("MisJuegos?opcion=" + opcion + "&valor=" + valor)
        .then(resultado => resultado.json())
        .then(resultado => pintarJuegos(resultado));  
    });
}

function pintarJuegos(datos) { // Pinta el listado de los juegos en el html, ya sea usando el buscador con la funcion "buscar()" o apenas entrar a la pagina con "obtenerJuegos()"
    let resultadoJuegos = document.querySelector("#resultadoJuegos");
    resultadoJuegos.innerHTML = "";

    if (datos == null) { // Mensaje si no se encontraron resultados de busqueda
        let listaVacia = document.createElement("LI");
        listaVacia.textContent = "No se encontraron resultados";
        resultadoJuegos.appendChild(listaVacia);  
    } else {
        for(var i=0;i<datos.length;i++){
            let listaJuego = document.createElement("LI");
            let juego = document.createElement("A");
            juego.href = "juego.html";
            juego.classList.add("juego_consola");
            juego.dataset.juego = JSON.stringify(datos[i]); // Agregar los datos del juego como atributo de datos
            
            if (datos[i].foto == null) {
                juego.textContent = datos[i].nombre;
            } else{
                juego.style.backgroundImage = "url(fotosJuegos/"+datos[i].foto+")";
            };
    
            juego.addEventListener("click", (event) => { // Al hacer clic se guardan los datos en el almacenamiento local para llevarlo a "juego.html" y/o "editarjuego.html"
                event.preventDefault(); // Evitar que se siga el enlace
                const juegoDatos = JSON.parse(event.target.dataset.juego);
                localStorage.setItem("juegoSeleccionado", JSON.stringify(juegoDatos)); // Guardar los datos en localStorage
                window.location.href = event.target.href; // Redirigir a la página de destino
            });
            listaJuego.appendChild(juego);
            resultadoJuegos.appendChild(juego);
        };
    }
};

function filtroBuscar() { // Genera las opciones de busqueda dentro del buscador 

    let opcion = document.querySelector("#opcionJuego");
    let buscar = document.querySelector("#buscarJuego");

    opcion.addEventListener("change", function () {

        const opcionMarcada = this.value;
        buscar.innerHTML = "";

        switch (opcionMarcada) {
            case "1": // Buscar por nombre
                let nombreJuego = document.createElement('input');
                nombreJuego.id = "valorJuego";
                nombreJuego.type = "text";
                nombreJuego.classList.add("opcion");
                nombreJuego.placeholder = "Nombre del Juego";
                buscar.appendChild(nombreJuego);
                break;
            case "2": // Buscar por formaro
                const formatos = [
                    { valor: "Fisico", texto: "Físico" },
                    { valor: "Digital", texto: "Digital" }
                  ];
                  let formato = document.createElement("SELECT");
                  formato.id = "valorJuego";
                  formato.classList.add("opcion");
                  
                  for (let i = 0; i < formatos.length; i++) {
                    let option = document.createElement("OPTION");
                    option.value = formatos[i].valor;
                    option.textContent = formatos[i].texto;
                    formato.appendChild(option);
                  }               
                  buscar.appendChild(formato);            
                break;
            case "3": // Buscar por consola
                let consola = document.createElement("SELECT");
                consola.id = "valorJuego";
                consola.classList.add("opcion");
                for(var i=0;i<datosConsolas.length;i++){
                    let option = document.createElement("OPTION");
                    option.value = datosConsolas[i].id;
                    option.text = datosConsolas[i].nombre;
                    consola.appendChild(option);
                }
                buscar.appendChild(consola); 
                break;
            case "4": // Buscar por estado
                const estados = [
                    { valor: "Deseado", texto: "Deseado" },
                    { valor: "Obtenido", texto: "Obtenido" },
                    { valor: "Jugando", texto: "Jugando" },
                    { valor: "Terminado", texto: "Terminado" }
                  ];               
                  let estado = document.createElement("SELECT");
                  estado.id = "valorJuego";
                  estado.classList.add("opcion");
                  
                  for (let i = 0; i < estados.length; i++) {
                    let option = document.createElement("OPTION");
                    option.value = estados[i].valor;
                    option.textContent = estados[i].texto;
                    estado.appendChild(option);
                  }                
                  buscar.appendChild(estado); 
                break;
            case "5": // Buscar por valoracion
                let valoracion = document.createElement("SELECT");
                valoracion.id = "valorJuego";
                valoracion.classList.add("opcion");
                for(var i=1;i<=10;i++){
                    let option = document.createElement("OPTION");
                    option.value = i;
                    option.text = i;
                    valoracion.appendChild(option);
                }
                buscar.appendChild(valoracion); 
                break;
            default:
                break;
        }; 
    });  
};

window.onload = function(){
    obtenerJuegos();
    listarConsola();
    filtroBuscar();
    buscar();
}