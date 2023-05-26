function obtenerDatos() { // Llama al servler para recuperar todos los datos de la pagina principal
    fetch("PaginaPrincipal")
    .then(resultado => resultado.json())
    .then(resultado => pintarDatos(resultado));
}

function pintarDatos(datos) { // Pinta los datos de la pagina principal en el html 
    let ultimosJuegos = datos.ultimosJuegos;
    let ultimasConsolas = datos.ultimasConsolas;
    document.querySelector("#numeroJuegos").textContent = datos.juegos;
    document.querySelector("#numeroConsolas").textContent = datos.consolas;
    document.querySelector("#numeroJugando").textContent = datos.jugando;
    document.querySelector("#numeroTerminados").textContent = datos.terminado;
    document.querySelector("#numeroObtenidos").textContent = datos.obtenido;
    document.querySelector("#numeroDeseados").textContent = datos.deseados;

    if (!datos.hasOwnProperty("ultimosJuegos")) { // Si no tiene juegos aun añadidos se consulta si "ultimosJuegos" esta presente en el arreglo
        let listaVacia = document.createElement("LI");
        listaVacia.textContent = "Aún no tienes juegos añadidos"
        document.querySelector("#ultimosJuegos").appendChild(listaVacia);
    } else {
        for(var i=0;i<ultimosJuegos.length;i++){
            let listaJuego = document.createElement("LI");
            let juego = document.createElement("A");
            juego.href = "juego.html";
            juego.classList.add("juego_consola");
            juego.dataset.juego = JSON.stringify(ultimosJuegos[i]); // Agregar los datos del juego como atributo de datos
            if (ultimosJuegos[i].foto == null) {
                juego.textContent = ultimosJuegos[i].nombre;
            } else{
                juego.style.backgroundImage = "url(fotosJuegos/"+ultimosJuegos[i].foto+")"
            }
        
            juego.addEventListener("click", (event) => { // Al hacer clic se guardan los datos en el almacenamiento local para llevarlo a "juego.html" y/o "editarjuego.html"
                event.preventDefault(); // Evitar que se siga el enlace
                const juegoDatos = JSON.parse(event.target.dataset.juego);
                localStorage.setItem("juegoSeleccionado", JSON.stringify(juegoDatos)); // Guardar los datos en localStorage
                window.location.href = event.target.href; // Redirigir a la página de destino
            });
            listaJuego.appendChild(juego);
            document.querySelector("#ultimosJuegos").appendChild(listaJuego);
        }      
    }

    if (!datos.hasOwnProperty("ultimasConsolas")) { // Si no tiene consolas añadidas se consulta si "ultimasConsolas" esta presente en el arreglo
        let listaVacia = document.createElement("LI");
        listaVacia.textContent = "Aún no tienes consolas añadidas"
        document.querySelector("#ultimasConsolas").appendChild(listaVacia);
    } else {
        for(var i=0;i<ultimasConsolas.length;i++){
            let listaConsola = document.createElement("LI");
            let consola = document.createElement("A");
            consola.href = "consola.html";    
            consola.classList.add("juego_consola");
            consola.dataset.consola = JSON.stringify(ultimasConsolas[i]); // Agregar los datos de la consola como atributo de datos
            if (ultimasConsolas[i].foto == null) {
                consola.textContent = ultimasConsolas[i].nombre;
            } else{
                consola.style.backgroundImage = "url(fotosConsolas/"+ultimasConsolas[i].foto+")"
            } 
    
            consola.addEventListener("click", (event) => { // Al hacer clic se guardan los datos en el almacenamiento local para llevarlo a "consola.html" y/o "editarconsola.html"
                event.preventDefault(); // Evitar que se siga el enlace
                const consolaDatos = JSON.parse(event.target.dataset.consola);
                localStorage.setItem("consolaSeleccionada", JSON.stringify(consolaDatos)); // Guardar los datos en localStorage
                window.location.href = event.target.href; // Redirigir a la página de destino
            });
            listaConsola.appendChild(consola);
            document.querySelector("#ultimasConsolas").appendChild(listaConsola);
        }    
    }
}

window.onload = function(){
        obtenerDatos();
}