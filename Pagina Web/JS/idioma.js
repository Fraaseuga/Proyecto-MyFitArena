// Sistema de traducción para My Fit Arena
const traducciones = {
    es: {
        // Header
        'header-title': 'My Fit Arena',
        
        // Navegación
        'nav-entrenamientos': 'Entrenamientos',
        'nav-calcIMC': 'Calculadora IMC',
        'nav-clubs': 'Clubs',
        'nav-contacto': 'Contacto',
        
        // Sección Entrenamientos
        'section-entrenamientos-title': 'Entrenamientos',
        'entrenamiento-calistenia-title': 'Calistenia',
        'entrenamiento-calistenia-text': 'La <span>calistenia</span> es un método de entrenamiento basado en el <span>peso corporal</span>. Incluye ejercicios como sentadillas, y se centra en desarrollar <span>fuerza</span>, <span>control corporal</span> y <span>movilidad</span>. Es quienes buscan un entrenamiento funcional, progresivo y sin necesidad de equipamiento.',
        
        'entrenamiento-hipertrofia-title': 'Hipertrofia',
        'entrenamiento-hipertrofia-text': 'El entrenamiento de <span>hipertrofia</span> está diseñado para <span>aumentar el tamaño del músculo</span>. Se basa en rutinas con cargas moderadas a altas, repeticiones controladas y un enfoque en la técnica y el <span>tiempo bajo tensión</span>.',
        
        'entrenamiento-powerlifting-title': 'Powerlifting',
        'entrenamiento-powerlifting-text': 'El <span>powerlifting</span> es un deporte de <span>fuerza máxima</span> centrado en tres levantamientos: <span>sentadilla</span>, <span>press de banca</span> y <span>peso muerto</span>. Su objetivo es levantar la mayor cantidad de peso posible en cada uno de ellos.',
        
        'entrenamiento-cardio-title': 'Cardio',
        'entrenamiento-cardio-text': 'El entrenamiento <span>cardiovascular</span> incluye actividades como correr, nadar o montar en bicicleta. Su propósito principal es mejorar la <span>resistencia</span> y la <span>salud del corazón</span>.',
        
        'entrenamiento-elasticidad-title': 'Elasticidad',
        'entrenamiento-elasticidad-text': 'El entrenamiento de <span>elasticidad</span> o <span>flexibilidad</span> se enfoca en mejorar el <span>rango de movimiento</span> de las articulaciones y la capacidad de estiramiento de los músculos. Incluye estiramientos estáticos, dinámicos y técnicas como yoga o movilidad articular.',
        
        // Sección IMC
        'section-imc-title': 'Calculadora <span>IMC</span>',
        'imc-label-altura': 'Altura en cm:',
        'imc-placeholder-altura': 'Ej: 175',
        'imc-label-peso': 'Peso en kg:',
        'imc-placeholder-peso': 'Ej: 70',
        'imc-btn-calcular': 'Calcular',
        'imc-btn-reiniciar': 'Reiniciar',
        'imc-resultado': 'Resultado',
        
        // Sección Clubs
        'section-clubs-title': 'Clubs Comunitarios',
        'clubs-subtitle': 'Encuentra tu tribu fitness',
        'clubs-description': 'Únete a un club, participa en eventos exclusivos, recibe anuncios de actividades y comparte tus logros con una comunidad que te motiva.',
        'clubs-stat1': 'Clubs activos',
        'clubs-stat2': 'Eventos anuales',
        'clubs-stat3': 'Miembros',
        
        // Sección Contacto
        'section-contacto-title': 'Contacto',
        'contacto-label-nombre': 'Nombre:',
        'contacto-placeholder-nombre': 'Tu nombre completo',
        'contacto-label-email': 'Email:',
        'contacto-placeholder-email': 'tu@email.com',
        'contacto-label-asunto': 'Asunto:',
        'contacto-option-default': 'Selecciona un asunto...',
        'contacto-option-consulta': 'Consulta general',
        'contacto-option-soporte': 'Soporte técnico',
        'contacto-option-sugerencia': 'Sugerencia',
        'contacto-option-otro': 'Otro',
        'contacto-label-mensaje': 'Mensaje:',
        'contacto-placeholder-mensaje': 'Escribe tu mensaje aquí...',
        'contacto-btn-enviar': 'Enviar Mensaje',
        
        // Categorías IMC
        'imc-bajo-peso': 'Bajo peso',
        'imc-peso-normal': 'Peso normal',
        'imc-sobrepeso': 'Sobrepeso',
        'imc-obesidad': 'Obesidad',
        
        // Tabla de tickets
        'titulo-historial': 'Historial de Consultas',
        'th-ticket': 'Nº Ticket',
        'th-nombre': 'Nombre',
        'th-email': 'Email',
        'th-asunto': 'Asunto',
        'th-fecha': 'Fecha',
        'th-acciones': 'Acción',
        
        // Footer
        'footer-text': '© My Fit Arena. Todos los derechos reservados. Queda prohibida la reproducción total o parcial de este sitio web, incluyendo textos, imágenes, logotipos y cualquier contenido, sin la autorización previa y por escrito de My Fit Arena.'
    },
    en: {
        // Header
        'header-title': 'My Fit Arena',
        
        // Navigation
        'nav-entrenamientos': 'Workouts',
        'nav-calcIMC': 'BMI Calculator',
        'nav-clubs': 'Clubs',
        'nav-contacto': 'Contact',
        
        // Workouts Section
        'section-entrenamientos-title': 'Workouts',
        'entrenamiento-calistenia-title': 'Calisthenics',
        'entrenamiento-calistenia-text': '<span>Calisthenics</span> is a training method based on <span>bodyweight</span>. It includes exercises like squats and focuses on developing <span>strength</span>, <span>body control</span>, and <span>mobility</span>. It\'s for those seeking a functional, progressive workout with no equipment needed.',
        
        'entrenamiento-hipertrofia-title': 'Hypertrophy',
        'entrenamiento-hipertrofia-text': '<span>Hypertrophy</span> training is designed to <span>increase muscle size</span>. It\'s based on routines with moderate to high loads, controlled repetitions, and a focus on technique and <span>time under tension</span>.',
        
        'entrenamiento-powerlifting-title': 'Powerlifting',
        'entrenamiento-powerlifting-text': '<span>Powerlifting</span> is a <span>maximum strength</span> sport focused on three lifts: <span>squat</span>, <span>bench press</span>, and <span>deadlift</span>. Its goal is to lift the maximum weight possible in each of them.',
        
        'entrenamiento-cardio-title': 'Cardio',
        'entrenamiento-cardio-text': '<span>Cardiovascular</span> training includes activities like running, swimming, or cycling. Its main purpose is to improve <span>endurance</span> and <span>heart health</span>.',
        
        'entrenamiento-elasticidad-title': 'Flexibility',
        'entrenamiento-elasticidad-text': '<span>Flexibility</span> training focuses on improving the <span>range of motion</span> of joints and the stretching capacity of muscles. It includes static and dynamic stretches and techniques like yoga or joint mobility.',
        
        // BMI Section
        'section-imc-title': '<span>BMI</span> Calculator',
        'imc-label-altura': 'Height in cm:',
        'imc-placeholder-altura': 'Ex: 175',
        'imc-label-peso': 'Weight in kg:',
        'imc-placeholder-peso': 'Ex: 70',
        'imc-btn-calcular': 'Calculate',
        'imc-btn-reiniciar': 'Reset',
        'imc-resultado': 'Result',
        
        // Clubs Section
        'section-clubs-title': 'Community Clubs',
        'clubs-subtitle': 'Find your fitness tribe',
        'clubs-description': 'Join a club, participate in exclusive events, receive activity announcements, and share your achievements with a motivating community.',
        'clubs-stat1': 'Active clubs',
        'clubs-stat2': 'Annual events',
        'clubs-stat3': 'Members',
        
        // Contact Section
        'section-contacto-title': 'Contact',
        'contacto-label-nombre': 'Name:',
        'contacto-placeholder-nombre': 'Your full name',
        'contacto-label-email': 'Email:',
        'contacto-placeholder-email': 'your@email.com',
        'contacto-label-asunto': 'Subject:',
        'contacto-option-default': 'Select a subject...',
        'contacto-option-consulta': 'General inquiry',
        'contacto-option-soporte': 'Technical support',
        'contacto-option-sugerencia': 'Suggestion',
        'contacto-option-otro': 'Other',
        'contacto-label-mensaje': 'Message:',
        'contacto-placeholder-mensaje': 'Write your message here...',
        'contacto-btn-enviar': 'Send Message',
        
        // BMI Categories
        'imc-bajo-peso': 'Underweight',
        'imc-peso-normal': 'Normal weight',
        'imc-sobrepeso': 'Overweight',
        'imc-obesidad': 'Obesity',
        
        // Tickets table
        'titulo-historial': 'Query History',
        'th-ticket': 'Ticket No.',
        'th-nombre': 'Name',
        'th-email': 'Email',
        'th-asunto': 'Subject',
        'th-fecha': 'Date',
        'th-acciones': 'Action',
        
        // Footer
        'footer-text': '© My Fit Arena. All rights reserved. Total or partial reproduction of this website, including texts, images, logos and any content, is prohibited without prior written authorization from My Fit Arena.'
    }
};
window.traducciones = traducciones;

