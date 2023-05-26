function obtenerDatos() { // Llama al servler para recuperar todos los datos del usuario.
    fetch("UsuarioServlet?opcion=1")
    .then(resultado => resultado.json())
    .then(resultado => pintarDatos(resultado));
}

function pintarDatos(datos) { // Pinta los datos del usuario en el html, se utiliza dentro de "obtenerDatos()"
    document.querySelector("#saludo").textContent = "Hola " + datos.nombre + "!";
    document.querySelector("#ed-nombre").value = datos.nombre || "";
    document.querySelector("#ed-apellido").value = datos.apellido || "";
    document.querySelector("#ed-fecha").value = datos.fecha || "";
    document.querySelector("#ed-pais").value = datos.pais || "";
    document.querySelector("#ed-correo").value = datos.mail || "";
    document.querySelector("#ed-password").value = datos.password || "";
    admin(datos.permiso);
}

function admin(permiso) { //Valida el nivel de permiso para agregar un boton adicional que lleva al aparado Administrador
    if (permiso == 10) {
        let botonEliminar = document.querySelector("#eliminarUsuario");
        let admin = document.createElement("A");
        admin.textContent = "Admin";
        admin.classList.add("boton");
        admin.href = "perfiladmin.html";
        botonEliminar.after(admin);
    }
}

function cerrarSesion() { // Boton cerrar sesion, envia una llamada al servler en invalida la sesion y se envia al login
    document.querySelector("#cerrarSesion").addEventListener("click", function() {
        fetch("LoginServlet")
        .then(() => {window.location.href = "login.html"}) 
    });
}

function eliminarUsuario() { // Boton eliminar, envia los datos al servlet para eliminar al usuario
    document.querySelector("#eliminarUsuario").addEventListener("click", function() {
        if (confirm("¿Estás seguro de que deseas eliminar tu cuenta? Se eliminarán todas las consolas y juegos almacenados")) {
            fetch("UsuarioServlet?opcion=2")
            .then(() => {window.location.href = "login.html"})
        } 
    });
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

function mensaje() { // Llamada al aviso con url del servlet
    if (location.search.includes("validado")) { // Formulario ok
        aviso("validado", "Se realizaron los cambios de tu perfil");
    }else if (location.search.includes("permiso")) { // En caso de saltar a la pagina admin y no tener permisos
        aviso("error", "No tienes permisos de Administrador");
    }  
}

function validacion() { // Validacion de formulario
    document.querySelector("#actualizar").addEventListener("click", function(event) {

        event.preventDefault();
        
        let valido = true;
        let nombre = document.querySelector("#ed-nombre").value.trim();
        let apellido = document.querySelector("#ed-apellido").value.trim();
        let fecha = document.querySelector("#ed-fecha").value;
        let pais = document.querySelector("#ed-pais").value.trim();
        let correo = document.querySelector("#ed-correo").value.trim();
        let password = document.querySelector("#ed-password").value.trim();

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
            document.querySelector("#formularioPerfil").submit(); 
        }
    });
}

window.onload = function(){
    obtenerDatos();
    cerrarSesion();
    eliminarUsuario();
    mensaje();
    validacion();
}
