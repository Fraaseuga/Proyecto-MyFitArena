    
const alturaInput = document.getElementById('altura');
const pesoInput = document.getElementById('peso');
const calcularBtn = document.getElementById('calcular-imc');
const reiniciarBtn = document.getElementById('reiniciar-imc');
const resultadoP = document.querySelector('.resultado-IMC');

// Función para obtener categoría traducida
function obtenerCategoriaTraducida(imc) {
    const idiomaActual = localStorage.getItem('idioma') || 'es';
    const traducciones = window.traducciones || {};
    
    let claveCategoria = '';
    
    if (imc < 18.5) {
        claveCategoria = 'imc-bajo-peso';
    } else if (imc >= 18.5 && imc < 25) {
        claveCategoria = 'imc-peso-normal';
    } else if (imc >= 25 && imc < 30) {
        claveCategoria = 'imc-sobrepeso';
    } else {
        claveCategoria = 'imc-obesidad';
    }
    
    return traducciones[idiomaActual]?.[claveCategoria] || '';
}

// Función para calcular IMC
function calcularIMC() {
    const alturaTexto = alturaInput.value.trim();
    const pesoTexto = pesoInput.value.trim();

    // Convertir a números
    const altura = parseFloat(alturaTexto);
    const peso = parseFloat(pesoTexto);

    // Validar que sean números válidos y positivos
    if (isNaN(altura) || isNaN(peso) || altura <= 0 || peso <= 0) {
        return; // No hacer nada si los valores no son válidos
    }

    // Convertir altura de cm a metros
    const alturaMetros = altura / 100;

    // Calcular IMC: peso / (altura²)
    const imc = peso / (alturaMetros * alturaMetros);

    // Obtener categoría traducida
    const categoria = obtenerCategoriaTraducida(imc);

    resultadoP.textContent = `IMC: ${imc.toFixed(2)} - ${categoria}`;
}

// Función para reiniciar
function reiniciar() {
    alturaInput.value = '';
    pesoInput.value = '';
    
    const idiomaActual = localStorage.getItem('idioma') || 'es';
    const traducciones = window.traducciones || {};
    const textoResultado = traducciones[idiomaActual]?.['imc-resultado'] || 'Resultado';
    
    resultadoP.textContent = textoResultado;
}

// Funciones a ejecutar al hacer clic en cada botón
calcularBtn.addEventListener('click', calcularIMC);
reiniciarBtn.addEventListener('click', reiniciar);
