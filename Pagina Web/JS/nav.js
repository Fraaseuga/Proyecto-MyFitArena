const enlacesNav = document.querySelectorAll('.menu-nav a');
    
enlacesNav.forEach(enlace => {
    enlace.addEventListener('click', function(e) {
        // Evitar que al hacer click vaya de golpe al bloque
        e.preventDefault();
            
        // Quitar clase activo de todos
        enlacesNav.forEach(link => link.classList.remove('activo'));
            
        // Añadir clase activo al clickeado
        this.classList.add('activo');
        
        // Seleccionar el elemento clickeado
        const targetId = this.getAttribute('href').substring(1);
        const targetSection = document.getElementById(targetId);
        
        // Scroll suave a la sección
        if (targetSection) {
            targetSection.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});