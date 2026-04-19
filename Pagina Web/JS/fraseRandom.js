const frases = {
  es: [
    {
      texto: "No cuentes los días, haz que los días cuenten.",
      autor: "Muhammad Ali"
    },
    {
      texto: "El éxito no es un accidente; es trabajo duro, perseverancia, aprendizaje y sacrificio.",
      autor: "Pelé"
    },
    {
      texto: "El dolor es temporal, el orgullo es para siempre.",
      autor: "Lance Armstrong"
    },
    {
      texto: "La disciplina vence al talento cuando el talento no se disciplina.",
      autor: "David Laid"
    },
    {
      texto: "La resistencia que enfrentas hoy es la fuerza que tendrás mañana.",
      autor: "Arnold Schwarzenegger"
    }
  ],
  en: [
    {
      texto: "Don't count the days, make the days count.",
      autor: "Muhammad Ali"
    },
    {
      texto: "Success is no accident. It is hard work, perseverance, learning, and sacrifice.",
      autor: "Pelé"
    },
    {
      texto: "Pain is temporary, pride is forever.",
      autor: "Lance Armstrong"
    },
    {
      texto: "Discipline beats talent when talent doesn't discipline itself.",
      autor: "David Laid"
    },
    {
      texto: "The resistance you face today is the strength you'll have tomorrow.",
      autor: "Arnold Schwarzenegger"
    }
  ]
};

function fraseAleatoria(idioma = 'es') {
  const frasesIdioma = frases[idioma] || frases.es;
  const indice = Math.floor(Math.random() * frasesIdioma.length);
  return frasesIdioma[indice];
}

function mostrarFrase() {
  // Obtener idioma actual (por defecto español)
  const idiomaActual = localStorage.getItem('idioma') || 'es';
  const frase = fraseAleatoria(idiomaActual);
  
  const elementoFrase = document.getElementById("frase");
  if (elementoFrase) {
    elementoFrase.innerHTML = `"${frase.texto}" <span>– ${frase.autor}</span>`;
  }
}

// Función para actualizar la frase cuando cambia el idioma
function actualizarFraseIdioma() {
  mostrarFrase();
}

// Mostrar frase al cargar la página
document.addEventListener('DOMContentLoaded', mostrarFrase);

// Exponer la función globalmente para que pueda ser llamada desde idioma.js
window.actualizarFraseIdioma = actualizarFraseIdioma;
