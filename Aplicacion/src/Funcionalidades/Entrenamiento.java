package Funcionalidades;
import java.util.ArrayList;

abstract public class Entrenamiento {
	private String dificultad;
	private int duracion;
	private ArrayList<Ejercicio> ejercicios;
	
	// Constructores
	public Entrenamiento(String dificultad, int duracion) {
		this.dificultad = dificultad;
		this.duracion = duracion;
		ejercicios = new ArrayList<Ejercicio>();
	}
	
	// Getters - Setters
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	// Método para saber si un ejercicio existe
	public Ejercicio buscarEjercicio(String nombreEj) {
		// Recorre el ArrayList buscando a ver si hay un ejercicio con el mismo nombre
		for(Ejercicio ej : ejercicios) {
			if(ej.getNombreEjercicio().equalsIgnoreCase(nombreEj)) {
				return ej;
			}
		}
		return null;
	}
	
	// Método para añadir un ejercicio al ArrayList de ejercicios
	public void addEjercicio(Ejercicio ej) {
		ejercicios.add(ej);
	}
	
	// Método para borrar un ejercicio del ArrayList de ejercicios
	public void removeEjercicio(Ejercicio ej) {
		ejercicios.remove(ej);
	}
	
	// Método para devolver todos los ejercicios
	public String mostrarTodosEjercicios() {
		String todos = "";
		for(Ejercicio ej : ejercicios) {
			todos += ej.toString()+"\n";
		}
		return todos;
	}
	
	// Método que se heredará, su función será calcular las calorias que se queman
	abstract public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario,boolean esHombre);
}