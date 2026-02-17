    
const alturaInput = document.getElementById('altura');
const pesoInput = document.getElementById('peso');
const calcularBtn = document.getElementById('calcular-imc');
const reiniciarBtn = document.getElementById('reiniciar-imc');
const resultadoP = document.querySelector('.resultado-IMC');

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

    // Determinar la categoría
    let categoria = '';
    if (imc < 18.5) {
        categoria = 'Bajo peso';
    } else if (imc >= 18.5 && imc < 25) {
        categoria = 'Peso normal';
    } else if (imc >= 25 && imc < 30) {
        categoria = 'Sobrepeso';
    } else {
        categoria = 'Obesidad';
    }

    resultadoP.textContent = `IMC: ${imc.toFixed(2)} - ${categoria}`;
}

// Función para reiniciar
function reiniciar() {
    alturaInput.value = '';
    pesoInput.value = '';
    resultadoP.textContent = 'Resultado';
}

// Funciones a ejecutar al hacer clic en cada botón
calcularBtn.addEventListener('click', calcularIMC);
reiniciarBtn.addEventListener('click', reiniciar);