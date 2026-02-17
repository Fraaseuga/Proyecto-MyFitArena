const frases = [
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
];

function fraseAleatoria() {
  const indice = Math.floor(Math.random() * frases.length);
  return frases[indice];
}

const frase = fraseAleatoria();

document.getElementById("frase").innerHTML = `"${frase.texto}" <span>– ${frase.autor}</span>`;