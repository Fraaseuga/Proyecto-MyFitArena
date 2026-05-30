// URL de la API
const API_URL = '../API/tickets.php';

// Traducciones para tickets
const traduccionesTickets = {
    es: {
        eliminar: 'Eliminar',
        no_tickets: 'No hay tickets registrados',
        ticket_enviado: 'Tu consulta ha sido enviada correctamente.',
        error_envio: 'Error al enviar el ticket. Por favor, intenta de nuevo.',
        confirmar_eliminar: '¿Estás seguro de que deseas eliminar este ticket?',
        ticket_eliminado: 'Ticket eliminado correctamente',
        cargando: 'Cargando tickets...',
        error_cargar: 'Error al cargar los tickets',
        consulta: "Consulta general",
        soporte: "Soporte técnico",
        sugerencia: "Sugerencia",
        otro: "Otro",
    },
    en: {
        eliminar: 'Delete',
        no_tickets: 'No tickets registered',
        ticket_enviado: 'Your query has been sent successfully.',
        error_envio: 'Error sending ticket. Please try again.',
        confirmar_eliminar: 'Are you sure you want to delete this ticket?',
        ticket_eliminado: 'Ticket deleted successfully',
        cargando: 'Loading tickets...',
        error_cargar: 'Error loading tickets',
        consulta: "General inquiry",
        soporte: "Technical support",
        sugerencia: "Suggestion",
        otro: "Other",
    }
};

// Claves de los asuntos
const mapaAsuntos = {
    "Consulta general": "consulta",
    "Soporte técnico": "soporte",
    "Sugerencia": "sugerencia",
    "Otro": "otro"
};

// Función para sacar la clave
function traducirAsunto(asunto) {
    const clave = mapaAsuntos[asunto] || asunto;
    return tTicket(clave);
}

// Función para obtener traducción de tickets
function tTicket(clave) {
    return traduccionesTickets[window.idiomaActual][clave] || clave;
}

// CARGAR TICKETS AL INICIAR LA PÁGINA
document.addEventListener('DOMContentLoaded', function() {
    cargarTickets();
    
    // Event listener para el formulario de contacto
    const formulario = document.getElementById('formulario-contacto');
    if (formulario) {
        formulario.addEventListener('submit', enviarTicket);
    }
});

// FUNCIÓN: CARGAR TODOS LOS TICKETS
window.cargarTickets = async function cargarTickets() {
    try {
        const response = await fetch(API_URL, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(tTicket(error_cargar));
        }

        const tickets = await response.json();
        mostrarTickets(tickets);
    } catch (error) {
        console.error('Error:', error);
        const tbody = document.getElementById('tabla-tickets');
        if (tbody) {
            tbody.innerHTML = `<tr><td colspan="5" style="text-align: center; padding: 2rem; color: var(--amarillo-texto);">${tTicket('error_cargar')}</td></tr>`;
        }
    }
}

// FUNCIÓN: MOSTRAR TICKETS EN LA TABLA
function mostrarTickets(tickets) {
    const tbody = document.getElementById('tabla-tickets');
    
    if (!tbody) return;

    if (tickets.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5" style="text-align: center; padding: 2rem; color: var(--amarillo-texto);">${tTicket('no_tickets')}</td></tr>`;
        return;
    }

    tbody.innerHTML = tickets.map((ticket,index) => `
        <tr data-id="${ticket.id}">
            <td>#${(index+1)}</td>
            <td>${ticket.nombre}</td>
            <td>${traducirAsunto(ticket.asunto)}</td>
            <td>${ticket.fecha_creacion}</td>
            <td class="acciones-ticket">
                <button onclick="eliminarTicket(${ticket.id})" class="btn-eliminar" title="${tTicket('eliminar')}">
                    <span>🗑️</span>
                </button>
            </td>
        </tr>
    `).join('');
}

// FUNCIÓN: ENVIAR NUEVO TICKET
async function enviarTicket(e) {
    e.preventDefault();

    const nombre = document.getElementById('nombre-contacto').value;
    const email = document.getElementById('email-contacto').value;
    const asunto = document.getElementById('asunto').value;
    const mensaje = document.getElementById('mensaje').value;

    // Validar que el asunto no sea la opción por defecto
    if (!asunto || asunto === '') {
        mostrarMensaje('Por favor selecciona un asunto', 'error');
        return;
    }

    const datosTicket = {
        nombre: nombre,
        email: email,
        asunto: asunto,
        mensaje: mensaje
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosTicket)
        });

        const resultado = await response.json();

        if (resultado.exito) {
            mostrarMensaje(tTicket('ticket_enviado'),'exito');
            document.getElementById('formulario-contacto').reset();
            cargarTickets(); // Recargar la tabla
        } else {
            mostrarMensaje(resultado.mensaje || tTicket('error_envio'), 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarMensaje(tTicket('error_envio'), 'error');
    }
}

// FUNCIÓN: ELIMINAR TICKET
async function eliminarTicket(id) {
    if (!confirm(tTicket('confirmar_eliminar'))) {
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: id })
        });

        const resultado = await response.json();

        if (resultado.exito) {
            mostrarMensaje(tTicket('ticket_eliminado'), 'exito');
            cargarTickets();
        } else {
            mostrarMensaje(resultado.mensaje, 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarMensaje('Error al eliminar el ticket', 'error');
    }
}

// FUNCIÓN: MOSTRAR MENSAJE DE FEEDBACK
function mostrarMensaje(texto, tipo) {
    const mensajeResultado = document.querySelector('.mensaje-resultado');
    if (mensajeResultado) {
        mensajeResultado.textContent = texto;
        mensajeResultado.className = `mensaje-resultado ${tipo}`;
        mensajeResultado.style.display = 'block';

        setTimeout(() => {
            mensajeResultado.style.display = 'none';
        }, 5000);
    }
}

// FUNCIÓN: CAMBIAR IDIOMA TICKETS
function cambiarIdiomaTickets(nuevoIdioma) {
    idiomaActualTickets = nuevoIdioma;
    cargarTickets(); // Recargar para aplicar traducciones
}