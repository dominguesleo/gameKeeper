function aviso(estado1, estado2, mensaje) { //Estructura para crear el aviso
  let aviso = document.createElement("DIV");
  aviso.textContent = mensaje;
  aviso.classList.add(estado1, estado2);
  document.querySelector("main").after(aviso);
  setTimeout(() => {
    aviso.remove();
  }, 3000); 
}

document.addEventListener("DOMContentLoaded", function() { // Llamada al aviso con url del servlet
  if (location.search.includes("error")) { // Credenciales no validas
    aviso("error", "mini", "Usuario o contraseña no válidos");
  } else if (location.search.includes("password")) { //Confirmacion cambio de contraseña
    aviso("validado", "mini", "Contraseña modificada");
  } else if (location.search.includes("permiso")) { //usuario bloqueado
    aviso("error", "mini", "Usuario bloqueado");
  } 
});