// Idioma actual (por defecto español)
let idiomaActual = localStorage.getItem('idioma') || 'es';
window.idiomaActual = idiomaActual;

// Función para cambiar el idioma
function cambiarIdioma() {
    // Alternar entre español e inglés
    idiomaActual = idiomaActual === 'es' ? 'en' : 'es';
    window.idiomaActual = idiomaActual;

    // Guardar preferencia
    localStorage.setItem('idioma', idiomaActual);
    
    // Actualizar el atributo lang del HTML
    document.documentElement.lang = idiomaActual;
    
    // Aplicar traducciones
    aplicarTraducciones();
    
    // Actualizar texto del botón
    actualizarBotonIdioma();

    // Actualizar frase motivacional
    if (typeof actualizarFraseIdioma === 'function') {
        actualizarFraseIdioma();
    }

    // Actualiza la parte de los tickets
    if (typeof cargarTickets === 'function') {
        cargarTickets();
    }

}

// Función para aplicar las traducciones
function aplicarTraducciones() {
    // Obtener todos los elementos con atributo data-translate
    const elementos = document.querySelectorAll('[data-translate]');
    
    elementos.forEach(elemento => {
        const clave = elemento.getAttribute('data-translate');
        const traduccion = traducciones[idiomaActual][clave];
        
        if (traduccion) {
            // Si el elemento es un input o textarea
            if (elemento.tagName === 'INPUT' || elemento.tagName === 'TEXTAREA') {
                if (elemento.hasAttribute('placeholder')) {
                    elemento.placeholder = traduccion;
                }
            } 
            // Si es un botón
            else if (elemento.tagName === 'BUTTON') {
                elemento.textContent = traduccion;
            }
            // Para otros elementos
            else {
                elemento.innerHTML = traduccion;
            }
        }
    });
}

// Función para actualizar el texto del botón de idioma
function actualizarBotonIdioma() {
    const btnIdioma = document.getElementById('btn-idioma');
    if (btnIdioma) {
        btnIdioma.textContent = idiomaActual === 'es' ? 'EN' : 'ES';
        btnIdioma.title = idiomaActual === 'es' ? 'Switch to English' : 'Cambiar a Español';
    }
}

// Inicializar al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    // Aplicar idioma guardado
    document.documentElement.lang = idiomaActual;
    aplicarTraducciones();
    actualizarBotonIdioma();
    
    // Agregar evento al botón de cambio de idioma
    const btnIdioma = document.getElementById('btn-idioma');
    if (btnIdioma) {
        btnIdioma.addEventListener('click', cambiarIdioma);
    }
});